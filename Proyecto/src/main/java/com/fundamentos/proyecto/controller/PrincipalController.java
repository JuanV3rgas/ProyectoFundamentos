package com.fundamentos.proyecto.controller;

import com.fundamentos.proyecto.util.CambiaEscenas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.ArrayList;
import java.util.List;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;



public class PrincipalController {

    private List<String> list = new ArrayList<String>();
    int j = 0;
    double orgCliskSceneX, orgReleaseSceneX;
    Button anterior, siguiente;
    ImageView imageView;
    @FXML
    private Button previous;
    @FXML
    private Button next;

    CambiaEscenas cambia = new CambiaEscenas();

    ArrayList<Image> imageList = new ArrayList<>();
    private int indice = 0;

    @FXML
    private void login(ActionEvent event) {
        cambia.cambiarEscena(event, "/view/login.fxml");

    }

    public void load(Stage primaryStage) {
        // images in src folder.
        try {
            list.add("resources/view//1.jpg");
            list.add("resources/view//2.png");
            list.add("resources/view//2.png");
            list.add("resources/view//3.png");
            list.add("resources/view//4.jpg");
            list.add("resources/view//5.png");
            list.add("resources/view//6.jpg");
            list.add("resources/view/Logo/Bithouse.png");

            GridPane root = new GridPane();
            root.setAlignment(Pos.CENTER);

            anterior = new Button("<");
            siguiente = new Button(">");

            Image images[] = new Image[list.size()];
            for (int i = 0; i < list.size(); i++) {
                images[i] = new Image(list.get(i));
            }

            imageView = new ImageView(images[j]);
            imageView.setCursor(Cursor.CLOSED_HAND);

            imageView.setOnMousePressed(circleOnMousePressedEventHandler);

            imageView.setOnMouseReleased(e -> {
                orgReleaseSceneX = e.getSceneX();
                if (orgCliskSceneX > orgReleaseSceneX) {
                    anterior.fire();
                } else {
                    siguiente.fire();
                }
            });

            siguiente.setOnAction(e -> {
                j = j + 1;
                if (j == list.size()) {
                    j = 0;
                }
                imageView.setImage(images[j]);

            });
            anterior.setOnAction(e -> {
                j = j - 1;
                if (j == 0 || j > list.size() + 1 || j == -1) {
                    j = list.size() - 1;
                }
                imageView.setImage(images[j]);

            });

            imageView.setFitHeight(100);
            imageView.setFitWidth(300);

            HBox hBox = new HBox();
            hBox.setSpacing(15);
            hBox.setAlignment(Pos.CENTER);
            // hBox.getChildren().addAll(anterior, imageView, siguiente);
            hBox.getChildren().addAll(imageView);

            root.add(hBox, 1, 1);
            Scene scene = new Scene(root, 800, 300);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    EventHandler<MouseEvent> circleOnMousePressedEventHandler = new EventHandler<MouseEvent>() {
        public void handle(MouseEvent t) {
            orgCliskSceneX = t.getSceneX();
        }
    };


}

