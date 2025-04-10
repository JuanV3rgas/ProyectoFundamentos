package com.fundamentos.proyecto.controller;

import com.fundamentos.proyecto.model.Inmueble;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.ByteArrayInputStream;
import java.math.BigDecimal;
import java.util.List;

public class ResultadosController {

    @FXML private TableView<Inmueble> tablaInmuebles;
    @FXML private TableColumn<Inmueble, String> colTipo;
    @FXML private TableColumn<Inmueble, Integer> colEstrato;
    @FXML private TableColumn<Inmueble, Double> colArea;
    @FXML private TableColumn<Inmueble, String> colDireccion;
    @FXML private TableColumn<Inmueble, BigDecimal> colPrecio;
    @FXML private TableColumn<Inmueble, byte[]> colImagen; // Nueva columna para la imagen

    @FXML
    public void initialize() {
        colTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        colEstrato.setCellValueFactory(new PropertyValueFactory<>("estrato"));
        colArea.setCellValueFactory(new PropertyValueFactory<>("area"));
        colDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));


        colImagen.setCellValueFactory(new PropertyValueFactory<>("imagen1"));
        colImagen.setCellFactory(column -> new TableCell<Inmueble, byte[]>() {
            private final ImageView imageView = new ImageView();

            {
                imageView.setFitWidth(200);
                imageView.setFitHeight(150);
                imageView.setPreserveRatio(true);
            }

            @Override
            protected void updateItem(byte[] item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setGraphic(null);
                } else {
                    Image image = new Image(new ByteArrayInputStream(item));
                    imageView.setImage(image);
                    setGraphic(imageView);
                }
            }
        });
    }

    public void setResultados(List<Inmueble> resultados) {
        tablaInmuebles.setItems(FXCollections.observableArrayList(resultados));
    }
}
