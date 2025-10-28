package src.main.java.R1_6_OpenAPI;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;

public class ApiService {

    private static final String API_URL =
            "https://pkgstore.datahub.io/core/language-codes/language-codes_json/data/97607046542b532c395cf83df5185246/language-codes_json.json";


    public List<Language> obtenerIdiomas() throws IOException, InterruptedIOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("✅ Datos obtenidos de la API (Status: " + response.statusCode() + ")");

        ObjectMapper mapper = new ObjectMapper();
        Language[] languagesArray = mapper.readValue(response.body(), Language[].class);

        return Arrays.asList(languagesArray);    }
}
