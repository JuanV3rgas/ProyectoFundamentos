package com.fundamentos.proyecto.Controller;

import com.fundamentos.proyecto.services.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VerificacionControllerTest {

    private UsuarioService usuarioService;

    @BeforeEach
    void setUp() {
        usuarioService = new UsuarioService();
    }

    @Test
    void testVerificacionExitosaJuan() {
        String correo = "juan@gmail.com";
        String pregunta = "nombre de su primera mascota";
        String respuesta = "Emilio";

        boolean resultado = usuarioService.validarRecuperacion(correo, pregunta, respuesta);

        assertTrue(resultado, "Juan debería pasar la verificación con sus datos correctos");
    }


}
