package ServicesTests;

import org.junit.jupiter.api.*;
import com.fundamentos.proyecto.services.UsuarioService;
import com.fundamentos.proyecto.model.Usuario;

public class UsuarioServiceTest {
    @BeforeEach
    void setUpDatabase() {
        TestDatabaseUtil.inicializarSchema();
    }

    @Test
    void testRegistrarYLoginUsuario() {
        boolean registrado = UsuarioService.registrarUsuario(
                "Ana", "Gomez", "12345", "ana@correo.com", "clave123", "Color", "Rojo", "3000000000"
        );
        Assertions.assertTrue(registrado, "El usuario debe registrarse correctamente");

        UsuarioService service = new UsuarioService();
        Usuario usuario = service.login("ana@correo.com", "clave123");
        Assertions.assertNotNull(usuario, "El login debe devolver un usuario válido");
        Assertions.assertEquals("Ana", usuario.getNombre());
    }

    @Test
    void testCambiarContrasena() {
        UsuarioService.registrarUsuario("Pedro", "Lopez", "67890", "pedro@correo.com", "claveold", "Animal", "Perro", "3011111111");
        UsuarioService service = new UsuarioService();

        boolean cambiado = service.cambiarContrasena("pedro@correo.com", "clavenueva");
        Assertions.assertTrue(cambiado);

        Usuario usuario = service.login("pedro@correo.com", "clavenueva");
        Assertions.assertNotNull(usuario, "Debe poder loguearse con la nueva contraseña");
    }
}
