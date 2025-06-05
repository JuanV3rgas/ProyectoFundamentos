package com.fundamentos.proyecto.controller;

import com.fundamentos.proyecto.model.Inmueble;
import com.fundamentos.proyecto.model.Publicacion;
import com.fundamentos.proyecto.dao.InmuebleDAO;
import com.fundamentos.proyecto.util.CambiaEscenas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class EditarPublicacionController {

    @FXML
    public TextField txtDireccion;
    @FXML
    public TextField txtHabitaciones;
    @FXML
    public TextField txtBanos;
    @FXML
    public TextField txtArea;
    @FXML
    public TextField txtPrecio;
    @FXML
    public ComboBox<String> comboTipo;
    @FXML
    public ComboBox<String> comboEstado;
    @FXML
    public ComboBox<String> comboEstrato;
    private byte[] imagen1;

    private Inmueble inmuebleActual;
    private Publicacion publicacionActual;

    private CambiaEscenas cambia = new CambiaEscenas();

    @FXML
    public void initialize() {
        comboTipo.getItems().addAll("casa", "apartamento", "local");
        comboEstado.getItems().addAll("nuevo", "usado", "obra gris", "obra negra");
        comboEstrato.getItems().addAll("1", "2", "3", "4", "5", "6");
    }

    public void setData(Inmueble inmueble, Publicacion publicacion) {
        this.inmuebleActual = inmueble;
        this.publicacionActual = publicacion;

        txtDireccion.setText(inmueble.getDireccion());
        txtHabitaciones.setText(String.valueOf(inmueble.getHabitaciones()));
        txtBanos.setText(String.valueOf(inmueble.getBanos()));
        txtArea.setText(String.valueOf(inmueble.getArea()));
        txtPrecio.setText(String.valueOf(inmueble.getPrecio()));
        comboTipo.setValue(inmueble.getTipo());
        comboEstado.setValue(inmueble.getEstado());
        comboEstrato.setValue(String.valueOf(inmueble.getEstrato()));

    }

    @FXML
    private byte[] seleccionarImagen() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar imagen");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Imágenes", "*.png", "*.jpg", "*.jpeg")
        );
        File archivo = fileChooser.showOpenDialog(null); // O el Stage actual
        if (archivo != null) {
            try {
                return Files.readAllBytes(archivo.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @FXML
    private void guardarCambios(ActionEvent event) {
        inmuebleActual.setDireccion(txtDireccion.getText());
        inmuebleActual.setHabitaciones(Integer.parseInt(txtHabitaciones.getText()));
        inmuebleActual.setBanos(Integer.parseInt(txtBanos.getText()));
        inmuebleActual.setArea(Double.parseDouble(txtArea.getText()));
        inmuebleActual.setPrecio(new java.math.BigDecimal(txtPrecio.getText()));
        inmuebleActual.setTipo(comboTipo.getValue());
        inmuebleActual.setEstado(comboEstado.getValue());
        inmuebleActual.setEstrato(Integer.parseInt(comboEstrato.getValue()));
        if (imagen1 != null) inmuebleActual.setImagen1(imagen1);

        boolean exito = InmuebleDAO.actualizarInmueble(inmuebleActual);

        if (exito) {
            Alert info = new Alert(Alert.AlertType.INFORMATION, "Cambios guardados correctamente.", ButtonType.OK);
            info.showAndWait();
            cambia.cambiarEscena(event, "/view/principal_sin_login.fxml");
        } else {
            Alert error = new Alert(Alert.AlertType.ERROR, "Error al guardar los cambios.", ButtonType.OK);
            error.showAndWait();
        }
    }


    @FXML
    private void comboBoxEvent(ActionEvent event) {
        // Para debug
        Object e = event.getSource();
        if (e == comboEstado) System.out.println(comboEstado.getSelectionModel().getSelectedItem());
        if (e == comboEstrato) System.out.println(comboEstrato.getSelectionModel().getSelectedItem());
        if (e == comboTipo) System.out.println(comboTipo.getSelectionModel().getSelectedItem());
    }

    @FXML
    private void back(ActionEvent event) {
        cambia.cambiarEscena(event, "/view/principal_sin_login.fxml");
    }

    @FXML
    private String escogerFoto() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar imagen");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Imágenes", "*.png", "*.jpg", "*.jpeg")
        );
        File archivo = fileChooser.showOpenDialog(null); // O el Stage actual
        if (archivo != null) {
            return archivo.getAbsolutePath();
        }
        return null;
    }
}
