package R1_3_Deserializacion;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.List;
import java.util.Map;

public class JsonCsv {
    public void readJsonCsv(String inputPath) throws IOException {
        StringBuilder jsonContent = new StringBuilder();
        try(BufferedReader reader = new BufferedReader(new FileReader(inputPath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                jsonContent.append(line);
            }
        }
        System.out.println("Archivo JSON leído correctamente");

        ObjectMapper mapper = new ObjectMapper();
        List<Map<String, Object>> languajes = mapper.readValue(
            jsonContent.toString(), List.class);

        System.out.println("JSON deserializado. Total de registros: "+ languajes.size());

        int id = 1;
        try (BufferedWriter  writter = new BufferedWriter(new FileWriter("idiomas.csv"))) {
            for (Map <String, Object> language: languajes) {
                String englishName = (String) language.get("English");
                if(englishName != null && !englishName.isEmpty()){
                    writter.write(id + ". " + englishName);
                    writter.newLine();
                    id++;
                }
            }
        }

        System.out.println(" OK -- CSV generado correctametne: idiomas.csv");
        System.out.println(" Total de idiomas procesados: " + (id -1));
    }

}
