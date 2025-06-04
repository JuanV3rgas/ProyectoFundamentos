package com.fundamentos.proyecto.model;

import java.util.Date;

public class Publicacion {
    private int id;
    private int idUsuario;      // dueño de la publicación
    private int idInmueble;
    private String estado;
    private Date fechaPublicacion;

    // Constructor
    public Publicacion(int id, int idUsuario, int idInmueble, String estado, Date fechaPublicacion) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.idInmueble = idInmueble;
        this.estado = estado;
        this.fechaPublicacion = fechaPublicacion;
    }

    public Publicacion() {
    }

    // Getters y setters
    public int getId() { return id; }
    public int getIdUsuario() { return idUsuario; }
    public int getIdInmueble() { return idInmueble; }
    public String getEstado() { return estado; }
    public Date getFechaPublicacion() { return fechaPublicacion; }

    public void setId(int id) { this.id = id; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }
    public void setIdInmueble(int idInmueble) { this.idInmueble = idInmueble; }
    public void setEstado(String estado) { this.estado = estado; }
    public void setFechaPublicacion(Date fechaPublicacion) { this.fechaPublicacion = fechaPublicacion; }
}
