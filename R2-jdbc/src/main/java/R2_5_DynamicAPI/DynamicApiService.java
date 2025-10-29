package R2_5_DynamicAPI;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DynamicApiService {

    public String fetchData(String apiUrl) throws IOException {
        System.out.println("Conectando a: " + apiUrl);

        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(10000);
        connection.setReadTimeout(10000);

        StringBuilder response = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(connection.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        }

        System.out.println("OK: Datos recibidos correctamente");
        return response.toString();
    }

    public JsonNode parseToJsonNode(String jsonData) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readTree(jsonData);
    }

    public int countRecords(String jsonData) throws Exception {
        JsonNode node = parseToJsonNode(jsonData);
        if (node.isArray()) {
            return node.size();
        }
        return 1;
    }
}