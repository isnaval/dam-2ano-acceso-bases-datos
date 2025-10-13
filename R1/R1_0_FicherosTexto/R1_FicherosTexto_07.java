import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class R1_FicherosTexto_07 {

    public static void main(String[] args) {
        String nombreArchivo1 = "archivo1.txt";
        String nombreArchivo2 = "archivo2.txt";
        String nombreArchivoResultado = "archivoCombinado.txt";

        try {
            combinarArchivos(nombreArchivo1, nombreArchivo2, nombreArchivoResultado);
            System.out.println("Se ha combinado el contenido de " + nombreArchivo1 + " y " + nombreArchivo2
                    + " en el archivo " + nombreArchivoResultado);
        } catch (IOException e) {
            System.out.println("Error al leer o escribir en los archivos: " + e.getMessage());
        }
    }


    public static void combinarArchivos(String nombreArchivo1, String nombreArchivo2, String nombreArchivoResultado)
            throws IOException {
        List<String> palabrasArchivo1 = obtenerPalabrasDeArchivo(nombreArchivo1);
        List<String> palabrasArchivo2 = obtenerPalabrasDeArchivo(nombreArchivo2);

        try (FileWriter escritorArchivo = new FileWriter(nombreArchivoResultado);
             BufferedWriter escritorBuffer = new BufferedWriter(escritorArchivo)) {

            int index1 = 0, index2 = 0;
            while (index1 < palabrasArchivo1.size() || index2 < palabrasArchivo2.size()) {
                if (index1 < palabrasArchivo1.size()) {
                    escritorBuffer.write(palabrasArchivo1.get(index1++) + " ");
                }
                if (index2 < palabrasArchivo2.size()) {
                    escritorBuffer.write(palabrasArchivo2.get(index2++) + " ");
                }
            }
        }
    }

    public static List<String> obtenerPalabrasDeArchivo(String nombreArchivo) throws IOException {
        List<String> palabras = new ArrayList<>();

        try (FileReader lectorArchivo = new FileReader(nombreArchivo);
             BufferedReader lectorBuffer = new BufferedReader(lectorArchivo)) {

            String linea;
            while ((linea = lectorBuffer.readLine()) != null) {
                String[] palabrasLinea = linea.split("\\s+");
                for (String palabra : palabrasLinea) {
                    if (!palabra.isEmpty()) {
                        palabras.add(palabra);
                    }
                }
                palabras.add("\n");
            }
        }

        return palabras;
    }
}
