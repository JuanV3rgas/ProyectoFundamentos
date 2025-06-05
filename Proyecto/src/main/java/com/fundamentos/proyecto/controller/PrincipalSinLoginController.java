package com.fundamentos.proyecto.controller;

import com.fundamentos.proyecto.util.CambiaEscenas;
import com.fundamentos.proyecto.util.DBConnection;
import com.fundamentos.proyecto.util.UserSession;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.sql.Connection;

public class PrincipalSinLoginController {

    CambiaEscenas cambia = new CambiaEscenas();

    @FXML private void busqueda(ActionEvent event) {
        cambia.cambiarEscena(event, "/view/busqueda.fxml");
    }


    @FXML private void Publicar(ActionEvent event) {
        UserSession session = UserSession.getInstance();
        cambia.cambiarEscena(event, "/view/crearPublicacion.fxml");
    }

    @FXML
    private void Mensajes(ActionEvent event) {
        UserSession session = UserSession.getInstance();
        if (session != null) {
            try {
                // Aquí obtienes la conexión
                Connection conexion = DBConnection.getConnection();

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/chat.fxml"));
                Parent root = loader.load();

                // Obtienes el controlador real de la pantalla de chat
                ChatController controller = loader.getController();

                int usuarioActualId = UserSession.getInstance().getUserId();

                controller.initData(-1, usuarioActualId, -1, conexion);

                // Muestras la nueva escena
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            mostrarAlerta(Alert.AlertType.ERROR, "No hay usuario en sesión");
        }
    }



    @FXML private void MisPublicaciones(ActionEvent event) {
        cambia.cambiarEscena(event, "/view/mis_publicaciones.fxml");
    }

    @FXML
    private void Perfil(ActionEvent event) {
        UserSession session = UserSession.getInstance();
        if (session != null) {
            mostrarAlerta(Alert.AlertType.INFORMATION, "Usuario logueado: " + session.getNombre() + " " + session.getApellido()
                    +  ", correo: " + session.getCorreo());
        } else {
            mostrarAlerta(Alert.AlertType.ERROR, "No hay usuario en sesión");
        }
    }


    private void mostrarAlerta(Alert.AlertType tipo, String mensaje) {
        Alert alert = new Alert(tipo);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
