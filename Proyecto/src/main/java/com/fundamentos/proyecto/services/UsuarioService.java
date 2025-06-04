package com.fundamentos.proyecto.services;

import com.fundamentos.proyecto.dao.UsuarioDAO;
import com.fundamentos.proyecto.model.Usuario;

public class UsuarioService {

    public Usuario login(String correo, String contrasena) {
        if (correo == null || contrasena == null || correo.trim().isEmpty() || contrasena.trim().isEmpty()) {
            return null; // O lanzar una excepción de validación
        }

        return UsuarioDAO.obtenerUsuario(correo, contrasena);
    }


    public static boolean registrarUsuario(String nombre, String apellido, String cedula,
                                           String correo, String contrasena,
                                           String pregunta, String respuesta,
                                           String celular) {
        return UsuarioDAO.crearUsuario(nombre, apellido, cedula, correo, contrasena, pregunta, respuesta, celular);
    }

    public static Usuario RegistroSession(String correo) {
        if (correo == null || correo.trim().isEmpty()) {
            return null; // O lanzar una excepción de validación
        }

        return UsuarioDAO.obtenerUsuarioCorreo(correo);
    }


    public boolean cambiarContrasena(String correo, String nuevaContrasena) {
        if (correo == null || nuevaContrasena == null ||
                correo.trim().isEmpty() || nuevaContrasena.trim().isEmpty()) {
            return false;
        }
        return UsuarioDAO.cambiarContrasena(correo, nuevaContrasena);
    }

    public boolean validarRecuperacion(String correo, String pregunta, String respuesta) {
        if (correo == null || pregunta == null || respuesta == null ||
                correo.trim().isEmpty() || pregunta.trim().isEmpty() || respuesta.trim().isEmpty()) {
            return false;
        }
        return UsuarioDAO.validarRecuperacion(correo, pregunta, respuesta);
    }
    public Usuario obtenerUsuarioPorId(int id) {
        if (id <= 0) {
            return null;
        }
        return UsuarioDAO.obtenerUsuarioPorId(id);
    }
}
