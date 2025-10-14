package R1_2_Serializacion;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

class JsonSerializeTest {

    @Test
    void testSerializeCoche() throws IOException {
        Coche coche = new Coche(1L, "Clio", "Renault");
        JsonSerialize serializer = new JsonSerialize();
        serializer.serializeCoche(coche);
    }

    @Test
    void testSerializeCoches() throws IOException {
        List<Coche> coches = Arrays.asList(
                new Coche(1L, "Clio", "Renault"),
                new Coche(2L, "Golf", "Volkswagen"),
                new Coche(3L, "Corolla", "Toyota")
        );
        JsonSerialize serializer = new JsonSerialize();
        serializer.serilizeCoches(coches);
    }
}
