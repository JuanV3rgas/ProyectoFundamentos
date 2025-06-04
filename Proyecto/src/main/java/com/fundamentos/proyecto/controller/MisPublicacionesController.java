package com.fundamentos.proyecto.controller;

import com.fundamentos.proyecto.dao.PublicacionDAO;
import com.fundamentos.proyecto.dao.PublicacionInmuebleDAO;
import com.fundamentos.proyecto.model.PublicacionInmueble;
import com.fundamentos.proyecto.util.CambiaEscenas;
import com.fundamentos.proyecto.util.UserSession;
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
import java.io.ByteArrayInputStream;
import java.util.List;

public class MisPublicacionesController {

    @FXML private TableView<PublicacionInmueble> tablaInmuebles;
    @FXML private TableColumn<PublicacionInmueble, String> colTipo;
    @FXML private TableColumn<PublicacionInmueble, String> colDireccion;
    @FXML private TableColumn<PublicacionInmueble, Object> colPrecio;
    @FXML private TableColumn<PublicacionInmueble, byte[]> colImagen;
    @FXML private TableColumn<PublicacionInmueble, Void> colDetalles;
    @FXML private TableColumn<PublicacionInmueble, Void> colEditar;
    @FXML private TableColumn<PublicacionInmueble, Void> colEliminar;

    private CambiaEscenas cambia = new CambiaEscenas();

    @FXML
    public void initialize() {
        colTipo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getInmueble().getTipo()));
        colDireccion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getInmueble().getDireccion()));
        colPrecio.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getInmueble().getPrecio()));

        colImagen.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getInmueble().getImagen1()));
        colImagen.setCellFactory(column -> new TableCell<>() {
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
                    setGraphic(new ImageView(new Image(new ByteArrayInputStream(item))));
                }
            }
        });

        int idUsuario = UserSession.getInstance().getUserId();

        List<PublicacionInmueble> misPublicaciones = PublicacionInmuebleDAO.obtenerPublicacionesPorUsuario(idUsuario);

        // 3. Llena la tabla
        setResultados(misPublicaciones);

        addButtonToDetalles();
        addButtonToEditar();
        addButtonToEliminar();
    }

    public void setResultados(List<PublicacionInmueble> resultados) {
        tablaInmuebles.setItems(FXCollections.observableArrayList(resultados));
    }

    // Botón Detalles
    private void addButtonToDetalles() {
        colDetalles.setCellFactory(param -> new TableCell<>() {
            private final Button btn = new Button("Ver detalles");
            {
                btn.setStyle("-fx-background-color: #363432; -fx-text-fill: #fff176; -fx-font-size: 12px;");
                btn.setOnAction(event -> {
                    PublicacionInmueble pubIm = getTableView().getItems().get(getIndex());
                    mostrarVentanaDetalles(pubIm, event);
                });
            }
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : btn);
            }
        });
    }

    // Botón Editar
    private void addButtonToEditar() {
        colEditar.setCellFactory(param -> new TableCell<>() {
            private final Button btn = new Button("Editar");
            {
                btn.setStyle("-fx-background-color: #363432; -fx-text-fill: #fff176; -fx-font-size: 12px;");
                btn.setOnAction(event -> {
                    PublicacionInmueble pubIm = getTableView().getItems().get(getIndex());
                    mostrarVentanaEditar(pubIm, event);
                });
            }
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : btn);
            }
        });
    }

    // Botón Eliminar
    private void addButtonToEliminar() {
        colEliminar.setCellFactory(param -> new TableCell<>() {
            private final Button btn = new Button("Eliminar");
            {
                btn.setStyle("-fx-background-color: #b71c1c; -fx-text-fill: white; -fx-font-size: 12px;");
                btn.setOnAction(event -> {
                    PublicacionInmueble pubIm = getTableView().getItems().get(getIndex());
                    eliminarPublicacion(pubIm);
                });
            }
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : btn);
            }
        });
    }

    private void mostrarVentanaDetalles(PublicacionInmueble publicacionInmueble, ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/publicacion.fxml"));
            Parent root = loader.load();
            PublicacionController controller = loader.getController();
            controller.setData(publicacionInmueble.getInmueble(), publicacionInmueble.getPublicacion());
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Detalle de Inmueble");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void mostrarVentanaEditar(PublicacionInmueble publicacionInmueble, ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/editarPublicacion.fxml"));
            Parent root = loader.load();
            EditarPublicacionController controller = loader.getController();
            controller.setData(publicacionInmueble.getInmueble(), publicacionInmueble.getPublicacion());
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Editar Inmueble");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void eliminarPublicacion(PublicacionInmueble publicacionInmueble) {
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle("Eliminar Publicación");
        alerta.setHeaderText("¿Estás seguro de eliminar esta publicación?");
        alerta.setContentText("Esta acción no se puede deshacer.");

        alerta.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                boolean exito = PublicacionDAO.eliminarPublicacionPorId(publicacionInmueble.getPublicacion().getId());
                if (exito) {
                    tablaInmuebles.getItems().remove(publicacionInmueble);
                    Alert info = new Alert(Alert.AlertType.INFORMATION, "Publicación eliminada correctamente.", ButtonType.OK);
                    info.showAndWait();
                } else {
                    Alert error = new Alert(Alert.AlertType.ERROR, "No se pudo eliminar la publicación.", ButtonType.OK);
                    error.showAndWait();
                }
            }
        });
    }

    @FXML
    private void back(ActionEvent event) {
        cambia.cambiarEscena(event, "/view/principal_sin_login.fxml");
    }
}
