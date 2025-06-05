package DAOTests;

import java.sql.Connection;
import java.sql.Statement;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestDatabaseUtil {
    public static void inicializarSchema() {
        try (Connection conn = com.fundamentos.proyecto.util.DBConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            String sql = new String(Files.readAllBytes(Paths.get("src/test/resources/schema-test.sql")));
            for (String comando : sql.split(";")) {
                if (!comando.trim().isEmpty()) {
                    stmt.execute(comando);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Error inicializando schema de pruebas", e);
        }
    }
}
