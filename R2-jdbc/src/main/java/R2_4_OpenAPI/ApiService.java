package R2_4_OpenAPI;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class ApiService {

    private static final String API_URL = "https://jsonplaceholder.typicode.com/users";

    public List<User> fetchUsers() throws IOException {
        System.out.println("📡 Conectando a la API: " + API_URL);

        // Conectar a la API
        URL url = new URL(API_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);

        // Leer respuesta
        StringBuilder response = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(connection.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        }

        System.out.println("OK: Datos recibidos de la API");

        // Convertir JSON a objetos User
        ObjectMapper mapper = new ObjectMapper();
        List<User> users = mapper.readValue(
                response.toString(),
                mapper.getTypeFactory().constructCollectionType(List.class, User.class)
        );

        System.out.println("OK: Total de usuarios obtenidos: " + users.size());
        return users;
    }
}