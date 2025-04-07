package com.fundamentos.proyecto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {
    private int id;
    private String nombre;
    private String apellido;
    private String correo;
    private String cedula;
    private String contrasena;
    private String pregunta;
    private String respuesta;


    public UsuarioDAO(int id, String nombre, String apellido, String correo, String cedula, String contrasena, String pregunta_seguridad, String respuesta_pregunta, String numero_telefonico) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.cedula = cedula;
        this.contrasena = contrasena;
        this.pregunta = pregunta_seguridad;
        this.respuesta = respuesta_pregunta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public static boolean validarUsuario(String correo, String contrasena) {
        String sql = "SELECT COUNT(*) FROM USUARIO WHERE correo = ? AND contrasena = ?";


        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, correo);
            ps.setString(2, contrasena);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0; // Devuelve true si se encontró un usuario
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public static boolean crearUsuario(String nombre, String apellido, String cedula, String correo
                                       ,String contrasena, String pregunta, String respuesta, String celular) {

        if(!(containsOnlyDigitsUsingIsDigit(celular))){
            return false;
        }

        String sql = "INSERT INTO USUARIO(nombre, apellido, cedula, correo, contrasena, pregunta_seguridad, respuesta_pregunta_seguridad, numero_telefonico) VALUES (?,?,?,?,?,?,?,?)\n";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, correo);
            ps.setString(2, contrasena);
            ps.setString(3, cedula);
            ps.setString(4, correo);
            ps.setString(5, contrasena);
            ps.setString(6, pregunta);
            ps.setString(7, respuesta);
            ps.setString(8, celular);
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean containsOnlyDigitsUsingIsDigit(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
