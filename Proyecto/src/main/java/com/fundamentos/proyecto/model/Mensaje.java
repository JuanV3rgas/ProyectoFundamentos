package com.fundamentos.proyecto.model;

public class Mensaje {
    private int id;
    private String mensaje;
    private int emisorId;
    private int receptorId;

    // Getters y setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getMensaje() { return mensaje; }
    public void setMensaje(String mensaje) { this.mensaje = mensaje; }

    public int getEmisorId() { return emisorId; }
    public void setEmisorId(int emisorId) { this.emisorId = emisorId; }

    public int getReceptorId() { return receptorId; }
    public void setReceptorId(int receptorId) { this.receptorId = receptorId; }
}
