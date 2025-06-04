package com.fundamentos.proyecto.controller;

import com.fundamentos.proyecto.model.PublicacionInmueble;
import com.fundamentos.proyecto.util.CambiaEscenas;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Callback;
import java.io.ByteArrayInputStream;
import java.util.List;

public class ResultadosController {

    @FXML private TableView<PublicacionInmueble> tablaInmuebles;
    @FXML private TableColumn<PublicacionInmueble, String> colTipo;
    @FXML private TableColumn<PublicacionInmueble, Integer> colEstrato;
    @FXML private TableColumn<PublicacionInmueble, Double> colArea;
    @FXML private TableColumn<PublicacionInmueble, String> colDireccion;
    @FXML private TableColumn<PublicacionInmueble, Object> colPrecio;
    @FXML private TableColumn<PublicacionInmueble, byte[]> colImagen;
    @FXML private TableColumn<PublicacionInmueble, Integer> colHabitaciones;
    @FXML private TableColumn<PublicacionInmueble, Integer> colBanos;
    @FXML private TableColumn<PublicacionInmueble, Void> colDetalles;

    private CambiaEscenas cambia = new CambiaEscenas();

    @FXML
    public void initialize() {
        colTipo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getInmueble().getTipo()));
        colEstrato.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getInmueble().getEstrato()).asObject());
        colArea.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getInmueble().getArea()));
        colDireccion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getInmueble().getDireccion()));
        colPrecio.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getInmueble().getPrecio()));
        colImagen.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getInmueble().getImagen1()));
        colHabitaciones.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getInmueble().getHabitaciones()).asObject());
        colBanos.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getInmueble().getBanos()).asObject());

        colImagen.setCellFactory(column -> new TableCell<PublicacionInmueble, byte[]>() {
            private final ImageView imageView = new ImageView();
            {
                imageView.setFitWidth(80);
                imageView.setFitHeight(60);
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

        addDetallesButtonToTable();
    }

    public void setResultados(List<PublicacionInmueble> resultados) {
        tablaInmuebles.setItems(FXCollections.observableArrayList(resultados));
    }

    private void addDetallesButtonToTable() {
        Callback<TableColumn<PublicacionInmueble, Void>, TableCell<PublicacionInmueble, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<PublicacionInmueble, Void> call(final TableColumn<PublicacionInmueble, Void> param) {
                final TableCell<PublicacionInmueble, Void> cell = new TableCell<>() {
                    private final Button btn = new Button("Ver detalles");
                    {
                        btn.setOnAction((event) -> {
                            PublicacionInmueble pubIm = getTableView().getItems().get(getIndex());
                            mostrarVentanaDetalles(pubIm, event);
                        });
                        btn.setStyle("-fx-background-color: #363432; -fx-text-fill: #fff176; -fx-font-size: 12px;");
                    }
                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        setGraphic(empty ? null : btn);
                    }
                };
                return cell;
            }
        };
        colDetalles.setCellFactory(cellFactory);
    }

    private void mostrarVentanaDetalles(PublicacionInmueble publicacionInmueble, javafx.event.ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/publicacion.fxml"));
            Parent root = loader.load();
            PublicacionController controller = loader.getController();
            controller.setData(publicacionInmueble.getInmueble(), publicacionInmueble.getPublicacion());

            // Cambia la escena en el stage actual
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Detalle de Inmueble");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void back(ActionEvent event) {
        cambia.cambiarEscena(event, "/view/busqueda.fxml");
    }

}
