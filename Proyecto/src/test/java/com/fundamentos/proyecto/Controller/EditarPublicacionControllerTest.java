package com.fundamentos.proyecto.controller;

import com.fundamentos.proyecto.controller.EditarPublicacionController;
import com.fundamentos.proyecto.model.Publicacion;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class EditarPublicacionControllerTest {

    private EditarPublicacionController controller;
    private Publicacion publicacion;

    @BeforeEach
    void setUp() {
        controller = new EditarPublicacionController();

        controller.tituloField = new TextField("Título original");
        controller.descripcionArea = new TextArea("Descripción original");

        publicacion = new Publicacion(1, "Título original", "Descripción original", new BigDecimal("100.00"), "Categoria", 1);
        controller.setPublicacion(publicacion);
    }

    @Test
    void testActualizarPublicacion() {
        controller.tituloField.setText("Nuevo título");
        controller.descripcionArea.setText("Nueva descripción");

        controller.actualizarPublicacion();

        assertEquals("Nuevo título", publicacion.getTitulo());
        assertEquals("Nueva descripción", publicacion.getDescripcion());
    }
}
