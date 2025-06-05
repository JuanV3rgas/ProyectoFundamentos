import org.junit.jupiter.api.*;
import com.fundamentos.proyecto.dao.*;
import com.fundamentos.proyecto.model.Inmueble;

import java.math.BigDecimal;

public class EditarInmuebleDAOTest {
    @BeforeAll
    static void setUpDatabase() {
        TestDatabaseUtil.inicializarSchema();
    }

    @Test
    void testEditarInmueble() {
        // Crea inmueble
        int id = InmuebleDAO.insertarInmueble("Casa", "Disponible", "Av. 10 #10-10", 5, 5, 4, new BigDecimal("500000000"), null, 200.0);

        // Modifica datos y actualiza
        Inmueble in = new Inmueble();
        in.setId(id);
        in.setTipo("Casa");
        in.setEstado("Reservada"); // Cambio de estado
        in.setDireccion("Av. 10 #10-10");
        in.setEstrato(5);
        in.setHabitaciones(5);
        in.setBanos(4);
        in.setPrecio(new BigDecimal("500000000"));
        in.setImagen1(null);
        in.setArea(200.0);

        boolean actualizado = InmuebleDAO.actualizarInmueble(in);
        Assertions.assertTrue(actualizado, "El inmueble debería actualizarse correctamente");
    }
}
