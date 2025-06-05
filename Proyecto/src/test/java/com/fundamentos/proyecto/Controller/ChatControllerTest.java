package com.fundamentos.proyecto.Controller;

import com.fundamentos.proyecto.controller.ChatController;
import com.fundamentos.proyecto.dao.MensajeDAO;
import com.fundamentos.proyecto.dao.UsuarioDAO;
import com.fundamentos.proyecto.util.CambiaEscenas;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class ChatControllerTest {

    private ChatController controller;
    private Connection conexionReal;

    @BeforeEach
    void setUp() throws SQLException {
        controller = new ChatController();

        // Instancia controles FXML simulados
        controller.mensajesVBox = new VBox();
        controller.chatsListVBox = new VBox();
        controller.mensajeTextField = new TextField();
        controller.enviarButton = new Button();
        controller.chatTitle = new Label();
        controller.mensajesScrollPane = new ScrollPane();

        // Usa servicios y DAOs reales
        // Cambia la URL según tu base de datos
        // Ejemplo para H2 en modo archivo:
        conexionReal = DriverManager.getConnection("jdbc:h2:file:./src/main/resources/db/bienesRaices", "david", "");
        controller.cambia = new CambiaEscenas();

        // Inicializa datos necesarios para el test (ajusta IDs a tu base real)
        int idPublicacion = 1;       // Debe existir en tu BD
        int usuarioActualId = 2;     // Debe existir en tu BD
        int receptorId = 3;          // Debe existir en tu BD

        controller.initData(idPublicacion, usuarioActualId, receptorId, conexionReal);

        // Prepara un mensaje para enviar
        controller.mensajeTextField.setText("Mensaje de prueba en test real");
    }

    @Test
    void testEnviarMensajeConServiciosReales() {
        // No validamos resultado interno, solo que el flujo no lance excepciones
        assertDoesNotThrow(() -> controller.enviarMensaje(),
                "El método enviarMensaje debe ejecutarse sin lanzar excepciones usando DAOs reales");
    }
}
