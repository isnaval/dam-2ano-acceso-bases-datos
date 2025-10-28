package R1_4_ParsingJackson;

import org.junit.jupiter.api.Test;
import R1_4_ParsingJackson.JsonParserCsv;

import java.io.IOException;

class JsonParserCsvTest {

    @Test
    void testParseJsonToCsv() throws IOException {
        JsonParserCsv parser = new JsonParserCsv();

        parser.parseJsonToCsv("languages.json");

        System.out.println("==========================================");
        System.out.println("Test completado exitosamente");
        System.out.println("Verifica el archivo idiomas.csv generado");
        System.out.println("==========================================");
    }
}