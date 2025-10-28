package R1_2_Serializacion;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

class JsonSerializeTest {

    @Test
    void testSerializeCoche() throws IOException {
        // Crear instancia del serializador
        JsonSerialize serializer = new JsonSerialize();

        // Crear un coche de ejemplo
        Coche coche = new Coche(1L, "Corolla", "Toyota");

        // Serializar a JSON
        serializer.serializeCoche(coche);

        System.out.println("==========================================");
        System.out.println("Test de serialización de coche completado");
        System.out.println("Verifica el archivo: coche.json");
        System.out.println("==========================================");
    }

    @Test
    void testSerializeCoches() throws IOException {
        // Crear instancia del serializador
        JsonSerialize serializer = new JsonSerialize();

        // Crear lista de coches
        List<Coche> coches = Arrays.asList(
                new Coche(1L, "Civic", "Honda"),
                new Coche(2L, "Focus", "Ford"),
                new Coche(3L, "Serie 3", "BMW")
        );

        // Serializar lista a JSON
        serializer.serilizeCoches(coches);

        System.out.println("==========================================");
        System.out.println("Test de serialización de coches completado");
        System.out.println("Verifica el archivo: coches.json");
        System.out.println("==========================================");
    }
}