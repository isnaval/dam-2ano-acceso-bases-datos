package R2_4_OpenAPI;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CsvExporter {

    // RUTA ABSOLUTA FIJA
    private static final String RESOURCES_PATH = "C:\\Users\\ismae\\OneDrive\\Documentos\\CURSOS\\014 DAM\\2 AÑO\\Acceso a datos\\EJERCICIOS\\R2-jdbc\\src\\main\\resources";

    public void export(List<User> users, String filename) throws IOException {
        File directory = new File(RESOURCES_PATH);

        // Crear carpeta si no existe
        if (!directory.exists()) {
            directory.mkdirs();
        }

        File file = new File(directory, filename);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            // Escribir encabezados
            writer.write("ID,Nombre,Usuario,Email,Teléfono,Website");
            writer.newLine();

            // Escribir datos
            for (User user : users) {
                writer.write(String.format("%d,\"%s\",\"%s\",\"%s\",\"%s\",\"%s\"",
                        user.getId(),
                        user.getName(),
                        user.getUsername(),
                        user.getEmail(),
                        user.getPhone(),
                        user.getWebsite()
                ));
                writer.newLine();
            }
        }

        System.out.println("OK: Archivo CSV guardado: " + file.getAbsolutePath());
        System.out.println("📊 Total de registros: " + users.size());
    }
}