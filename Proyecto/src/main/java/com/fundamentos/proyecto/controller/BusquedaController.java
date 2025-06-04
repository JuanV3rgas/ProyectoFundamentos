package com.fundamentos.proyecto.controller;

import com.fundamentos.proyecto.model.Inmueble;
import com.fundamentos.proyecto.model.PublicacionInmueble;
import com.fundamentos.proyecto.services.InmuebleService;
import com.fundamentos.proyecto.services.PublicacionInmuebleService;
import com.fundamentos.proyecto.util.CambiaEscenas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import java.math.BigDecimal;
import java.util.List;

//controlador de busqueda

public class BusquedaController {

    @FXML
    private ComboBox<String> comboTipo;
    @FXML
    private ComboBox<String> comboEstado;
    @FXML
    private TextField txtEstratoMin;
    @FXML
    private TextField txtEstratoMax;
    @FXML
    private TextField txtAreaMin;
    @FXML
    private TextField txtAreaMax;
    @FXML
    private TextField txtPrecioMin;
    @FXML
    private TextField txtPrecioMax;
    @FXML
    private TextField txtHabitaciones;
    @FXML
    private TextField txtBanos;


    private CambiaEscenas cambia = new CambiaEscenas();

    private PublicacionInmuebleService publicacionInmuebleService = new PublicacionInmuebleService();

    @FXML
    public void initialize() {
        // Llena el comboTipo con opciones
        comboTipo.getItems().addAll("casa", "apartamento", "local");
        comboEstado.getItems().addAll("nueva", "usada", "obra gris", "obra negra");
    }

    @FXML
    private void buscarAvanzada(ActionEvent event) {
        String tipo = comboTipo.getValue();
        String estado = comboEstado.getValue();
        Integer estratoMin = parseInteger(txtEstratoMin.getText());
        Integer estratoMax = parseInteger(txtEstratoMax.getText());
        Double areaMin = parseDouble(txtAreaMin.getText());
        Double areaMax = parseDouble(txtAreaMax.getText());
        BigDecimal precioMin = parseBigDecimal(txtPrecioMin.getText());
        BigDecimal precioMax = parseBigDecimal(txtPrecioMax.getText());
        Integer habitaciones = parseInteger(txtHabitaciones.getText());
        Integer banos = parseInteger(txtBanos.getText());


        List<PublicacionInmueble> resultados = publicacionInmuebleService.filtrarPublicacionInmuebles(
                tipo, estado, estratoMin, estratoMax,
                areaMin, areaMax, precioMin, precioMax, habitaciones, banos);
        cambia.cambiarEscenaConResultados(event, "/view/resultados.fxml", resultados);
    }

    private Integer parseInteger(String txt) {
        try {
            if (txt == null || txt.trim().isEmpty()) return null;
            return Integer.parseInt(txt.trim());
        } catch (NumberFormatException ex) {
            return null;
        }
    }

    private Double parseDouble(String txt) {
        try {
            if (txt == null || txt.trim().isEmpty()) return null;
            return Double.parseDouble(txt.trim());
        } catch (NumberFormatException ex) {
            return null;
        }
    }

    private BigDecimal parseBigDecimal(String txt) {
        try {
            if (txt == null || txt.trim().isEmpty()) return null;
            return new BigDecimal(txt.trim());
        } catch (NumberFormatException ex) {
            return null;
        }
    }

    @FXML
    private void back(ActionEvent event) {
        cambia.cambiarEscena(event, "/view/principal_sin_login.fxml");
    }

}
