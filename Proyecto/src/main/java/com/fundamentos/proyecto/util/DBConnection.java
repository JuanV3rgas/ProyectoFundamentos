package com.fundamentos.proyecto.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:h2:file:C:/Users/juanv/OneDrive/Documentos/ProyectoFundamentos/Proyecto/src/main/resources/db/bienesRaices;AUTO_SERVER=TRUE";
    private static final String USER = "david";
    private static final String PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // Si está activada la propiedad de test, usa H2 en memoria y carga el script de test.
        String testProperty = System.getProperty("testdb");
        if ("true".equals(testProperty)) {
            String testUrl = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;INIT=RUNSCRIPT FROM 'src/test/resources/schema.sql'";
            return DriverManager.getConnection(testUrl, "sa", "");
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
