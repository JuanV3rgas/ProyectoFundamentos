package com.fundamentos.proyecto.controller;

import com.fundamentos.proyecto.model.Inmueble;
import com.fundamentos.proyecto.services.InmuebleService;
import com.fundamentos.proyecto.util.CambiaEscenas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import java.math.BigDecimal;
import java.util.List;

public class BusquedaController {

    @FXML private ComboBox<String> comboTipo;
    @FXML private ComboBox<String> comboEstado;
    @FXML private TextField txtEstratoMin;
    @FXML private TextField txtEstratoMax;
    @FXML private TextField txtAreaMin;
    @FXML private TextField txtAreaMax;
    @FXML private TextField txtPrecioMin;
    @FXML private TextField txtPrecioMax;

    private InmuebleService inmuebleService = new InmuebleService();
    private CambiaEscenas cambia = new CambiaEscenas();

    @FXML
    public void initialize() {
        // Llena el comboTipo con opciones
        comboTipo.getItems().addAll("casa", "apartamento", "local");
        comboEstado.getItems().addAll("nueva", "usada", "Obra Gris", "Obra Negra");
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

        List<Inmueble> resultados = inmuebleService.filtrarInmuebles(tipo, estado, estratoMin, estratoMax,
                areaMin, areaMax, precioMin, precioMax);

        // Cambia a la pantalla de resultados pasando los resultados
        cambia.cambiarEscenaConResultados("/view/resultados.fxml", resultados);
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
}
