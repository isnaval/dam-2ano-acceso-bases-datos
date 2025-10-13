import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class R1_FicherosTexto_08 {
    public static void main(String[] args) {
        List<String> archivos = Arrays.asList("archivo1.txt", "archivo2.txt", "archivo3.txt");
        String nombreArchivoResultado = "archivoCombinado.txt";

        try {
            combinarArchivos(archivos, nombreArchivoResultado);
            System.out.println("Se ha combinado el contenido de los archivos en el archivo " + nombreArchivoResultado);
        } catch (IOException e) {
            System.out.println("Error al leer o escribir en los archivos: " + e.getMessage());
        }
    }


    public static void combinarArchivos(List<String> nombresArchivos, String nombreArchivoResultado)
            throws IOException {
        List<List<String>> palabrasArchivos = new ArrayList<>();

        for (String nombreArchivo : nombresArchivos) {
            palabrasArchivos.add(obtenerPalabrasDeArchivo(nombreArchivo));
        }

        try (FileWriter escritorArchivo = new FileWriter(nombreArchivoResultado);
             BufferedWriter escritorBuffer = new BufferedWriter(escritorArchivo)) {

            boolean hayPalabras = true;
            while (hayPalabras) {
                hayPalabras = false;
                for (List<String> palabrasArchivo : palabrasArchivos) {
                    if (!palabrasArchivo.isEmpty()) {
                        escritorBuffer.write(palabrasArchivo.remove(0) + " ");
                        hayPalabras = true;
                    }
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
