package R1_3_Deserializacion;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.*;
import java.util.List;
import java.util.Map;

public class JsonCsv {

    public void readJsonCsv(String inputPath) throws IOException {
        // ✅ CORRECTO: Busca directamente en resources/ (SIN /data/)
        InputStream inputStream = getClass().getResourceAsStream("/" + inputPath);

        if (inputStream == null) {
            throw new IOException("Archivo no encontrado en resources: /" + inputPath);
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
        List<Map<String, Object>> languages = mapper.readValue(
                jsonContent.toString(),
                mapper.getTypeFactory().constructCollectionType(List.class, Map.class)
        );
        System.out.println("JSON deserializado. Total de registros: " + languages.size());

        int id = 1;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("target/idiomas.csv"))) {
            for (Map<String, Object> language : languages) {
                String englishName = (String) language.get("English");
                if (englishName != null && !englishName.isEmpty()) {
                    writer.write(id + ". " + englishName);
                    writer.newLine();
                    id++;
                }
            }
        }
        System.out.println("OK -- CSV generado correctamente: target/idiomas.csv");
        System.out.println("Total de idiomas procesados: " + (id - 1));
    }

    public static void main(String[] args) throws IOException {
        new JsonCsv().readJsonCsv("languages.json");
    }
}