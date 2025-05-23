package com.fundamentos.proyecto.controller;

import com.fundamentos.proyecto.model.Usuario;
import com.fundamentos.proyecto.dao.UsuarioDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class PerfilController {

    @FXML public Label nombreLabel;
    @FXML public Label correoLabel;
    @FXML public Label telefonoLabel;

    private Usuario usuario;

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
        cargarDatosPerfil();
    }

    public void cargarDatosPerfil() {
        if (usuario != null) {
            nombreLabel.setText(usuario.getNombre() + " " + usuario.getApellido());
            correoLabel.setText(usuario.getCorreo());
            telefonoLabel.setText(String.valueOf(usuario.getCedula()));
        }
    }

    public void actualizarContrasena(String nuevaContrasena) {
        boolean actualizado = UsuarioDAO.cambiarContrasena(usuario.getCorreo(), nuevaContrasena);
        if (actualizado) {
            System.out.println("Contraseña actualizada correctamente.");
        } else {
            System.out.println("Error al actualizar contraseña.");
        }
    }
}


