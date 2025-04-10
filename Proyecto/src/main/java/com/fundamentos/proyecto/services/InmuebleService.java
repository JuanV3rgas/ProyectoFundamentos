package com.fundamentos.proyecto.services;

import com.fundamentos.proyecto.dao.InmuebleDAO;
import com.fundamentos.proyecto.model.Inmueble;
import java.math.BigDecimal;
import java.util.List;


public class InmuebleService {

    private InmuebleDAO inmuebleDAO;

    public InmuebleService() {
        this.inmuebleDAO = new InmuebleDAO();
    }

    public int registrarInmueble(String tipo, String estado, String direccion, int estrato,
                                 int habitaciones, int banos, double area, BigDecimal precio,
                                 byte[] imagen1, byte[] imagen2, byte[] imagen3) {
        return inmuebleDAO.insertarInmueble(tipo, estado, direccion, estrato, habitaciones,
                banos, precio, imagen1, imagen2, imagen3, area);
    }

    public List<Inmueble> filtrarInmuebles(String tipo,
                                           String estado,
                                           Integer estratoMin,
                                           Integer estratoMax,
                                           Double areaMin,
                                           Double areaMax,
                                           BigDecimal precioMin,
                                           BigDecimal precioMax) {
        return InmuebleDAO.filtrarInmuebles(tipo, estratoMin, estratoMax, areaMin, areaMax, precioMin, precioMax);
    }
}
