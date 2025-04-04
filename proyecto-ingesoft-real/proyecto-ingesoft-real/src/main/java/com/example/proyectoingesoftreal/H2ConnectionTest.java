package com.example.proyectoingesoftreal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class H2ConnectionTest {
    public static void main(String[] args) {
        String url = "jdbc:h2:tcp://localhost:1521/test";
        String username = "sa";  // Usuario por defecto
        String password = "";    // Contraseña por defecto (vacía)

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            System.out.println("¡Conexión exitosa a la base de datos!");
            // Aquí puedes agregar la lógica para trabajar con la base de datos
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
