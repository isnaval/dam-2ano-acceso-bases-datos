package R2_5_DynamicAPI;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Iterator;

public class DynamicCsvExporter {

    private static final String RESOURCES_PATH = "C:\\Users\\ismae\\OneDrive\\Documentos\\CURSOS\\014 DAM\\2 AÑO\\Acceso a datos\\EJERCICIOS\\R2-jdbc\\src\\main\\resources";

    public void export(String jsonData, String filename) throws Exception {
        File directory = new File(RESOURCES_PATH);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        File file = new File(directory, filename);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(jsonData);

        // Si no es array, convertir a array de un elemento
        if (!rootNode.isArray()) {
            rootNode = mapper.createArrayNode().add(rootNode);
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            if (rootNode.size() == 0) {
                System.out.println("WARNING: No hay datos para exportar");
                return;
            }

            // Obtener nombres de columnas del primer registro
            JsonNode firstRecord = rootNode.get(0);
            Iterator<String> fieldNames = firstRecord.fieldNames();

            // Escribir encabezados
            boolean first = true;
            while (fieldNames.hasNext()) {
                if (!first) writer.write(",");
                writer.write("\"" + fieldNames.next() + "\"");
                first = false;
            }
            writer.newLine();

            // Escribir datos
            SchemaAnalyzer analyzer = new SchemaAnalyzer();
            for (JsonNode record : rootNode) {
                Iterator<JsonNode> values = record.elements();
                first = true;
                while (values.hasNext()) {
                    if (!first) writer.write(",");
                    JsonNode value = values.next();
                    writer.write("\"" + analyzer.convertToString(value).replace("\"", "\"\"") + "\"");
                    first = false;
                }
                writer.newLine();
            }
        }

        System.out.println("OK: CSV guardado en " + file.getAbsolutePath());
        System.out.println("Total de registros exportados: " + rootNode.size());
    }
}