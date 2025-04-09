package com.fundamentos.proyecto.controller;

import com.fundamentos.proyecto.model.Inmueble;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.math.BigDecimal;
import java.util.List;

public class ResultadosController {

    @FXML private TableView<Inmueble> tablaInmuebles;
    @FXML private TableColumn<Inmueble, String> colTipo;
    @FXML private TableColumn<Inmueble, Integer> colEstrato;
    @FXML private TableColumn<Inmueble, Double> colArea;
    @FXML private TableColumn<Inmueble, String> colDireccion;
    @FXML private TableColumn<Inmueble, BigDecimal> colPrecio;

    @FXML
    public void initialize() {
        colTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        colEstrato.setCellValueFactory(new PropertyValueFactory<>("estrato"));
        colArea.setCellValueFactory(new PropertyValueFactory<>("area"));
        colDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
    }

    public void setResultados(List<Inmueble> resultados) {
        tablaInmuebles.setItems(FXCollections.observableArrayList(resultados));
    }
}
