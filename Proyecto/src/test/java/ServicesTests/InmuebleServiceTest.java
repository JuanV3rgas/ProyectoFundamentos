package ServicesTests;
import org.junit.jupiter.api.*;
import com.fundamentos.proyecto.services.InmuebleService;
import com.fundamentos.proyecto.model.Inmueble;
import java.math.BigDecimal;
import java.util.List;

public class InmuebleServiceTest {
    @BeforeEach
    void setUpDatabase() {
        TestDatabaseUtil.inicializarSchema();
    }

    @Test
    void testRegistrarInmueble() {
        InmuebleService service = new InmuebleService();
        int id = service.registrarInmueble("Casa", "Disponible", "Cra 1 #2-3", 3, 4, 2, 100.0, new BigDecimal("300000000"), null);
        Assertions.assertTrue(id > 0, "El inmueble debe registrarse y retornar ID válido");
    }

    @Test
    void testFiltrarInmuebles() {
        InmuebleService service = new InmuebleService();
        service.registrarInmueble("Apartamento", "Disponible", "Calle 10 #20-30", 4, 3, 2, 80.5, new BigDecimal("200000000"), null);
        List<Inmueble> lista = service.filtrarInmuebles("Apartamento", "Disponible", null, null, null, null, null, null, null, null);
        Assertions.assertFalse(lista.isEmpty(), "Debe encontrar al menos un inmueble");
    }
}
