package com.fundamentos.proyecto.controller;

import com.fundamentos.proyecto.dao.UsuarioDAO;
import com.fundamentos.proyecto.model.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BusquedaControllerTest {

    private BusquedaController controller;

    @BeforeEach
    void setUp() {
        controller = new BusquedaController();

        
        Usuario usuario = new Usuario(
                1, "Carlos", "Pérez", 1122334455, "carlos@email.com",
                "password", "Color favorito", "Rojo", "3216549870"
        );
        UsuarioDAO.insertarUsuario(usuario);
    }

    @Test
    void testBuscarUsuarioExistente() {
        Usuario usuario = UsuarioDAO.obtenerUsuarioPorCedula(1122334455);
        assertNotNull(usuario);
        assertEquals("Carlos", usuario.getNombre());
        assertEquals("carlos@email.com", usuario.getCorreo());
    }

    @Test
    void testBuscarUsuarioInexistente() {
        Usuario usuario = UsuarioDAO.obtenerUsuarioPorCedula(999999999);
        assertNull(usuario);
    }
}