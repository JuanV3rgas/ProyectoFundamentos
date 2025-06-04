package com.fundamentos.proyecto.controller;

import com.fundamentos.proyecto.dao.InmuebleDAO;    // Ejemplo
import com.fundamentos.proyecto.dao.PublicacionDAO; // Ejemplo
import com.fundamentos.proyecto.util.CambiaEscenas;
import com.fundamentos.proyecto.util.UserSession;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.time.LocalDate;
import java.sql.Date;

public class CrearPublicacionController {

    @FXML private ComboBox<String> comboTipo;
    @FXML private ComboBox<String> comboEstado;
    @FXML private ComboBox<String> comboEstrato;

    @FXML private TextField txtDireccion;
    @FXML private TextField txtHabitaciones;
    @FXML private TextField txtBanos;
    @FXML private TextField txtArea;
    @FXML private TextField txtPrecio;

    // Variables para almacenar las rutas de las imágenes
    private byte[] imagen1;

    private CambiaEscenas cambia = new CambiaEscenas();

    @FXML
    public void initialize() {
        // Llenar los ComboBox con las opciones deseadas
        comboTipo.getItems().addAll("casa", "apartamento", "local");
        comboEstado.getItems().addAll("nuevo", "usado", "obra gris", "obra negra");
        comboEstrato.getItems().addAll("1", "2", "3", "4", "5", "6");
    }

    @FXML
    private void escogeFoto(ActionEvent event) {
        imagen1 = seleccionarImagen();
    }

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
    private void publicarVivienda(ActionEvent event) {
        // Obtener los datos de los campos
        String tipo = comboTipo.getValue();
        String estado = comboEstado.getValue();
        String direccion = txtDireccion.getText();
        int estrato = Integer.parseInt(comboEstrato.getValue());
        int habitaciones = Integer.parseInt(txtHabitaciones.getText());
        int banos = Integer.parseInt(txtBanos.getText());
        double area = Double.parseDouble(txtArea.getText());
        BigDecimal precio = new BigDecimal(txtPrecio.getText());


        UserSession session = UserSession.getInstance();
        int idUsuario = session.getUserId();


        int idInmueble = InmuebleDAO.insertarInmueble(tipo, estado, direccion, estrato, habitaciones,
                banos, precio, imagen1,area);
        
        if (idInmueble > 0) {

            Date fecha = Date.valueOf(LocalDate.now());
            boolean publicada = PublicacionDAO.insertarPublicacion(idUsuario, fecha, "publicada", idInmueble);

            if (publicada) {
                mostrarAlerta(Alert.AlertType.INFORMATION, "Publicacion creada exitosamente");
                cambia.cambiarEscena(event, "/view/principal_sin_login.fxml");
            } else {
                mostrarAlerta(Alert.AlertType.INFORMATION, "Error al crear la publicación");
            }
        } else {
            mostrarAlerta(Alert.AlertType.INFORMATION, "Error al crear el inmueble");
        }
    }

    @FXML
    private void comboBoxEvent(ActionEvent event) {

        Object e = event.getSource();
        if(e == comboEstado) {
            System.out.println(comboEstado.getSelectionModel().getSelectedItem());
        }
        if(e == comboEstrato) {
            System.out.println(comboEstrato.getSelectionModel().getSelectedItem());
        }
        if(e == comboTipo) {
            System.out.println(comboTipo.getSelectionModel().getSelectedItem());
        }

    }

    @FXML
    private void back(ActionEvent event) {
        cambia.cambiarEscena(event, "/view/principal_sin_login.fxml");
    }


    private void mostrarAlerta(Alert.AlertType tipo, String mensaje) {
        Alert alert = new Alert(tipo);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
