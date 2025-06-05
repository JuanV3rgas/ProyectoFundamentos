package com.fundamentos.proyecto.controller;

import com.fundamentos.proyecto.controller.ChatController;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ChatControllerTest {

    private ChatController controller;

    @BeforeEach
    void setUp() {
        controller = new ChatController();

        controller.chatArea = new TextArea();
        controller.mensajeField = new TextField();
    }

    @Test
    void testEnviarMensaje() {
        controller.mensajeField.setText("Hola");
        controller.enviarMensaje();
        assertTrue(controller.chatArea.getText().contains("Hola"));
    }

    @Test
    void testMensajeVacioNoSeAgrega() {
        controller.mensajeField.setText("");
        controller.enviarMensaje();
        assertEquals("", controller.chatArea.getText().trim());
    }
}
