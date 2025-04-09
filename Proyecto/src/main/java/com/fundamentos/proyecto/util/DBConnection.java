package com.fundamentos.proyecto.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:h2:file:C:/Users/juanv/IdeaProjects/FundamentosDeIngesoft/Proyecto/src/main/resources/db/bienesRaices;AUTO_SERVER=TRUE";
    private static final String USER = "david";
    private static final String PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection("jdbc:h2:file:C:/Users/juanv/IdeaProjects/FundamentosDeIngesoft/Proyecto/src/main/resources/db/bienesRaices;AUTO_SERVER=TRUE", "david", "");
    }

}

