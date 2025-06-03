package com.fundamentos.proyecto.util;

import com.fundamentos.proyecto.controller.ResultadosController;
import com.fundamentos.proyecto.model.PublicacionInmueble;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.util.List;

public class CambiaEscenas {

    public void cambiarEscena(ActionEvent event, String rutaFxml) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(rutaFxml));
            Stage stage = (Stage)((javafx.scene.Node)event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void cambiarEscenaConResultados(String rutaFxml, List<PublicacionInmueble> resultados) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(rutaFxml));
            Parent root = loader.load();

            ResultadosController controller = loader.getController();
            controller.setResultados(resultados);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
