package R2_5_DynamicAPI;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DynamicDbExporter {

    private static final String RESOURCES_PATH = "C:\\Users\\ismae\\OneDrive\\Documentos\\CURSOS\\014 DAM\\2 AÑO\\Acceso a datos\\EJERCICIOS\\R2-jdbc\\src\\main\\resources";

    public void export(String jsonData, String filename) throws Exception {
        File directory = new File(RESOURCES_PATH);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        File dbFile = new File(directory, filename);
        String url = "jdbc:sqlite:" + dbFile.getAbsolutePath();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(jsonData);

        if (!rootNode.isArray()) {
            rootNode = mapper.createArrayNode().add(rootNode);
        }

        if (rootNode.size() == 0) {
            System.out.println("WARNING: No hay datos para exportar");
            return;
        }

        try (Connection conn = DriverManager.getConnection(url)) {
            // Analizar estructura
            JsonNode firstRecord = rootNode.get(0);
            List<String> fieldNames = new ArrayList<>();
            Iterator<String> fields = firstRecord.fieldNames();
            while (fields.hasNext()) {
                fieldNames.add(fields.next());
            }

            // Crear tabla dinámica evitando conflicto con "id"
            StringBuilder createTableSQL = new StringBuilder("CREATE TABLE IF NOT EXISTS data (");
            createTableSQL.append("record_id INTEGER PRIMARY KEY AUTOINCREMENT");

            for (String fieldName : fieldNames) {
                String sanitizedName = sanitizeFieldName(fieldName);
                // Si el campo original se llama "id", renombrarlo a "original_id"
                if (sanitizedName.equalsIgnoreCase("id")) {
                    sanitizedName = "original_id";
                }
                createTableSQL.append(", ").append(sanitizedName).append(" TEXT");
            }
            createTableSQL.append(")");

            try (Statement stmt = conn.createStatement()) {
                stmt.execute(createTableSQL.toString());
                System.out.println("OK: Tabla 'data' creada");
            }

            // Insertar datos con los nombres corregidos
            StringBuilder insertSQL = new StringBuilder("INSERT INTO data (");
            for (int i = 0; i < fieldNames.size(); i++) {
                if (i > 0) insertSQL.append(", ");
                String sanitizedName = sanitizeFieldName(fieldNames.get(i));
                if (sanitizedName.equalsIgnoreCase("id")) {
                    sanitizedName = "original_id";
                }
                insertSQL.append(sanitizedName);
            }
            insertSQL.append(") VALUES (");
            for (int i = 0; i < fieldNames.size(); i++) {
                if (i > 0) insertSQL.append(", ");
                insertSQL.append("?");
            }
            insertSQL.append(")");

            SchemaAnalyzer analyzer = new SchemaAnalyzer();
            try (PreparedStatement pstmt = conn.prepareStatement(insertSQL.toString())) {
                for (JsonNode record : rootNode) {
                    for (int i = 0; i < fieldNames.size(); i++) {
                        JsonNode value = record.get(fieldNames.get(i));
                        pstmt.setString(i + 1, analyzer.convertToString(value));
                    }
                    pstmt.executeUpdate();
                }
            }

            System.out.println("OK: Base de datos guardada en " + dbFile.getAbsolutePath());
            System.out.println("Total de registros insertados: " + rootNode.size());
        }
    }

    private String sanitizeFieldName(String name) {
        return name.replaceAll("[^a-zA-Z0-9_]", "_");
    }
}