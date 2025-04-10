package com.fundamentos.proyecto.model;

import java.math.BigDecimal;

//clase Inmueble
public class Inmueble {
    private int id;
    private String tipo;
    private String estado;
    private String direccion;
    private int estrato;
    private int habitaciones;
    private int banos;
    private double area;
    private BigDecimal precio;
    // Agregamos para imágenes:
    private byte[] imagen1;

    // Getters y setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public int getEstrato() { return estrato; }
    public void setEstrato(int estrato) { this.estrato = estrato; }

    public int getHabitaciones() { return habitaciones; }
    public void setHabitaciones(int habitaciones) { this.habitaciones = habitaciones; }

    public int getBanos() { return banos; }
    public void setBanos(int banos) { this.banos = banos; }

    public double getArea() { return area; }
    public void setArea(double area) { this.area = area; }

    public BigDecimal getPrecio() { return precio; }
    public void setPrecio(BigDecimal precio) { this.precio = precio; }

    public byte[] getImagen1() { return imagen1; }
    public void setImagen1(byte[] imagen1) { this.imagen1 = imagen1; }

}
