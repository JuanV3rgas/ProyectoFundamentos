package com.fundamentos.proyecto.controller;

import com.fundamentos.proyecto.model.Usuario;
import com.fundamentos.proyecto.services.UsuarioService;
import com.fundamentos.proyecto.util.CambiaEscenas;
import com.fundamentos.proyecto.util.UserSession;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML private TextField txtCorreo;
    @FXML private PasswordField txtContrasena;

    private CambiaEscenas cambia = new CambiaEscenas();

    private UsuarioService usuarioService = new UsuarioService();


    @FXML
    private void iniciarSesion(ActionEvent event) {
        String correo = txtCorreo.getText();
        String contrasena = txtContrasena.getText();


        Usuario usuarioSesion = usuarioService.login(correo, contrasena);

        if (usuarioSesion != null) {
            mostrarAlerta(Alert.AlertType.INFORMATION, "Inicio de sesión exitoso");
            UserSession.createSession(
                    usuarioSesion.getId(),
                    usuarioSesion.getNombre(),
                    usuarioSesion.getApellido(),
                    usuarioSesion.getCedula(),
                    usuarioSesion.getCorreo(),
                    usuarioSesion.getContrasena(),
                    usuarioSesion.getPregunta_seguridad(),
                    usuarioSesion.getRespuesta_pregunta_seguridad(),
                    usuarioSesion.getNumero_telefonico()
            );
            System.out.println("Usuario logueado: " + usuarioSesion.getNombre()
                    + " " + usuarioSesion.getApellido()
                    + ", ID: " + usuarioSesion.getId()
                    + ", correo: " + usuarioSesion.getCorreo());
            cambia.cambiarEscena(event, "/view/principal_sin_login.fxml");
        } else {
            mostrarAlerta(Alert.AlertType.ERROR, "Correo o contraseña incorrectos");
            limpiarCampos();
        }
    }

    @FXML
    private void mostrarRegistro(ActionEvent event) {
        cambia.cambiarEscena(event, "/view/registro.fxml");
    }

    @FXML
    private void olvidasteContrasena(ActionEvent event) {
        cambia.cambiarEscena(event, "/view/verificacion.fxml");
    }

    private void limpiarCampos(){
        txtCorreo.clear();
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
