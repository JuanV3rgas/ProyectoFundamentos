package com.fundamentos.proyecto.services;

import com.fundamentos.proyecto.dao.MensajeDAO;
import com.fundamentos.proyecto.model.Mensaje;
import com.fundamentos.proyecto.util.UserSession;

import java.util.List;

public class MensajeService {

    public boolean enviarMensaje(int receptorId, String contenido) {
        if (contenido == null || contenido.trim().isEmpty()) {
            return false; // No se permite enviar mensajes vacíos
        }

        UserSession sesion = UserSession.getInstance();
        if (sesion == null) {
            return false; // No hay usuario logueado
        }

        int emisorId = sesion.getUserId();
        return MensajeDAO.enviarMensaje(emisorId, receptorId, contenido.trim());
    }

    public List<Mensaje> obtenerMensajesRecibidos() {
        UserSession sesion = UserSession.getInstance();
        if (sesion == null) {
            return null; // No hay usuario logueado
        }

        int receptorId = sesion.getUserId();
        return MensajeDAO.obtenerMensajesRecibidos(receptorId);
    }
}
