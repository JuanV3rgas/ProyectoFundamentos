package ServicesTests;
import com.fundamentos.proyecto.util.UserSession;
import org.junit.jupiter.api.*;
import com.fundamentos.proyecto.services.*;
import com.fundamentos.proyecto.model.*;
import java.math.BigDecimal;
import java.util.List;


public class PublicacionInmuebleServiceTest {
    @BeforeEach
    void setUpDatabase() {
        TestDatabaseUtil.inicializarSchema();
    }

    @Test
    void testFiltrarPublicacionInmuebles() {
        // Crear usuario, inmueble y publicación (como en test anterior)
        UsuarioService.registrarUsuario("Camilo", "Perez", "88888", "camilo@correo.com", "clave", "Mascota", "Leon", "3033333333");
        Usuario usuario = new UsuarioService().login("camilo@correo.com", "clave");
        InmuebleService inService = new InmuebleService();
        int idInmueble = inService.registrarInmueble("Apartamento", "Disponible", "Cra 50 #50-50", 5, 3, 2, 90.0, new BigDecimal("400000000"), null);
        UserSession.createSession(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getApellido(),
                usuario.getCedula(),
                usuario.getCorreo(),
                usuario.getContrasena(),
                usuario.getPregunta_seguridad(),
                usuario.getRespuesta_pregunta_seguridad(),
                usuario.getNumero_telefonico()
        );
        PublicacionService pubService = new PublicacionService();
        pubService.registrarPublicacion("publicada", idInmueble);

        PublicacionInmuebleService service = new PublicacionInmuebleService();
        List<PublicacionInmueble> lista = service.filtrarPublicacionInmuebles("Apartamento", "Disponible", null, null, null, null, null, null, null, null);
        Assertions.assertFalse(lista.isEmpty(), "Debe filtrar publicaciones por tipo correctamente");
    }
}
