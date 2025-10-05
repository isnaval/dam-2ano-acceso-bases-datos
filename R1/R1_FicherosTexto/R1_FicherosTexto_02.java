import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class R1_FicherosTexto_02 {
    public static void main(String[] args) {
        crearFicheros("ed.txt", 5);
    }

    public static void crearFicheros(String ruta, int n) {
        if (new File(ruta).isDirectory()) {
            for (int i = 1; i <= n; i++) {
                String nombreFichero = ruta + "\nnombre( " + i + ".txt";
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreFichero))) {
                    writer.write("Este es el fichero nombre(" + i + ").txt");
                } catch (IOException e) {
                    System.out.println("Error al crear archivo: " + e.getMessage());
                }
            }

        } else System.out.println("La ruta no es válida");

    }
}
