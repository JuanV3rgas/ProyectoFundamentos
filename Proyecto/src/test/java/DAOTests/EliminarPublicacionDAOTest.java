package DAOTests;

import org.junit.jupiter.api.*;
import com.fundamentos.proyecto.dao.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

public class EliminarPublicacionDAOTest {
    @BeforeAll
    static void setUpDatabase() {
        TestDatabaseUtil.inicializarSchema();
    }

    @Test
    void testEliminarPublicacion() {
        // Crea usuario e inmueble
        UsuarioDAO.crearUsuario("Luis", "Martinez", "22222", "luis@correo.com", "passluis", "Ciudad", "Bogotá", "3016666666");
        int idInmueble = InmuebleDAO.insertarInmueble("Apartamento", "Disponible", "Cl 8 #5-20", 2, 2, 2, new BigDecimal("180000000"), null, 70.0);
        var usuario = UsuarioDAO.obtenerUsuario("luis@correo.com", "passluis");
        PublicacionDAO.insertarPublicacion(usuario.getId(), Date.valueOf(LocalDate.now()), "publicada", idInmueble);

        // Busca el ID de la publicación
        var publicaciones = PublicacionInmuebleDAO.obtenerPublicacionesPorUsuario(usuario.getId());
        int idPublicacion = publicaciones.get(0).getPublicacion().getId();

        // Elimina la publicación
        boolean eliminada = PublicacionDAO.eliminarPublicacionPorId(idPublicacion);
        Assertions.assertTrue(eliminada, "La publicación debería eliminarse correctamente");
    }
}
