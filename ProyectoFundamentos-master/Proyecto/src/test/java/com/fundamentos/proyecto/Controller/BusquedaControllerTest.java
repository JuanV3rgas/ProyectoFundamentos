package com.fundamentos.proyecto.Controller;

import com.fundamentos.proyecto.controller.PerfilController;
import com.fundamentos.proyecto.dao.UsuarioDAO;
import com.fundamentos.proyecto.model.Usuario;
import javafx.scene.control.Label;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PerfilControllerTest {

    private PerfilController controller;

    @BeforeEach
    void setUp() {
        controller = new PerfilController();

        // Hacer públicas o simular los Labels
        controller.nombreLabel = new Label();
        controller.correoLabel = new Label();
        controller.telefonoLabel = new Label();

        // Cédula corregida como entero
        Usuario usuario = new Usuario(
                1, "Laura", "Gómez", 123456789, "laura@email.com",
                "clave123", "Color favorito", "Azul", "3123456789"
        );

        controller.setUsuario(usuario);
    }

    @Test
    void testCargarDatosPerfil() {
        assertEquals("Laura Gómez", controller.nombreLabel.getText());
        assertEquals("laura@email.com", controller.correoLabel.getText());
        assertEquals("3123456789", controller.telefonoLabel.getText());
    }
    @Test
    void testActualizarContrasena() {
        // Simula que el correo ya fue seteado en el usuario actual
        Usuario usuario = new Usuario(
                1, "Laura", "Gómez", 123456789, "correo@ejemplo.com",
                "vieja123", "Color favorito", "Azul", "3123456789"
        );
        controller.setUsuario(usuario);
        // Probar el cambio de contraseña
        boolean resultado = UsuarioDAO.cambiarContrasena("correo@ejemplo.com", "nuevaClave123");
        // Ejecutar el metodo del controlador (Solo imprime el mensaje)
        controller.actualizarContrasena("nuevaClave123");
        // Validar resultado
        assertTrue(resultado, "La contraseña debio ser actualizado");
    }

}

