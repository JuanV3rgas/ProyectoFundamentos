package com.fundamentos.proyecto.controller;

import com.fundamentos.proyecto.dao.InmuebleDAO;    // Ejemplo
import com.fundamentos.proyecto.dao.PublicacionDAO; // Ejemplo
import com.fundamentos.proyecto.util.CambiaEscenas;
import com.fundamentos.proyecto.util.UserSession;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;
import java.math.BigDecimal;
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
    private byte[] imagen2;
    private byte[] imagen3;

    private CambiaEscenas cambia = new CambiaEscenas();

    @FXML
    public void initialize() {
        // Llenar los ComboBox con las opciones deseadas
        comboTipo.getItems().addAll("casa", "apartamento", "local");
        comboEstado.getItems().addAll("nuevo", "usado", "obra gris", "obra negra");
        comboEstrato.getItems().addAll("1", "2", "3", "4", "5", "6");
    }

    @FXML
    private void escogeFoto1(ActionEvent event) {
        imagen1 = seleccionarImagen().getBytes();
    }

    @FXML
    private void escogeFoto2(ActionEvent event) {
        imagen2 = seleccionarImagen().getBytes();
    }

    @FXML
    private void escogeFoto3(ActionEvent event) {
        imagen3 = seleccionarImagen().getBytes();
    }

    /**
     * Abre un FileChooser para seleccionar una imagen y retorna la ruta seleccionada
     */
    private String seleccionarImagen() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar imagen");
        // Extensiones posibles
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Imágenes", "*.png", "*.jpg", "*.jpeg")
        );
        File archivo = fileChooser.showOpenDialog(null); // O el Stage actual
        if (archivo != null) {
            return archivo.getAbsolutePath();
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
                banos, precio, imagen1, imagen2, imagen3, area);
        
        if (idInmueble > 0) {

            Date fecha = Date.valueOf(LocalDate.now());
            boolean publicada = PublicacionDAO.insertarPublicacion(idUsuario, fecha, "publicada", idInmueble);

            if (publicada) {
                // Navegar o mostrar mensaje
                System.out.println("Publicación creada exitosamente");
                // Cambiar de escena si lo deseas
                cambia.cambiarEscena(event, "/view/principal_sin_login.fxml");
            } else {
                System.out.println("Error al crear la publicación");
            }
        } else {
            System.out.println("Error al crear el inmueble");
        }
    }
}
