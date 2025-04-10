package com.fundamentos.proyecto.controller;

import com.fundamentos.proyecto.dao.UsuarioDAO;
import com.fundamentos.proyecto.services.UsuarioService;
import com.fundamentos.proyecto.util.CambiaEscenas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class VerificacionController {

    @FXML private TextField txtCorreo;
    @FXML private TextField txtRespuesta;
    @FXML ComboBox<String> combobox_Pregunta;

    CambiaEscenas cambia = new CambiaEscenas();
    private UsuarioService usuarioService = new UsuarioService();


    @FXML
    private void verificarUser(ActionEvent event) {
        String correo = txtCorreo.getText();
        String respuesta = txtRespuesta.getText();
        String preguntaSeleccionada = combobox_Pregunta.getValue();

        boolean validarRecuperacion = usuarioService.validarRecuperacion(correo, preguntaSeleccionada, respuesta);


        if(validarRecuperacion) {
            cambia.cambiarEscena(event, "/view/recuperacion.fxml");
        } else {
            mostrarAlerta(Alert.AlertType.ERROR, "Datos incorrectos, inténtelo nuevamente");
            limpiarCampos();
        }
    }



    private void limpiarCampos(){
        txtCorreo.clear();
        txtRespuesta.clear();
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

    @FXML
    private void initialize() {
        combobox_Pregunta.getItems().add("Nombre de tu primera mascota");
        combobox_Pregunta.getItems().add("Nombre del barrio donde creciste");
        combobox_Pregunta.getItems().add("Nombre de tu mejor amigo");
    }
}
