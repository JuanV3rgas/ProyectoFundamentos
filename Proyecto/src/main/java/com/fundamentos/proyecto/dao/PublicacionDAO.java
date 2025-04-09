package com.fundamentos.proyecto.dao;

import com.fundamentos.proyecto.util.DBConnection;

import java.sql.*;

public class PublicacionDAO {

    public static boolean insertarPublicacion(int idUsuario, Date fecha, String estado, int idInmueble) {
        String sql = "INSERT INTO PUBLICACIONES (id_usuario, fecha_publicacion, estado, id_inmueble) "
                + "VALUES (?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idUsuario);
            ps.setDate(2, fecha);
            ps.setString(3, estado);
            ps.setInt(4, idInmueble);

            int filas = ps.executeUpdate();
            return filas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
