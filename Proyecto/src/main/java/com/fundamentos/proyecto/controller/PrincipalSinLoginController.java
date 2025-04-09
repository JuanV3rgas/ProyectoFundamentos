package com.fundamentos.proyecto.controller;

import com.fundamentos.proyecto.util.CambiaEscenas;
import com.fundamentos.proyecto.util.UserSession;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.event.ActionEvent;

public class PrincipalSinLoginController {

    CambiaEscenas cambia = new CambiaEscenas();

    @FXML private void busqueda(ActionEvent event) {

    }


    @FXML private void Publicar(ActionEvent event) {
        UserSession session = UserSession.getInstance();
        cambia.cambiarEscena(event, "/view/crearPublicacion.fxml");
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
