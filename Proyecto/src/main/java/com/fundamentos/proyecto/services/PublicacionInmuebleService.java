package com.fundamentos.proyecto.services;

import com.fundamentos.proyecto.dao.PublicacionInmuebleDAO;
import com.fundamentos.proyecto.model.PublicacionInmueble;
import java.util.List;

public class PublicacionInmuebleService {

    private PublicacionInmuebleDAO publicacionInmuebleDAO;

    public PublicacionInmuebleService() {
        this.publicacionInmuebleDAO = new PublicacionInmuebleDAO();
    }

    public List<PublicacionInmueble> filtrarPublicacionInmuebles(
            String tipo, String estado, Integer estratoMin, Integer estratoMax,
            Double areaMin, Double areaMax,
            java.math.BigDecimal precioMin, java.math.BigDecimal precioMax,
            Integer habitaciones, Integer banos) {
        return publicacionInmuebleDAO.filtrarPublicacionInmuebles(
                tipo, estado, estratoMin, estratoMax, areaMin, areaMax, precioMin, precioMax, habitaciones, banos
        );
    }
}
