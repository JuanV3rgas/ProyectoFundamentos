package com.fundamentos.proyecto.Controller;

import com.fundamentos.proyecto.controller.BusquedaController;
import com.fundamentos.proyecto.services.PublicacionInmuebleService;
import com.fundamentos.proyecto.util.CambiaEscenas;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BusquedaControllerTest {

    private BusquedaController controller;

    @BeforeEach
    void setUp() {
        controller = new BusquedaController();

        // Usar los servicios reales
        controller.publicacionInmuebleService = new PublicacionInmuebleService();
        controller.cambia = new CambiaEscenas();

        // Instanciar los controles FXML
        controller.comboTipo = new ComboBox<>();
        controller.comboEstado = new ComboBox<>();
        controller.txtEstratoMin = new TextField();
        controller.txtEstratoMax = new TextField();
        controller.txtAreaMin = new TextField();
        controller.txtAreaMax = new TextField();
        controller.txtPrecioMin = new TextField();
        controller.txtPrecioMax = new TextField();
        controller.txtHabitaciones = new TextField();
        controller.txtBanos = new TextField();

        // Asignar valores para la búsqueda
        controller.comboTipo.setValue("casa");            // Cambia si tienes valores diferentes en tu BD
        controller.comboEstado.setValue("usada");
        controller.txtEstratoMin.setText("1");
        controller.txtEstratoMax.setText("6");
        controller.txtAreaMin.setText("30");
        controller.txtAreaMax.setText("300");
        controller.txtPrecioMin.setText("50000000");
        controller.txtPrecioMax.setText("900000000");
        controller.txtHabitaciones.setText("1");
        controller.txtBanos.setText("1");
    }

    @Test
    void testBuscarAvanzadaConServiciosReales() {
        ActionEvent fakeEvent = new ActionEvent();


        assertDoesNotThrow(() -> controller.buscarAvanzada(fakeEvent),
                "El método buscarAvanzada debe ejecutarse sin lanzar excepciones usando servicios reales");
    }
}
