package DAOTests;

import org.junit.jupiter.api.*;
import com.fundamentos.proyecto.dao.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

public class PublicacionDAOTest {
    @BeforeAll
    static void setUpDatabase() {
        TestDatabaseUtil.inicializarSchema();
    }

    @Test
    void testCrearPublicacion() {
        // Crea un usuario
        UsuarioDAO.crearUsuario(
                "Maria", "Gomez", "54321", "maria@correo.com", "pass456", "Mascota", "Luna", "3001112222"
        );
        // Crea un inmueble
        int idInmueble = InmuebleDAO.insertarInmueble(
                "Casa", "Disponible", "Carrera 45 #67-89", 3, 4, 3, new BigDecimal("350000000"), null, 120.0
        );
        // Busca el usuario para obtener el ID
        var usuario = UsuarioDAO.obtenerUsuario("maria@correo.com", "pass456");

        // Crea la publicación
        boolean creada = PublicacionDAO.insertarPublicacion(
                usuario.getId(),
                Date.valueOf(LocalDate.now()),
                "publicada",
                idInmueble
        );
        Assertions.assertTrue(creada, "La publicación debería crearse correctamente");
    }
}
