package com.fundamentos.proyecto.Controller;

import com.fundamentos.proyecto.controller.EditarPublicacionController;
import com.fundamentos.proyecto.model.Inmueble;
import com.fundamentos.proyecto.model.Publicacion;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EditarPublicacionControllerTest {

    private EditarPublicacionController controller;
    private Inmueble inmueble;
    private Publicacion publicacion;

    @BeforeEach
    void setUp() {
        controller = new EditarPublicacionController();

        // Simula los controles FXML manualmente
        controller.txtDireccion = new TextField();
        controller.txtHabitaciones = new TextField();
        controller.txtBanos = new TextField();
        controller.txtArea = new TextField();
        controller.txtPrecio = new TextField();
        controller.comboTipo = new ComboBox<>();
        controller.comboEstado = new ComboBox<>();
        controller.comboEstrato = new ComboBox<>();

        inmueble = new Inmueble();
        inmueble.setDireccion("Calle 123");
        inmueble.setHabitaciones(3);
        inmueble.setBanos(2);
        inmueble.setArea(95.5);
        inmueble.setPrecio(new java.math.BigDecimal("350000000"));
        inmueble.setTipo("apartamento");
        inmueble.setEstado("usado");
        inmueble.setEstrato(4);

        publicacion = new Publicacion();
    }

    @Test
    void testSetData() {
        controller.setData(inmueble, publicacion);

        assertEquals("Calle 123", controller.txtDireccion.getText());
        assertEquals("3", controller.txtHabitaciones.getText());
        assertEquals("2", controller.txtBanos.getText());
        assertEquals("95.5", controller.txtArea.getText());
        assertEquals("350000000", controller.txtPrecio.getText());
        assertEquals("apartamento", controller.comboTipo.getValue());
        assertEquals("usado", controller.comboEstado.getValue());
        assertEquals("4", controller.comboEstrato.getValue());
    }
}
