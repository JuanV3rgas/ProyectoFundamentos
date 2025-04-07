    package com.fundamentos.proyecto.app;

    import javafx.application.Application;
    import javafx.fxml.FXMLLoader;
    import javafx.scene.Parent;
    import javafx.scene.Scene;
    import javafx.stage.Stage;

    import java.net.URL;

    public class App extends Application {

        @Override
        public void start(Stage stage) throws Exception {
            URL fxmlUrl = getClass().getResource("/view/principal_con_login.fxml");
            if (fxmlUrl == null) {
                System.err.println("No se encontró el recurso: /view/principal_con_login");
                return;
            }
            Parent root = FXMLLoader.load(fxmlUrl);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Pagina principal");
            stage.show();
        }


        public static void main(String[] args) {
            launch(args);
        }
    }
