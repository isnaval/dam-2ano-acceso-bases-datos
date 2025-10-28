package R1_5_GestionLibros;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OperacionesJSON {
    private static final String ARCHIVO = "libros.json";
    private ObjectMapper mapper;

    public OperacionesJSON () {
        this.mapper = new ObjectMapper();
        this.mapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    public void guardarLibros(List<Book> libros) throws IOException {
        mapper.writeValue(new File(ARCHIVO), libros);
        System.out.println("OK Libros guardados en " + ARCHIVO);
    }

    public List<Book> cargarLibros() throws IOException {
        File file = new File(ARCHIVO);
        if (!file.exists()) {
            System.out.println("ERROR: Archivo no existe. Creando lista vacía.");
            return new ArrayList<>();
        }
        Book[] booksArray = mapper.readValue(file, Book[].class);
        return new ArrayList<>(Arrays.asList(booksArray));
    }

    public List<Book> buscarLibros(List<Book> libros, String criterio) {
        List<Book> resultados = new ArrayList<>();
        String criterioLower = criterio.toLowerCase();

        for (Book libro : libros) {
            if (libro.getTitulo().toLowerCase().contains(criterioLower) ||
                    libro.getAutor().toLowerCase().contains(criterioLower)) {
                resultados.add(libro);
            }
        }
        return resultados;
    }

    // Verificar si un libro ya existe por ISBN
    public boolean libroExiste(List<Book> libros, String isbn) {
        for (Book libro : libros) {
            if (libro.getIsbn().equals(isbn)) {
                return true;
            }
        }
        return false;
    }

}
