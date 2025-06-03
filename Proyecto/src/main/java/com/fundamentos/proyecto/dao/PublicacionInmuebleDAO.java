package com.fundamentos.proyecto.dao;

import com.fundamentos.proyecto.model.Publicacion;
import com.fundamentos.proyecto.model.Inmueble;
import com.fundamentos.proyecto.model.PublicacionInmueble;
import com.fundamentos.proyecto.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;

public class PublicacionInmuebleDAO {
    public List<PublicacionInmueble> filtrarPublicacionInmuebles(
            String tipo, String estado, Integer estratoMin, Integer estratoMax,
            Double areaMin, Double areaMax, BigDecimal precioMin, BigDecimal precioMax,
            Integer habitaciones, Integer banos) {

        List<PublicacionInmueble> lista = new ArrayList<>();
        StringBuilder sql = new StringBuilder(
                "SELECT p.ID as pub_id, p.ID_USUARIO, p.FECHA_PUBLICACION, p.ESTADO as pub_estado, p.ID_INMUEBLE, " +
                        "i.ID as in_id, i.TIPO, i.ESTADO as in_estado, i.DIRECCION, i.ESTRATO, i.PRECIO, i.IMAGEN, i.HABITACIONES, i.BANOS, i.AREA " +
                        "FROM PUBLICACIONES p " +
                        "JOIN INMUEBLE i ON p.ID_INMUEBLE = i.ID WHERE 1=1 "
        );

        List<Object> params = new ArrayList<>();

        if (tipo != null && !tipo.isEmpty()) {
            sql.append(" AND i.TIPO = ?");
            params.add(tipo);
        }
        if (estado != null && !estado.isEmpty()) {
            sql.append(" AND i.ESTADO = ?");
            params.add(estado);
        }
        if (estratoMin != null) {
            sql.append(" AND i.ESTRATO >= ?");
            params.add(estratoMin);
        }
        if (estratoMax != null) {
            sql.append(" AND i.ESTRATO <= ?");
            params.add(estratoMax);
        }
        if (areaMin != null) {
            sql.append(" AND i.AREA >= ?");
            params.add(areaMin);
        }
        if (areaMax != null) {
            sql.append(" AND i.AREA <= ?");
            params.add(areaMax);
        }
        if (precioMin != null) {
            sql.append(" AND i.PRECIO >= ?");
            params.add(precioMin);
        }
        if (precioMax != null) {
            sql.append(" AND i.PRECIO <= ?");
            params.add(precioMax);
        }
        if (habitaciones != null) {
            sql.append(" AND i.HABITACIONES = ?");
            params.add(habitaciones);
        }
        if (banos != null) {
            sql.append(" AND i.BANOS = ?");
            params.add(banos);
        }

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql.toString())) {

            // Setea los parámetros en orden
            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                // ... tu código de mapeo ...
                Publicacion pub = new Publicacion();
                pub.setId(rs.getInt("pub_id"));
                pub.setIdUsuario(rs.getInt("ID_USUARIO"));
                pub.setFechaPublicacion(rs.getDate("FECHA_PUBLICACION"));
                pub.setEstado(rs.getString("pub_estado"));
                pub.setIdInmueble(rs.getInt("ID_INMUEBLE"));

                Inmueble in = new Inmueble();
                in.setId(rs.getInt("in_id"));
                in.setTipo(rs.getString("TIPO"));
                in.setEstado(rs.getString("in_estado"));
                in.setDireccion(rs.getString("DIRECCION"));
                in.setEstrato(rs.getInt("ESTRATO"));
                in.setPrecio(rs.getBigDecimal("PRECIO"));
                in.setImagen1(rs.getBytes("IMAGEN"));
                in.setHabitaciones(rs.getInt("HABITACIONES"));
                in.setBanos(rs.getInt("BANOS"));
                in.setArea(rs.getDouble("AREA"));

                lista.add(new PublicacionInmueble(in, pub));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
}
