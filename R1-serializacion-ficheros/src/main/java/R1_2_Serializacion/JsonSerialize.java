package R1_2_Serializacion;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.io.IOException;

public class JsonSerialize {

    public void serializeCoche(Coche coche)throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(coche);
        System.out.println("Coche serializado: " + json);
        mapper.writeValue(new File("src/main/resources/coche.json"), coche);
        System.out.println("Fichero src/main/resources/coche.json creado");
    }

    public void serilizeCoches(List<Coche> coches) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(coches);
        System.out.println("Coches serializadoJsonSerializeTests: " + json);
        mapper.writeValue(new File("src/main/resources/coches.json"), coches);
        System.out.println("Fichero src/main/resources/coches.json creado");
    }
}
