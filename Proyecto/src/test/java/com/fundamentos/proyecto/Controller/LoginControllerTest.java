package com.fundamentos.proyecto.Controller;

import com.fundamentos.proyecto.model.Usuario;
import com.fundamentos.proyecto.services.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LoginControllerTest {

    private UsuarioService usuarioService;

    @BeforeEach
    void setUp() {
        usuarioService = new UsuarioService();
    }

    @Test
    void testLoginJuanVargas() {
        Usuario usuario = usuarioService.login("juan@gmail.com", "123");

        assertNotNull(usuario, "El usuario Juan Vargas debería existir");
        assertEquals("juan@gmail.com", usuario.getCorreo());
    }





}