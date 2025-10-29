import R2_4_OpenAPI.*;
import org.junit.jupiter.api.Test;

import java.util.List;

class ApiServiceTest {

    @Test
    void testFetchUsers() throws Exception {
        System.out.println("=== TEST 1: Obtener usuarios de la API ===");

        ApiService service = new ApiService();
        List<User> users = service.fetchUsers();

        // Verificaciones simples
        assert users != null : "La lista no debe ser null";
        assert !users.isEmpty() : "Debe haber usuarios";
        assert users.size() == 10 : "Deben ser 10 usuarios";

        System.out.println("✅ Test API completado - " + users.size() + " usuarios obtenidos");
        System.out.println();
    }

    @Test
    void testExportCsv() throws Exception {
        System.out.println("=== TEST 2: Exportar a CSV ===");

        ApiService service = new ApiService();
        List<User> users = service.fetchUsers();

        CsvExporter exporter = new CsvExporter();
        exporter.export(users, "users_test.csv");

        System.out.println("✅ Test CSV completado");
        System.out.println();
    }

    @Test
    void testExportDb() throws Exception {
        System.out.println("=== TEST 3: Exportar a SQLite ===");

        ApiService service = new ApiService();
        List<User> users = service.fetchUsers();

        DbExporter exporter = new DbExporter();
        exporter.export(users, "users_test.db");

        System.out.println("✅ Test DB completado");
        System.out.println();
    }

    @Test
    void testExportSql() throws Exception {
        System.out.println("=== TEST 4: Exportar a SQL ===");

        ApiService service = new ApiService();
        List<User> users = service.fetchUsers();

        SqlExporter exporter = new SqlExporter();
        exporter.export(users, "users_test.sql");

        System.out.println("✅ Test SQL completado");
        System.out.println();
    }

    @Test
    void testAllFormats() throws Exception {
        System.out.println("=== TEST 5: Exportar a TODOS los formatos ===");

        ApiService service = new ApiService();
        List<User> users = service.fetchUsers();

        // Exportar a CSV
        new CsvExporter().export(users, "users_all_test.csv");

        // Exportar a DB
        new DbExporter().export(users, "users_all_test.db");

        // Exportar a SQL
        new SqlExporter().export(users, "users_all_test.sql");

        System.out.println("✅ Test de TODOS los formatos completado");
        System.out.println("📂 Verifica la carpeta resources:");
        System.out.println("   - users_all_test.csv");
        System.out.println("   - users_all_test.db");
        System.out.println("   - users_all_test.sql");
        System.out.println();
    }
}