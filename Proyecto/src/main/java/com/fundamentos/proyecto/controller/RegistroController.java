package com.fundamentos.proyecto.controller;

import com.fundamentos.proyecto.dao.UsuarioDAO;
import com.fundamentos.proyecto.util.CambiaEscenas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;


public class RegistroController {

    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtApellido;
    @FXML
    private TextField txtCedula;
    @FXML
    private TextField txtCorreo;
    @FXML private TextField txtContrasena;
    @FXML private TextField txtRespuesta;
    @FXML
    private ComboBox<String> combobox_Pregunta;
    @FXML private  TextField txtCelular;

    CambiaEscenas cambia = new CambiaEscenas();

    @FXML
    private void initialize() {
        combobox_Pregunta.getItems().add("Nombre de tu primera mascota");
        combobox_Pregunta.getItems().add("Nombre del barrio donde creciste");
        combobox_Pregunta.getItems().add("Nombre de tu mejor amigo");
    }


    @FXML
    private void registrar(ActionEvent event) {
        String nombre = txtNombre.getText();
        String apellido = txtApellido.getText();
        String cedula = txtCedula.getText();
        String correo = txtCorreo.getText();
        String contrasena = txtContrasena.getText();
        String respuesta = txtRespuesta.getText();
        String preguntaSeleccionada = combobox_Pregunta.getValue();
        String celular = txtCelular.getText();

        boolean registro = UsuarioDAO.crearUsuario(txtNombre.getText(), txtApellido.getText(),
                txtCedula.getText(), txtCorreo.getText(), txtContrasena.getText(),
                preguntaSeleccionada, txtRespuesta.getText(), txtCelular.getText());

        if(registro) {
            mostrarAlerta(Alert.AlertType.INFORMATION, "Registro exitoso");
            cambia.cambiarEscena(event, "/view/principal_sin_login.fxml");
        } else {
            mostrarAlerta(Alert.AlertType.ERROR, "Datos incorrectos, inténtelo nuevamente");
            limpiarCampos();
        }
    }

    private void limpiarCampos(){
        txtContrasena.clear( );
    }

    private void mostrarAlerta(Alert.AlertType tipo, String mensaje) {
        Alert alert = new Alert(tipo);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    @FXML
    private void comboBoxEvent(ActionEvent event) {

        Object e = event.getSource();
        if(e == combobox_Pregunta) {
            System.out.println(combobox_Pregunta.getSelectionModel().getSelectedItem());
        }

    }

}
