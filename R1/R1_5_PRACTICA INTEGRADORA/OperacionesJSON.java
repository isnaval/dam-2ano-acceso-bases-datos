import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.util.Scanner;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.ArrayList;

public class OperacionesJSON {
    public static List<Book> leerArrayObjetosJson(Path ruta) {
        if (!Files.exists(ruta)) {
            System.out.println("Archivo 'book.json' no existe. Iniciando con lista vacía.");
            return new ArrayList<>();
        }

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(ruta.toFile(), new TypeReference<List<Book>>() {
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void escribirListaObjetosJson(List<Book> books, Path ruta) {
        try {
            Files.deleteIfExists(ruta);
            ObjectMapper objectMapper = new ObjectMapper();
            // La siguiente línea es opcional, pero hace que el JSON se muestre con formato
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            objectMapper.writeValue(ruta.toFile(), books);
        } catch (IOException e) {
            System.out.println("El fichero no existe");
        }
    }

    public static List<Book> añadirLibroObjetoJson(Book nuevoLibro, Path ruta) {
        List<Book> listaActual = leerArrayObjetosJson(ruta);

        for (Book book : listaActual) {
            if (book.getIsbn().equals(nuevoLibro.getIsbn())) {
                System.out.println("El libro con ISBN '" + nuevoLibro.getIsbn() + "' ya existe. No se agrega.");
                return listaActual;
            }
        }

        listaActual.add(nuevoLibro);
        System.out.println("Libro '" + nuevoLibro.getTitle() + "' agregado a la lista.");

        // FIX: Llama al método de escritura, no recursivo
        escribirListaObjetosJson(listaActual, ruta);
        System.out.println("Lista actualizada guardada en 'book.json' (total: " + listaActual.size() + " libros).");

        return listaActual;
    }


}