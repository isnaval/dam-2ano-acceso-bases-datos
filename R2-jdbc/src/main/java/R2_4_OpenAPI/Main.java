package R2_4_OpenAPI;


import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {

            System.out.println("=== DESCARGADOR DE DATOS API ===");

            System.out.println();

            // 1. Obtener datos de la API
            ApiService apiService = new ApiService();
            List<User> users = apiService.fetchUsers();
            System.out.println();
            System.out.println("👥 Primeros 3 usuarios obtenidos:");
            users.stream().limit(3).forEach(System.out::println);

            // 2. Menú de formatos
            System.out.println();
                System.out.println("--- ELIGE EL FORMATO DE EXPORTACIÓN ---");
                    System.out.println();
            System.out.println("1. CSV  - Comma-Separated Values (Valores Separados por Comas)");
            System.out.println("2. DB   - Base de datos SQLite");
            System.out.println("3. SQL  - Script SQL");
            System.out.println("0. Salir");
            System.out.println();
            System.out.print("Selecciona una opción (0-3): ");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            System.out.println();

            switch (opcion) {
                case 1:
                    CsvExporter csvExporter = new CsvExporter();
                    csvExporter.export(users, "users.csv");
                    System.out.println("📄 Puedes abrir el archivo con Excel o cualquier editor de texto");
                    break;

                case 2:
                    DbExporter dbExporter = new DbExporter();
                    dbExporter.export(users, "users.db");
                    System.out.println("💾 Puedes abrir el archivo con DB Browser for SQLite");
                    break;

                case 3:
                    SqlExporter sqlExporter = new SqlExporter();
                    sqlExporter.export(users, "users.sql");
                    System.out.println("📝 Puedes ejecutar el script en cualquier base de datos SQL");
                    break;

                case 0:
                    System.out.println("¡Hasta luego!");
                    return;

                default:
                    System.out.println("ERROR: Opción no válida");
                    return;
            }

            System.out.println();
            System.out.println("OK: ¡Proceso completado con éxito!");

        } catch (Exception e) {
            System.err.println("ERROR: " + e.getMessage());
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}