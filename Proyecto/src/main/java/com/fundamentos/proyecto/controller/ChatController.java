package com.fundamentos.proyecto.controller;

import com.fundamentos.proyecto.model.Mensaje;
import com.fundamentos.proyecto.model.Usuario;
import com.fundamentos.proyecto.dao.MensajeDAO;
import com.fundamentos.proyecto.dao.UsuarioDAO;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.Pos;
import javafx.scene.text.TextAlignment;

import java.sql.Connection;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class ChatController {

    @FXML private VBox mensajesVBox;
    @FXML private TextField mensajeTextField;
    @FXML private Button enviarButton;
    @FXML private Label chatTitle;
    @FXML private Label chatStatus;
    @FXML private ScrollPane mensajesScrollPane; // Asegúrate de tenerlo en tu FXML

    private int idPublicacion;
    private int usuarioActualId;
    private int receptorId;
    private Connection conexion;

    private MensajeDAO mensajeDAO;

    private Timer timer;

    public void initData(int idPublicacion, int usuarioActualId, int receptorId, Connection conexion) {
        this.idPublicacion = idPublicacion;
        this.usuarioActualId = usuarioActualId;
        this.receptorId = receptorId;
        this.conexion = conexion;

        this.mensajeDAO = new MensajeDAO(conexion);

        // Cargar nombre receptor
        Usuario receptor = UsuarioDAO.obtenerUsuarioPorId(receptorId, conexion);
        chatTitle.setText(receptor != null ? receptor.getNombre() : "Desconocido");

        // Mostrar mensajes al inicio
        cargarMensajes();

        // Enviar mensaje al presionar botón o Enter
        enviarButton.setOnAction(e -> enviarMensaje());
        mensajeTextField.setOnAction(e -> enviarMensaje());

        // Refrescar mensajes cada 1 segundo
        timer = new Timer(true);
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(ChatController.this::cargarMensajes);
            }
        }, 0, 1000);
    }

    public void closeChat() {
        if (timer != null) timer.cancel();
    }

    private void cargarMensajes() {
        mensajesVBox.getChildren().clear();
        List<Mensaje> mensajes = mensajeDAO.obtenerMensajesPorPublicacion(idPublicacion, usuarioActualId, receptorId);
        for (Mensaje m : mensajes) {
            mensajesVBox.getChildren().add(crearBurbujaMensaje(m));
        }
        // Auto-scroll al final
        if (!mensajesVBox.getChildren().isEmpty() && mensajesScrollPane != null) {
            Platform.runLater(() -> mensajesScrollPane.setVvalue(1.0));
        }
    }

    private void enviarMensaje() {
        String texto = mensajeTextField.getText();
        if (texto == null || texto.trim().isEmpty()) return;

        mensajeDAO.insertarMensaje(idPublicacion, usuarioActualId, receptorId, texto);
        mensajeTextField.clear();
        cargarMensajes();
    }

    // Burbujas alineadas derecha/izquierda según remitente
    private HBox crearBurbujaMensaje(Mensaje mensaje) {
        HBox contenedor = new HBox();
        contenedor.setSpacing(6);
        contenedor.setPadding(new Insets(2, 10, 2, 10));
        Label contenido = new Label(mensaje.getContenido());
        contenido.setWrapText(true);
        contenido.setMaxWidth(300);
        contenido.setTextAlignment(TextAlignment.LEFT);
        contenido.setStyle(
                "-fx-padding: 10 16 10 16; -fx-background-radius: 18;" +
                        (mensaje.getEmisorId() == usuarioActualId ?
                                "-fx-background-color: #375fff; -fx-text-fill: white;" :
                                "-fx-background-color: #f1f1f1; -fx-text-fill: black;")
        );

        Label fecha = new Label(mensaje.getFecha());
        fecha.setStyle("-fx-font-size: 10; -fx-text-fill: #777;");
        VBox burbuja = new VBox(contenido, fecha);
        burbuja.setAlignment(Pos.BOTTOM_LEFT);

        if (mensaje.getEmisorId() == usuarioActualId) {
            contenedor.setAlignment(Pos.CENTER_RIGHT);
            contenedor.getChildren().add(burbuja);
        } else {
            contenedor.setAlignment(Pos.CENTER_LEFT);
            contenedor.getChildren().add(burbuja);
        }
        return contenedor;
    }
}
