package com.fundamentos.proyecto.model;

public class Conversacion {
    private int idPublicacion;
    private int otroUsuarioId;
    private String ultimoMensaje;

    public Conversacion(int idPublicacion, int otroUsuarioId, String ultimoMensaje) {
        this.idPublicacion = idPublicacion;
        this.otroUsuarioId = otroUsuarioId;
        this.ultimoMensaje = ultimoMensaje;
    }

    public int getIdPublicacion() { return idPublicacion; }
    public int getOtroUsuarioId() { return otroUsuarioId; }
    public String getUltimoMensaje() { return ultimoMensaje; }
}
