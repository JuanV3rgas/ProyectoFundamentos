package com.fundamentos.proyecto.model;

public class Usuario {
    private int id;
    private String nombre;
    private String apellido;
    private String cedula;
    private String correo;
    private String contrasena;
    private String pregunta_seguridad;
    private String respuesta_pregunta_seguridad;
    private String numero_telefonico;


    public Usuario(int id, String nombre, String apellido, String cedula, String correo, String contrasena, String pregunta_seguridad, String respuesta_pregunta_seguridad, String numero_telefonico) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.correo = correo;
        this.contrasena = contrasena;
        this.pregunta_seguridad = pregunta_seguridad;
        this.respuesta_pregunta_seguridad = respuesta_pregunta_seguridad;
        this.numero_telefonico = numero_telefonico;
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

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
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
