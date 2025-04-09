package com.fundamentos.proyecto.services;

import com.fundamentos.proyecto.dao.PublicacionDAO;
import com.fundamentos.proyecto.util.UserSession;
import java.sql.Date;
import java.time.LocalDate;

public class PublicacionService {

    private PublicacionDAO publicacionDAO;

    public PublicacionService() {
        this.publicacionDAO = new PublicacionDAO();
    }

    /**
     * Registra una publicación en la base de datos, asociando al usuario actual de la sesión con el inmueble.
     *
     * @param estado Estado de la publicación ("publicada", etc.)
     * @param idInmueble ID del inmueble recién insertado.
     * @return true si se insertó correctamente la publicación.
     */
    public boolean registrarPublicacion(String estado, int idInmueble) {
        // Obtener el id del usuario de la sesión actual
        int idUsuario = UserSession.getInstance().getUserId();
        // Usamos la fecha actual como fecha de publicación.
        Date fechaPublicacion = Date.valueOf(LocalDate.now());
        return PublicacionDAO.insertarPublicacion(idUsuario, fechaPublicacion, estado, idInmueble);
    }
}
