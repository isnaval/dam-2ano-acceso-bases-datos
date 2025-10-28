package src.main.java.R1_3_Deserializacion;  // Paquete correcto y estándar

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.*;
import java.util.List;
import java.util.Map;

public class JsonCsv {

    public void readJsonCsv(String inputPath) throws IOException {
        // FIX: Carga desde resources/data/ (classpath de Maven)
        InputStream inputStream = getClass().getResourceAsStream("/data/" + inputPath);
        if (inputStream == null) {
            throw new IOException("Archivo no encontrado en resources: /data/" + inputPath);
        }

        StringBuilder jsonContent = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                jsonContent.append(line);
            }
        }
        System.out.println("Archivo JSON leído correctamente");

        ObjectMapper mapper = new ObjectMapper();
        List<Map<String, Object>> languages = mapper.readValue(  // Nota: "languajes" → "languages" (typo corregido)
                jsonContent.toString(),
                mapper.getTypeFactory().constructCollectionType(List.class, Map.class)
        );
        System.out.println("JSON deserializado. Total de registros: " + languages.size());

        int id = 1;
        // FIX: Escribe CSV en target/ (seguro en Maven; se crea en build)
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("target/idiomas.csv"))) {  // "writter" → "writer"
            for (Map<String, Object> language : languages) {
                String englishName = (String) language.get("English");
                if (englishName != null && !englishName.isEmpty()) {
                    writer.write(id + ". " + englishName);
                    writer.newLine();
                    id++;
                }
            }
        }
        System.out.println("OK -- CSV generado correctamente: target/idiomas.csv");  // "correctametne" → "correctamente"
        System.out.println("Total de idiomas procesados: " + (id - 1));
    }

    // Opcional: Main para probar solo
    public static void main(String[] args) throws IOException {
        new JsonCsv().readJsonCsv("languages.json");
    }
}