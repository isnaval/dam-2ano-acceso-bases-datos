import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class R1_FicherosTexto_03 {
    public static void main(String[] args) {
        cantidadPalabras("archivo.txt", "88");
    }

    public static void cantidadPalabras(String archivo, String palabra) {
        int contador = 0;

        try {
            BufferedReader br = new BufferedReader(new FileReader(archivo));
            Scanner scn = new Scanner(br);
            String linea = null;
            while ((linea = br.readLine()) != null) {
                String[] palabrasLinea = scn.nextLine().split("\\s+");
                for (String palabraActual : palabrasLinea) {
                    if (palabraActual.equals(palabra)) {  // Usa 'palabra'
                        contador++;
                    }
                }
            }

        } catch (IOException e) {
            System.out.println("Ocurrió un error al leer el archivo " + archivo + ": " + e.getMessage());
            return;
        }
        System.out.println("La palabra '" + palabra + "' aparece " + contador + " veces en el archivo.");
    }
}
