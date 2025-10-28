package R1_4_ParsingJackson;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.io.*;

public class JsonParserCsv {

    public void parseJsonToCsv(String inputPath) throws IOException {
        StringBuilder jsonContent = new StringBuilder();

        InputStream inputStream = getClass().getResourceAsStream("/" + inputPath);

        if (inputStream == null) {
            throw new IOException("Archivo no encontrado en resources: /" + inputPath);
        }

        try(BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while((line = reader.readLine()) != null) {
                jsonContent.append(line);
            }
        }

        System.out.println("Archivo JSON leído correctamente");

        ObjectMapper mapper = new ObjectMapper();
        ArrayNode arrayNode = (ArrayNode) mapper.readTree(jsonContent.toString());
        System.out.println("JSON parseado. Total de nodos: " + arrayNode.size());

        int id = 1;
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("target/idiomas_parser.csv"))) {
            for (JsonNode node : arrayNode) {
                JsonNode englishNode = node.get("English");

                if(englishNode != null) {
                    String englishName = englishNode.asText();
                    if(!englishName.isEmpty()) {
                        writer.write(id + ". " + englishName);
                        writer.newLine();
                        id++;
                    }
                }
            }
        }
        System.out.println("CSV generado correctamente: target/idiomas_parser.csv");
        System.out.println("Total de idiomas procesados: " + (id - 1));
    }
}