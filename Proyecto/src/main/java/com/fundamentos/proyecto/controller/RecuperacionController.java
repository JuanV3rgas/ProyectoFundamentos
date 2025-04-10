package com.fundamentos.proyecto.controller;

import com.fundamentos.proyecto.dao.UsuarioDAO;
import com.fundamentos.proyecto.model.Usuario;
import com.fundamentos.proyecto.services.UsuarioService;
import com.fundamentos.proyecto.util.CambiaEscenas;
import com.fundamentos.proyecto.util.UserSession;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;


public class RecuperacionController {
    @FXML
    private TextField txtCorreo;
    @FXML
    private TextField txtNuevaContrasena;

    CambiaEscenas cambia = new CambiaEscenas();
    private UsuarioService usuarioService = new UsuarioService();

    @FXML
    private void cambiarContrasena(ActionEvent event) {
        String correo = txtCorreo.getText();
        String contrasena = txtNuevaContrasena.getText();

        boolean cambiarContra = usuarioService.cambiarContrasena(correo, contrasena);
        if(cambiarContra) {
            Usuario usuarioActualizado = usuarioService.RegistroSession(correo);
            UserSession.createSession(
                    usuarioActualizado.getId(),
                    usuarioActualizado.getNombre(),
                    usuarioActualizado.getApellido(),
                    usuarioActualizado.getCedula(),
                    usuarioActualizado.getCorreo(),
                    usuarioActualizado.getContrasena(),
                    usuarioActualizado.getPregunta_seguridad(),
                    usuarioActualizado.getRespuesta_pregunta_seguridad(),
                    usuarioActualizado.getNumero_telefonico()
            );
            mostrarAlerta(Alert.AlertType.INFORMATION, "Registro exitoso");
            cambia.cambiarEscena(event, "/view/principal_sin_login.fxml");
        }
        else {
            mostrarAlerta(Alert.AlertType.ERROR, "Correo no encontrado, inténtelo nuevamente");
            limpiarCampos();
        }
    }




    private void mostrarAlerta(Alert.AlertType tipo, String mensaje) {
        Alert alert = new Alert(tipo);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void limpiarCampos(){
        txtNuevaContrasena.clear();
        txtCorreo.clear( );
    }

}
