package R2_4_OpenAPI;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class SqlExporter {

    // ✅ RUTA ABSOLUTA FIJA
    private static final String RESOURCES_PATH = "C:\\Users\\ismae\\OneDrive\\Documentos\\CURSOS\\014 DAM\\2 AÑO\\Acceso a datos\\EJERCICIOS\\R2-jdbc\\src\\main\\resources";

    public void export(List<User> users, String filename) throws IOException {
        File directory = new File(RESOURCES_PATH);

        // Crear carpeta si no existe
        if (!directory.exists()) {
            directory.mkdirs();
        }

        File file = new File(directory, filename);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            // Encabezado
            writer.write("-- Script SQL generado desde API JSONPlaceholder");
            writer.newLine();
            writer.write("-- Fecha: " + java.time.LocalDateTime.now());
            writer.newLine();
            writer.newLine();

            // Crear tabla
            writer.write("CREATE TABLE IF NOT EXISTS users (");
            writer.newLine();
            writer.write("    id INTEGER PRIMARY KEY,");
            writer.newLine();
            writer.write("    name VARCHAR(255) NOT NULL,");
            writer.newLine();
            writer.write("    username VARCHAR(100) NOT NULL,");
            writer.newLine();
            writer.write("    email VARCHAR(255) NOT NULL,");
            writer.newLine();
            writer.write("    phone VARCHAR(50),");
            writer.newLine();
            writer.write("    website VARCHAR(255)");
            writer.newLine();
            writer.write(");");
            writer.newLine();
            writer.newLine();

            // Insertar datos
            writer.write("-- Insertar datos");
            writer.newLine();

            for (User user : users) {
                writer.write(String.format(
                        "INSERT INTO users (id, name, username, email, phone, website) VALUES (%d, '%s', '%s', '%s', '%s', '%s');",
                        user.getId(),
                        escapeSql(user.getName()),
                        escapeSql(user.getUsername()),
                        escapeSql(user.getEmail()),
                        escapeSql(user.getPhone()),
                        escapeSql(user.getWebsite())
                ));
                writer.newLine();
            }
        }

        System.out.println("✅ Script SQL guardado: " + file.getAbsolutePath());
        System.out.println("📝 Total de INSERT statements: " + users.size());
    }

    private String escapeSql(String value) {
        if (value == null) return "";
        return value.replace("'", "''");
    }
}