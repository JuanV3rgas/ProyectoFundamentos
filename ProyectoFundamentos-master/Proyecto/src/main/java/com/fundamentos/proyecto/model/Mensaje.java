package com.fundamentos.proyecto.model;

public class Mensaje {
    private int id;
    private int publicacionId;
    private int emisorId;
    private int receptorId;
    private String contenido;
    private String fecha;

    public Mensaje(int id, int publicacionId, int emisorId, int receptorId, String contenido, String fecha) {
        this.id = id;
        this.publicacionId = publicacionId;
        this.emisorId = emisorId;
        this.receptorId = receptorId;
        this.contenido = contenido;
        this.fecha = fecha;
    }

    // Getters y setters
    public int getId() { return id; }
    public int getPublicacionId() { return publicacionId; }
    public int getEmisorId() { return emisorId; }
    public int getReceptorId() { return receptorId; }
    public String getContenido() { return contenido; }
    public String getFecha() { return fecha; }
}
