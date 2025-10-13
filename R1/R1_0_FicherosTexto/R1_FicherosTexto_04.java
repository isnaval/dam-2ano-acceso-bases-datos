import java.io.*;
import java.util.Scanner;

public class R1_FicherosTexto_04 {

    public static void main(String[] args) {

        eliminarPalabras("archivo.txt", "Sd");
    }

    public static void eliminarPalabras(String nombreArchivo, String palabra) {
        try {
            File fichero = new File(nombreArchivo);
            if (!fichero.exists() || !fichero.isFile()) {
                throw new FileNotFoundException();
            }

            // lector del fichero origen
            BufferedReader br = new BufferedReader(new FileReader(nombreArchivo));
            Scanner scn = new Scanner(br);

            // escritor del fichero destino
            String nombreFicheroNuevo = nombreArchivo.replace(".txt", "_2.txt");
            BufferedWriter bw = new BufferedWriter(new FileWriter(nombreFicheroNuevo));

            String linea;
            while (scn.hasNextLine()) {
                linea = scn.nextLine().replaceAll(palabra, "");
                bw.write(linea + "\n");
            }
            scn.close();
            bw.close();

        } catch (FileNotFoundException e) {
            System.out.println("El archivo " + nombreArchivo + " no existe o no es un archivo válido");
        } catch (IOException e) {
            System.out.println("Ocurrió un error al procesar el archivo");
        }

    }
}
