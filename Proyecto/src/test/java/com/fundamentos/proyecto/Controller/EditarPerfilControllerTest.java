package com.fundamentos.proyecto.Controller;
import com.fundamentos.proyecto.controller.EditarPerfilController;
import com.fundamentos.proyecto.model.Usuario;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EditarPerfilControllerTest {

    private EditarPerfilController controller;
    private Usuario usuario;

    @BeforeEach
    void setUp() {
        controller = new EditarPerfilController();

        controller.nombreField = new TextField("Carlos");
        controller.apellidoField = new TextField("Ramírez");
        controller.telefonoField = new TextField("3001112233");

        usuario = new Usuario(1, "Carlos", "Ramírez", 123456789, "carlos@email.com",
                "clave123", "Color", "Azul", "3001112233");

        controller.setUsuario(usuario);
    }

    @Test
    void testActualizarPerfil() {
        controller.nombreField.setText("Juan");
        controller.apellidoField.setText("Martínez");
        controller.telefonoField.setText("3119998877");

        controller.actualizarPerfil();

        assertEquals("Juan", usuario.getNombre());
        assertEquals("Martínez", usuario.getApellido());
        assertEquals("3119998877", usuario.getTelefono());
    }
}
