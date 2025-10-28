package src.test.R1_3_Deserializacion;

import org.testng.annotations.Test;
import src.main.java.R1_3_Deserializacion.JsonCsv;

import java.io.IOException;

public class JsonCsvTest {

    @Test
    void testReadJsonCsv() throws IOException {
        JsonCsv jsonCsv = new JsonCsv();
        jsonCsv.readJsonCsv("languages.json");

        System.out.println("==========================================");
        System.out.println("Test completado exitosamente");
        System.out.println("Verifica el archivo idiomas.csv generado");
        System.out.println("==========================================");
    }
}
