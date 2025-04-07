package com.fundamentos.proyecto.controller;

import com.fundamentos.proyecto.dao.UsuarioDAO;
import com.fundamentos.proyecto.util.CambiaEscenas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class LoginController {

    @FXML private TextField txtCorreo;
    @FXML private PasswordField txtContrasena;
    CambiaEscenas cambia = new CambiaEscenas();

    // Este metodo se invoca cuando el usuario hace clic en "Iniciar sesión"
    @FXML
    private void iniciarSesion(ActionEvent event) {
        String correo = txtCorreo.getText();
        String contrasena = txtContrasena.getText();

        boolean usuarioValido = UsuarioDAO.validarUsuario(correo, contrasena);

        if(usuarioValido) {
            mostrarAlerta(Alert.AlertType.INFORMATION, "Inicio de sesión exitoso");
            cambia.cambiarEscena(event, "/view/principal_sin_login.fxml");
        } else {
            mostrarAlerta(Alert.AlertType.ERROR, "Datos incorrectos, inténtelo nuevamente");
            limpiarCampos();
        }
    }

    @FXML
    private void mostrarRegistro(ActionEvent event) {
        cambia.cambiarEscena(event, "/view/registro.fxml");
    }

    @FXML
    private void olvidasteContrasena(ActionEvent event) {
        cambia.cambiarEscena(event, "/view/olvidasteContrasena.fxml");
    }

    private void limpiarCampos(){
        txtContrasena.clear();
    }

    private void mostrarAlerta(Alert.AlertType tipo, String mensaje) {
        Alert alert = new Alert(tipo);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
