package DAOTests;

import org.junit.jupiter.api.*;
import com.fundamentos.proyecto.dao.InmuebleDAO;

import java.math.BigDecimal;

public class InmuebleDAOTest {
    @BeforeAll
    static void setUpDatabase() {
        TestDatabaseUtil.inicializarSchema();
    }

    @Test
    void testCrearInmueble() {
        int id = InmuebleDAO.insertarInmueble(
                "Apartamento", "Disponible", "Calle 123 #45-67", 4, 3, 2, new BigDecimal("250000000"), null, 80.5
        );
        Assertions.assertTrue(id > 0, "El inmueble debería crearse y devolver un ID válido");
    }
}
