package com.fundamentos.proyecto.dao;

import com.fundamentos.proyecto.model.Mensaje;
import com.fundamentos.proyecto.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MensajeDAO {

    public static boolean enviarMensaje(int emisorId, int receptorId, String mensaje) {
        String sql = "INSERT INTO MENSAJE(mensaje, emisor_id, receptor_id) VALUES (?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, mensaje);
            ps.setInt(2, emisorId);
            ps.setInt(3, receptorId);

            int filas = ps.executeUpdate();
            return filas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<Mensaje> obtenerMensajesRecibidos(int receptorId) {
        String sql = "SELECT * FROM MENSAJE WHERE receptor_id = ?";
        List<Mensaje> mensajes = new ArrayList<>();

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, receptorId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Mensaje m = new Mensaje();
                    m.setId(rs.getInt("id"));
                    m.setMensaje(rs.getString("mensaje"));
                    m.setEmisorId(rs.getInt("emisor_id"));
                    m.setReceptorId(rs.getInt("receptor_id"));
                    mensajes.add(m);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return mensajes;
    }
}
