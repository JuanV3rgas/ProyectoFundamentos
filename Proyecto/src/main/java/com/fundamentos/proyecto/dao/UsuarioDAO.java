package com.fundamentos.proyecto.dao;

import com.fundamentos.proyecto.model.Usuario;
import com.fundamentos.proyecto.util.DBConnection;

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

        String sql = "INSERT INTO USUARIO("
                + "nombre, "
                + "apellido, "
                + "cedula, "
                + "correo, "
                + "contrasena, "
                + "pregunta_seguridad, "
                + "respuesta_pregunta_seguridad, "
                + "numero_telefonico"
                + ") VALUES (?,?,?,?,?,?,?,?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            // Coincidir las columnas con las variables
            ps.setString(1, nombre);
            ps.setString(2, apellido);
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

    public static boolean validarRecuperacion(String correo, String pregunta_comboBox, String respuesta_comboBox) {
        String sql = "SELECT COUNT(*) FROM USUARIO WHERE correo = ? AND pregunta_seguridad = ? AND respuesta_pregunta_seguridad = ?";


        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, correo);
            ps.setString(2, pregunta_comboBox);
            ps.setString(3, respuesta_comboBox);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public static boolean cambiarContrasena(String correo, String nuevaContrasena) {
        correo = correo.trim();

        String sql = "UPDATE USUARIO SET contrasena = ? WHERE correo = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nuevaContrasena);
            ps.setString(2, correo);

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Usuario obtenerUsuario(String correo, String contrasena) {
        String sql = "SELECT * FROM USUARIO WHERE correo = ? AND contrasena = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, correo.trim());
            ps.setString(2, contrasena);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("ID");
                    String nombre = rs.getString("Nombre");
                    String apellido = rs.getString("Apellido");
                    int cedula = rs.getInt("Cedula");
                    String correoU = rs.getString("Correo");
                    String contrasenaU = rs.getString("Contrasena");
                    String preguntaSeguridad = rs.getString("pregunta_seguridad");
                    String respuesta = rs.getString("respuesta_pregunta_seguridad");
                    String celular = rs.getString("numero_telefonico");

                    return new Usuario(id, nombre, apellido, cedula, correo, contrasena, preguntaSeguridad, respuesta, celular);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }


    public static Usuario obtenerUsuarioCorreo(String correo) {
        String sql = "SELECT * FROM USUARIO WHERE correo = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, correo.trim());

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("ID");
                    String nombre = rs.getString("Nombre");
                    String apellido = rs.getString("Apellido");
                    int cedula = rs.getInt("Cedula");
                    String correoU = rs.getString("Correo");
                    String contrasenaU = rs.getString("Contrasena");
                    String preguntaSeguridad = rs.getString("pregunta_seguridad");
                    String respuesta = rs.getString("respuesta_pregunta_seguridad");
                    String celular = rs.getString("numero_telefonico");

                    return new Usuario(id, nombre, apellido, cedula, correo, contrasenaU, preguntaSeguridad, respuesta, celular);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    public static Usuario obtenerUsuarioPorId(int id) {
        Usuario usuario = null;
        String sql = "SELECT * FROM USUARIO WHERE ID = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int idU = rs.getInt("ID");
                String nombre = rs.getString("Nombre");
                String apellido = rs.getString("Apellido");
                int cedula = rs.getInt("Cedula");
                String correo = rs.getString("Correo");
                String contrasenaU = rs.getString("Contrasena");
                String preguntaSeguridad = rs.getString("pregunta_seguridad");
                String respuesta = rs.getString("respuesta_pregunta_seguridad");
                String celular = rs.getString("numero_telefonico");

                return new Usuario(id, nombre, apellido, cedula, correo, contrasenaU, preguntaSeguridad, respuesta, celular);
            }

            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return usuario;
    }
}
