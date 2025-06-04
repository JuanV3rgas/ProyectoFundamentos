package com.fundamentos.proyecto.model;

public class PublicacionInmueble {
    private Inmueble inmueble;
    private Publicacion publicacion;

    public PublicacionInmueble(Inmueble inmueble, Publicacion publicacion) {
        this.inmueble = inmueble;
        this.publicacion = publicacion;
    }

    public Inmueble getInmueble() { return inmueble; }
    public Publicacion getPublicacion() { return publicacion; }
}
