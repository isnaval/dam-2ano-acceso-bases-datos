package R2_5_DynamicAPI;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("==========================================");
            System.out.println("   API DINAMICA - Exportador Universal");
            System.out.println("   Funciona con CUALQUIER API REST");
            System.out.println("==========================================");
            System.out.println();

            // Solicitar URL de la API
            System.out.println("Ejemplos de APIs publicas:");
            System.out.println("1. https://jsonplaceholder.typicode.com/users");
            System.out.println("2. https://jsonplaceholder.typicode.com/posts");
            System.out.println("3. https://api.github.com/users/octocat");
            System.out.println("4. https://catfact.ninja/facts?limit=10");
            System.out.println("5. https://randomuser.me/api/?results=5");
            System.out.println();
            System.out.print("Introduce la URL de la API: ");
            String apiUrl = scanner.nextLine().trim();

            if (apiUrl.isEmpty()) {
                apiUrl = "https://jsonplaceholder.typicode.com/posts";
                System.out.println("INFO: Usando API por defecto - " + apiUrl);
            }

            System.out.println();

            // Obtener datos
            DynamicApiService apiService = new DynamicApiService();
            String jsonData = apiService.fetchData(apiUrl);
            int recordCount = apiService.countRecords(jsonData);

            System.out.println("Total de registros detectados: " + recordCount);

            // Analizar esquema
            SchemaAnalyzer analyzer = new SchemaAnalyzer();
            List<SchemaAnalyzer.FieldInfo> schema = analyzer.analyzeSchema(jsonData);

            System.out.println("\nCampos detectados en el JSON:");
            for (SchemaAnalyzer.FieldInfo field : schema) {
                System.out.println("  - " + field.name + " (" + field.type + ")");
            }

            // Menú de formatos
            System.out.println();
            System.out.println("==========================================");
            System.out.println("   ELIGE EL FORMATO DE EXPORTACION");
            System.out.println("==========================================");
            System.out.println();
            System.out.println("1. CSV  - Comma-Separated Values");
            System.out.println("2. DB   - Base de datos SQLite");
            System.out.println("3. SQL  - Script SQL");
            System.out.println("0. Salir");
            System.out.println();
            System.out.print("Selecciona una opcion (0-3): ");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            System.out.println();

            switch (opcion) {
                case 1:
                    DynamicCsvExporter csvExporter = new DynamicCsvExporter();
                    csvExporter.export(jsonData, "dynamic_data.csv");
                    System.out.println("INFO: Archivo compatible con Excel");
                    break;

                case 2:
                    DynamicDbExporter dbExporter = new DynamicDbExporter();
                    dbExporter.export(jsonData, "dynamic_data.db");
                    System.out.println("INFO: Abrir con DB Browser for SQLite");
                    break;

                case 3:
                    DynamicSqlExporter sqlExporter = new DynamicSqlExporter();
                    sqlExporter.export(jsonData, "dynamic_data.sql");
                    System.out.println("INFO: Ejecutar en MySQL, PostgreSQL, etc.");
                    break;

                case 0:
                    System.out.println("Saliendo del programa");
                    return;

                default:
                    System.out.println("ERROR: Opcion no valida");
                    return;
            }

            System.out.println();
            System.out.println("==========================================");
            System.out.println("Proceso completado exitosamente");
            System.out.println("==========================================");

        } catch (Exception e) {
            System.err.println("ERROR: " + e.getMessage());
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}