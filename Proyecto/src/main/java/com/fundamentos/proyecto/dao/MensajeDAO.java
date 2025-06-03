package com.fundamentos.proyecto.dao;

import com.fundamentos.proyecto.model.Mensaje;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MensajeDAO {
    private final Connection conexion;

    public MensajeDAO(Connection conexion) {
        this.conexion = conexion;
    }

    // Insertar mensaje (FECHA por defecto = CURRENT_DATE en la BD)
    public void insertarMensaje(int publicacionId, int emisorId, int receptorId, String contenido) {
        String sql = "INSERT INTO Mensajes (FK_ID_PUBLICACION, EMISOR_ID, RECEPTOR_ID, CONTENIDO) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, publicacionId);
            stmt.setInt(2, emisorId);
            stmt.setInt(3, receptorId);
            stmt.setString(4, contenido);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Obtener mensajes por publicacion y ambos usuarios (en orden por fecha e ID)
    public List<Mensaje> obtenerMensajesPorPublicacion(int publicacionId, int userA, int userB) {
        List<Mensaje> mensajes = new ArrayList<>();
        String sql = "SELECT * FROM Mensajes " +
                "WHERE FK_ID_PUBLICACION = ? AND " +
                "((EMISOR_ID = ? AND RECEPTOR_ID = ?) OR (EMISOR_ID = ? AND RECEPTOR_ID = ?)) " +
                "ORDER BY FECHA ASC, ID ASC";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, publicacionId);
            stmt.setInt(2, userA);
            stmt.setInt(3, userB);
            stmt.setInt(4, userB);
            stmt.setInt(5, userA);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Mensaje mensaje = new Mensaje(
                        rs.getInt("ID"),
                        rs.getInt("FK_ID_PUBLICACION"),
                        rs.getInt("EMISOR_ID"),
                        rs.getInt("RECEPTOR_ID"),
                        rs.getString("CONTENIDO"),
                        rs.getDate("FECHA").toString() // convierte java.sql.Date a String (YYYY-MM-DD)
                );
                mensajes.add(mensaje);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mensajes;
    }
}
