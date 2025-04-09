package com.fundamentos.proyecto.controller;
import com.fundamentos.proyecto.util.CambiaEscenas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;




public class PrincipalController {

    CambiaEscenas cambia = new CambiaEscenas();

    @FXML
    private void login(ActionEvent event) {
        cambia.cambiarEscena(event, "/view/login.fxml");

    }


}

