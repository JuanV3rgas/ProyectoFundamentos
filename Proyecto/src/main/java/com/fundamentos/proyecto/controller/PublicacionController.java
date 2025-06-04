package com.fundamentos.proyecto.controller;

import com.fundamentos.proyecto.model.Inmueble;
import com.fundamentos.proyecto.model.Publicacion;
import com.fundamentos.proyecto.util.CambiaEscenas;
import com.fundamentos.proyecto.util.UserSession;
import com.fundamentos.proyecto.util.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Connection;

public class PublicacionController {

    @FXML private Label tipoLabel;
    @FXML private Label estadoLabel;
    @FXML private Label direccionLabel;
    @FXML private Label estratoLabel;
    @FXML private Label precioLabel;
    @FXML private Label habitacionesLabel;
    @FXML private Label banosLabel;
    @FXML private ImageView imageView1;

    private CambiaEscenas cambia = new CambiaEscenas();

    // IDs necesarios para el chat
    private int idPublicacion;
    private int dueñoId;

    public void setData(Inmueble inmueble, Publicacion publicacion) {
        if (inmueble == null || publicacion == null) return;
        tipoLabel.setText(inmueble.getTipo());
        estadoLabel.setText(inmueble.getEstado());
        direccionLabel.setText(inmueble.getDireccion());
        estratoLabel.setText(String.valueOf(inmueble.getEstrato()));
        precioLabel.setText(String.valueOf(inmueble.getPrecio()));
        habitacionesLabel.setText(String.valueOf(inmueble.getHabitaciones()));
        banosLabel.setText(String.valueOf(inmueble.getBanos()));
        if (inmueble.getImagen1() != null) {
            imageView1.setImage(new Image(new ByteArrayInputStream(inmueble.getImagen1())));
        } else {
            imageView1.setImage(null);
        }
        this.idPublicacion = publicacion.getId();
        this.dueñoId = publicacion.getIdUsuario();
    }

    @FXML
    private void onContactoClick() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/chat.fxml"));
            Parent root = loader.load();
            ChatController chatController = loader.getController();
            UserSession session = UserSession.getInstance();

            int usuarioActualId = session.getUserId();

            // Usa tu singleton de conexión (H2)
            chatController.initData(idPublicacion, usuarioActualId, dueñoId, getConexionH2());

            Stage stage = new Stage();
            stage.setTitle("Chat con el dueño");
            stage.setScene(new Scene(root));
            stage.setOnCloseRequest(e -> chatController.closeChat());
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Devuelve la conexión real con la base de datos
    private Connection getConexionH2() {
        try {
            return DBConnection.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @FXML
    private void back(ActionEvent event) {
        cambia.cambiarEscena(event, "/view/resultados.fxml");
    }

}
