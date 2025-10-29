package R2_5_DynamicAPI;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DynamicSqlExporter {

    private static final String RESOURCES_PATH = "C:\\Users\\ismae\\OneDrive\\Documentos\\CURSOS\\014 DAM\\2 AÑO\\Acceso a datos\\EJERCICIOS\\R2-jdbc\\src\\main\\resources";

    public void export(String jsonData, String filename) throws Exception {
        File directory = new File(RESOURCES_PATH);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        File file = new File(directory, filename);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(jsonData);

        if (!rootNode.isArray()) {
            rootNode = mapper.createArrayNode().add(rootNode);
        }

        if (rootNode.size() == 0) {
            System.out.println("WARNING: No hay datos para exportar");
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            // Encabezado
            writer.write("-- Script SQL generado dinamicamente");
            writer.newLine();
            writer.write("-- Fecha: " + java.time.LocalDateTime.now());
            writer.newLine();
            writer.newLine();

            // Analizar estructura
            JsonNode firstRecord = rootNode.get(0);
            List<String> fieldNames = new ArrayList<>();
            Iterator<String> fields = firstRecord.fieldNames();
            while (fields.hasNext()) {
                fieldNames.add(fields.next());
            }

            // Crear tabla
            writer.write("CREATE TABLE IF NOT EXISTS data (");
            writer.newLine();
            writer.write("    id INTEGER PRIMARY KEY AUTO_INCREMENT");
            for (String fieldName : fieldNames) {
                writer.write(",");
                writer.newLine();
                writer.write("    " + sanitizeFieldName(fieldName) + " TEXT");
            }
            writer.newLine();
            writer.write(");");
            writer.newLine();
            writer.newLine();

            // Insertar datos
            writer.write("-- Insertar datos");
            writer.newLine();

            SchemaAnalyzer analyzer = new SchemaAnalyzer();
            for (JsonNode record : rootNode) {
                writer.write("INSERT INTO data (");
                for (int i = 0; i < fieldNames.size(); i++) {
                    if (i > 0) writer.write(", ");
                    writer.write(sanitizeFieldName(fieldNames.get(i)));
                }
                writer.write(") VALUES (");
                for (int i = 0; i < fieldNames.size(); i++) {
                    if (i > 0) writer.write(", ");
                    JsonNode value = record.get(fieldNames.get(i));
                    writer.write("'" + escapeSql(analyzer.convertToString(value)) + "'");
                }
                writer.write(");");
                writer.newLine();
            }
        }

        System.out.println("OK: SQL guardado en " + file.getAbsolutePath());
        System.out.println("Total de statements INSERT generados: " + rootNode.size());
    }

    private String sanitizeFieldName(String name) {
        return name.replaceAll("[^a-zA-Z0-9_]", "_");
    }

    private String escapeSql(String value) {
        if (value == null) return "";
        return value.replace("'", "''");
    }
}