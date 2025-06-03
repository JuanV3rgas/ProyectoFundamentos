package com.fundamentos.proyecto.controller;

import com.fundamentos.proyecto.services.MensajeService;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class ContactoController {

    @FXML
    private TextArea mensajeTextArea;

    @FXML
    private Button enviarButton;

    private final MensajeService mensajeService = new MensajeService();

    private int receptorId; // Este se setea desde fuera

    @FXML
    public void initialize() {
        enviarButton.setOnAction(event -> enviarMensaje());
    }

    public void setReceptorId(int receptorId) {
        this.receptorId = receptorId;
    }

    private void enviarMensaje() {
        String contenido = mensajeTextArea.getText();

        boolean enviado = mensajeService.enviarMensaje(receptorId, contenido);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if (enviado) {
            alert.setTitle("Éxito");
            alert.setHeaderText(null);
            alert.setContentText("Mensaje enviado correctamente.");
            mensajeTextArea.clear();
        } else {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("No se pudo enviar el mensaje.");
        }
        alert.showAndWait();
    }
}
