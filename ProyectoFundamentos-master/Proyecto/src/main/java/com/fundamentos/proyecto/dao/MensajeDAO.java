package com.fundamentos.proyecto.dao;

import com.fundamentos.proyecto.model.Conversacion;
import com.fundamentos.proyecto.model.Mensaje;
import com.fundamentos.proyecto.util.DBConnection;

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

    public List<Conversacion> obtenerConversacionesParaUsuario(int usuarioActualId) {
        List<Conversacion> lista = new ArrayList<>();
        String sql = """
        SELECT c.FK_ID_PUBLICACION,
               c.otroUsuarioId,
               (SELECT m.CONTENIDO 
                FROM MENSAJES m
                WHERE ( (m.EMISOR_ID = ? AND m.RECEPTOR_ID = c.otroUsuarioId)
                     OR (m.EMISOR_ID = c.otroUsuarioId AND m.RECEPTOR_ID = ?) )
                  AND m.FK_ID_PUBLICACION = c.FK_ID_PUBLICACION
                ORDER BY m.FECHA DESC
                LIMIT 1
               ) AS ultimoMensaje
        FROM (
            SELECT FK_ID_PUBLICACION,
                   CASE WHEN EMISOR_ID = ? THEN RECEPTOR_ID ELSE EMISOR_ID END as otroUsuarioId
            FROM MENSAJES
            WHERE EMISOR_ID = ? OR RECEPTOR_ID = ?
            GROUP BY FK_ID_PUBLICACION, otroUsuarioId
        ) c
        """;
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            // El mismo usuario va en los ?
            ps.setInt(1, usuarioActualId);
            ps.setInt(2, usuarioActualId);
            ps.setInt(3, usuarioActualId);
            ps.setInt(4, usuarioActualId);
            ps.setInt(5, usuarioActualId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int idPublicacion = rs.getInt("FK_ID_PUBLICACION");
                int otroUsuarioId = rs.getInt("otroUsuarioId");
                String ultimoMensaje = rs.getString("ultimoMensaje");
                lista.add(new Conversacion(idPublicacion, otroUsuarioId, ultimoMensaje));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

        // Devuelve todos los mensajes entre dos usuarios para una publicación dada (ordenados por fecha)
    public List<Mensaje> obtenerMensajesPorConversacion(int idPublicacion, int usuario1, int usuario2) {
        List<Mensaje> mensajes = new ArrayList<>();
        String sql = "SELECT * FROM MENSAJES " +
                "WHERE FK_ID_PUBLICACION = ? " +
                "AND ((EMISOR_ID = ? AND RECEPTOR_ID = ?) OR (EMISOR_ID = ? AND RECEPTOR_ID = ?)) " +
                "ORDER BY FECHA ASC";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idPublicacion);
            ps.setInt(2, usuario1);
            ps.setInt(3, usuario2);
            ps.setInt(4, usuario2);
            ps.setInt(5, usuario1);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    mensajes.add(new Mensaje(
                            rs.getInt("ID"),
                            rs.getInt("FK_ID_PUBLICACION"),
                            rs.getInt("EMISOR_ID"),
                            rs.getInt("RECEPTOR_ID"),
                            rs.getString("CONTENIDO"),
                            rs.getString("FECHA")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mensajes;
    }


}
