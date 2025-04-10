package com.fundamentos.proyecto.dao;

import com.fundamentos.proyecto.model.Inmueble;
import com.fundamentos.proyecto.util.DBConnection;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InmuebleDAO {

    public static int insertarInmueble(String tipo, String estado, String direccion, int estrato,
                                       int habitaciones, int banos, BigDecimal precio,
                                       byte[] imagen1, double area) {
        // Nota: El orden de los parámetros es:
        // 1: tipo, 2: estado, 3: direccion, 4: estrato,
        // 5: habitaciones, 6: banos, 7: precio, 8: imagen1, 9: imagen2, 10: imagen3, 11: area
        String sql = "INSERT INTO INMUEBLE(tipo, estado, direccion, estrato, "
                + "habitaciones, banos, precio, imagen, area) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, tipo);
            ps.setString(2, estado);
            ps.setString(3, direccion);
            ps.setInt(4, estrato);
            ps.setInt(5, habitaciones);
            ps.setInt(6, banos);
            ps.setBigDecimal(7, precio);
            ps.setBytes(8, imagen1);
            ps.setDouble(11, area);

            int filas = ps.executeUpdate();
            if (filas > 0) {
                // Obtener la llave generada (ID)
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        return rs.getInt(1);  // Devuelve el ID del inmueble recién creado
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // Indica error
    }

    public static List<Inmueble> filtrarInmuebles(String tipo,
                                                  String estado,
                                                  Integer estratoMin,
                                                  Integer estratoMax,
                                                  Double areaMin,
                                                  Double areaMax,
                                                  BigDecimal precioMin,
                                                  BigDecimal precioMax) {
        List<Inmueble> lista = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM INMUEBLE WHERE 1=1 ");
        List<Object> parametros = new ArrayList<>();

        if (tipo != null && !tipo.isEmpty()) {
            sql.append("AND tipo = ? ");
            parametros.add(tipo);
        }
        if (estado != null && !estado.isEmpty()) {
            sql.append("AND tipo = ? ");
            parametros.add(tipo);
        }
        if (estratoMin != null) {
            sql.append("AND estrato >= ? ");
            parametros.add(estratoMin);
        }
        if (estratoMax != null) {
            sql.append("AND estrato <= ? ");
            parametros.add(estratoMax);
        }
        if (areaMin != null) {
            sql.append("AND area >= ? ");
            parametros.add(areaMin);
        }
        if (areaMax != null) {
            sql.append("AND area <= ? ");
            parametros.add(areaMax);
        }
        if (precioMin != null) {
            sql.append("AND precio >= ? ");
            parametros.add(precioMin);
        }
        if (precioMax != null) {
            sql.append("AND precio <= ? ");
            parametros.add(precioMax);
        }

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql.toString())) {
            for (int i = 0; i < parametros.size(); i++) {
                ps.setObject(i + 1, parametros.get(i));
            }
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Inmueble inm = new Inmueble();
                    inm.setId(rs.getInt("id"));
                    inm.setTipo(rs.getString("tipo"));
                    inm.setEstado(rs.getString("estado"));
                    inm.setDireccion(rs.getString("direccion"));
                    inm.setEstrato(rs.getInt("estrato"));
                    inm.setHabitaciones(rs.getInt("habitaciones"));
                    inm.setBanos(rs.getInt("banos"));
                    inm.setArea(rs.getDouble("area"));
                    inm.setPrecio(rs.getBigDecimal("precio"));
                    // Puedes agregar asignación de imágenes si necesitas.

                    lista.add(inm);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
