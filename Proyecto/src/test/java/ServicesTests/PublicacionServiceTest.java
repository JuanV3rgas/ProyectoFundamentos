package ServicesTests;
import com.fundamentos.proyecto.util.UserSession;
import org.junit.jupiter.api.*;
import com.fundamentos.proyecto.services.PublicacionService;
import com.fundamentos.proyecto.services.UsuarioService;
import com.fundamentos.proyecto.services.InmuebleService;
import com.fundamentos.proyecto.model.Usuario;
import java.math.BigDecimal;


public class PublicacionServiceTest {
    @BeforeEach
    void setUpDatabase() {
        TestDatabaseUtil.inicializarSchema();
    }

    @Test
    void testRegistrarPublicacion() {
        UsuarioService.registrarUsuario("Sofia", "Ramirez", "1234567", "sofia@correo.com", "clave", "Animal", "Gato", "3022222222");
        Usuario usuario = new UsuarioService().login("sofia@correo.com", "clave");
        InmuebleService inService = new InmuebleService();
        int idInmueble = inService.registrarInmueble("Casa", "Disponible", "Av 3 #45-67", 4, 5, 3, 140.0, new BigDecimal("600000000"), null);

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
        boolean publicada = pubService.registrarPublicacion("publicada", idInmueble);
        Assertions.assertTrue(publicada, "La publicación debe realizarse correctamente");
    }
}
