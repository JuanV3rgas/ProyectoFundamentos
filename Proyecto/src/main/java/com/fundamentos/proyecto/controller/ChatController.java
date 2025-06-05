package com.fundamentos.proyecto.controller;

import com.fundamentos.proyecto.dao.MensajeDAO;
import com.fundamentos.proyecto.dao.UsuarioDAO;
import com.fundamentos.proyecto.model.Conversacion;
import com.fundamentos.proyecto.model.Mensaje;
import com.fundamentos.proyecto.model.Usuario;
import com.fundamentos.proyecto.util.CambiaEscenas;
import javafx.application.Platform;
import javafx.event.ActionEvent;
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
    @FXML private VBox chatsListVBox;
    @FXML private TextField mensajeTextField;
    @FXML private Button enviarButton;
    @FXML private Label chatTitle;
    @FXML private ScrollPane mensajesScrollPane;

    private int idPublicacion;
    private int usuarioActualId;
    private int receptorId;
    private Connection conexion;
    private MensajeDAO mensajeDAO;

    private Timer timer;

    private CambiaEscenas cambia = new CambiaEscenas();


    public void initData(int idPublicacion, int usuarioActualId, int receptorId, Connection conexion) {
        if (conexion == null) {
            throw new IllegalArgumentException("La conexión a la base de datos no puede ser null");
        }

        this.idPublicacion = idPublicacion;
        this.usuarioActualId = usuarioActualId;
        this.receptorId = receptorId;
        this.conexion = conexion;
        this.mensajeDAO = new MensajeDAO(conexion);

        cargarListaConversaciones();

        if (idPublicacion > 0 && receptorId > 0) {
            // Hay un chat seleccionado: carga el título y los mensajes de la conversación
            Usuario receptor = UsuarioDAO.obtenerUsuarioPorId(receptorId, conexion);
            chatTitle.setText(receptor != null ? receptor.getNombre() : "Desconocido");
            cargarMensajes();
        } else {
            // No hay chat seleccionado, muestra sólo la lista de chats y limpia la ventana de mensajes
            chatTitle.setText("Selecciona un chat");
            mensajesVBox.getChildren().clear();
        }

        enviarButton.setOnAction(e -> enviarMensaje());
        mensajeTextField.setOnAction(e -> enviarMensaje());

        // Timer para refrescar mensajes si hay chat seleccionado
        if (timer != null) timer.cancel();

        if (idPublicacion > 0 && receptorId > 0) {
            timer = new Timer(true);
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    Platform.runLater(ChatController.this::cargarMensajes);
                }
            }, 0, 1000);
        }
    }

    public void closeChat() {
        if (timer != null) timer.cancel();
    }

    private void cargarListaConversaciones() {
        chatsListVBox.getChildren().clear();
        List<Conversacion> conversaciones = mensajeDAO.obtenerConversacionesParaUsuario(usuarioActualId);

        for (Conversacion conv : conversaciones) {
            HBox chatPreview = new HBox();
            chatPreview.setSpacing(12);
            chatPreview.setAlignment(Pos.CENTER_LEFT);
            chatPreview.setPadding(new Insets(6, 8, 6, 18));
            chatPreview.setStyle("-fx-background-color: transparent;");

            Usuario otro = UsuarioDAO.obtenerUsuarioPorId(conv.getOtroUsuarioId(), conexion);
            String nombreUsuario = (otro != null) ? otro.getNombre() : "Usuario " + conv.getOtroUsuarioId();

            VBox textoPreview = new VBox();
            Label nombre = new Label(nombreUsuario);
            nombre.setStyle("-fx-font-size: 15px; -fx-text-fill: white; -fx-font-weight: bold;");
            Label ultimoMensaje = new Label(conv.getUltimoMensaje() != null ? conv.getUltimoMensaje() : "");
            ultimoMensaje.setStyle("-fx-font-size: 13px; -fx-text-fill: #aaaaaa;");
            textoPreview.getChildren().addAll(nombre, ultimoMensaje);

            chatPreview.getChildren().add(textoPreview);

            // Siempre pasa tu usuario como usuarioActualId, el otro como receptorId
            chatPreview.setOnMouseClicked(e -> {
                initData(conv.getIdPublicacion(), usuarioActualId, conv.getOtroUsuarioId(), conexion);
            });

            chatsListVBox.getChildren().add(chatPreview);
        }
    }

    private void cargarMensajes() {
        System.out.println("Cargando mensajes para idPublicacion=" + idPublicacion +
                ", usuarioActualId=" + usuarioActualId +
                ", receptorId=" + receptorId);

        mensajesVBox.getChildren().clear();
        List<Mensaje> mensajes = mensajeDAO.obtenerMensajesPorConversacion(idPublicacion, usuarioActualId, receptorId);

        System.out.println("Mensajes recuperados: " + mensajes.size());
        for (Mensaje m : mensajes) {
            System.out.println("Mensaje: " + m.getEmisorId() + "->" + m.getReceptorId() + " : " + m.getContenido());
            mensajesVBox.getChildren().add(crearBurbujaMensaje(m));
        }
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
        cargarListaConversaciones();
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

    @FXML
    private void back(ActionEvent event) {
        cambia.cambiarEscena(event, "/view/principal_sin_login.fxml");
    }
}
