import org.junit.jupiter.api.*;
import com.fundamentos.proyecto.dao.UsuarioDAO;

public class UsuarioDAOTest {
    @BeforeAll
    static void setUpDatabase() {
        TestDatabaseUtil.inicializarSchema();
    }

    @Test
    void testCrearUsuario() {
        boolean creado = UsuarioDAO.crearUsuario(
                "Juan", "Pérez", "12345", "juan@correo.com", "password123", "Color favorito", "Azul", "3000000000"
        );
        Assertions.assertTrue(creado, "El usuario debería crearse exitosamente");
    }
}
