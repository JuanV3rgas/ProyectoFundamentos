package com.fundamentos.proyecto.util;

public class UserSession {
    private static UserSession instance;

    private int userId;
    private String nombre;
    private String apellido;
    private int cedula;

    private String correo;

    private String contrasena;
    private String pregunta_seguridad;
    private String respuesta_pregunta_seguridad;
    private String numero_telefonico;


    // Constructor privado


    private UserSession(int userId, String nombre, String apellido, int cedula, String correo, String contrasena, String pregunta_seguridad, String respuesta_pregunta_seguridad, String numero_telefonico) {
        this.userId = userId;
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.correo = correo;
        this.contrasena = contrasena;
        this.pregunta_seguridad = pregunta_seguridad;
        this.respuesta_pregunta_seguridad = respuesta_pregunta_seguridad;
        this.numero_telefonico = numero_telefonico;
    }

    public static UserSession createSession(int userId, String nombre, String apellido, int cedula, String correo, String contrasena, String pregunta_seguridad, String respuesta_pregunta_seguridad, String numero_telefonico) {
        instance = new UserSession(userId, nombre, apellido, cedula, correo, contrasena, pregunta_seguridad, respuesta_pregunta_seguridad, numero_telefonico );
        return instance;
    }


    public static UserSession getInstance() {
        return instance;
    }

    public static void clearSession() {
        instance = null;
    }


    public static void setInstance(UserSession instance) {
        UserSession.instance = instance;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getPregunta_seguridad() {
        return pregunta_seguridad;
    }

    public void setPregunta_seguridad(String pregunta_seguridad) {
        this.pregunta_seguridad = pregunta_seguridad;
    }

    public String getRespuesta_pregunta_seguridad() {
        return respuesta_pregunta_seguridad;
    }

    public void setRespuesta_pregunta_seguridad(String respuesta_pregunta_seguridad) {
        this.respuesta_pregunta_seguridad = respuesta_pregunta_seguridad;
    }

    public String getNumero_telefonico() {
        return numero_telefonico;
    }

    public void setNumero_telefonico(String numero_telefonico) {
        this.numero_telefonico = numero_telefonico;
    }
}
