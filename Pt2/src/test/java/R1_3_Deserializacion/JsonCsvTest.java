package R1_3_Deserializacion;

import org.junit.jupiter.api.Test;

import java.io.IOException;

public class JsonCsvTest {
    @Test
    void testReadJsonCsv() throws IOException {
        JsonCsv jsonCsv = new JsonCsv();

        // Usar el nombre correcto del archivo
        jsonCsv.readJsonCsv("languages.json");

        System.out.println("==========================================");
        System.out.println("Test completado exitosamente");
        System.out.println("Verifica el archivo idiomas.csv generado");
        System.out.println("==========================================");
    }
}
