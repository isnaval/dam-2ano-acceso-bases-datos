import java.io.*;

public class R1_FicherosTexto_05 {

    private static final int DESPLAZAMIENTO = 3;
    private static final String NOMBRE_FICHERO = "fichero.txt";

    public static void main(String[] args) {

        encriptarArchivo(NOMBRE_FICHERO, "fichero_encriptado.txt");
        desencriptarArchivo("fichero_encriptado.txt", "fichero_desencriptado.txt");
    }

    public static void encriptarArchivo(String rutaEntrada, String rutaSalida) {
        procesarArchivo(rutaEntrada, rutaSalida, false);
    }

    public static void desencriptarArchivo(String rutaEntrada, String rutaSalida) {
        procesarArchivo(rutaEntrada, rutaSalida, true);
    }

    public static void procesarArchivo(String rutaEntrada, String rutaSalida, boolean estaEncriptado) {
        try {
            File archivoEntrada = new File(rutaEntrada);
            if (!archivoEntrada.exists() || !archivoEntrada.isFile()) {
                throw new FileNotFoundException();
            }

            File archivoSalida = new File(rutaSalida);
            BufferedReader lector = new BufferedReader(new FileReader(archivoEntrada));
            BufferedWriter escritor = new BufferedWriter(new FileWriter(archivoSalida));

            int datos;
            while ((datos = lector.read()) != -1) {
                char c = (char) datos;
                if (estaEncriptado)
                    escritor.write(c - DESPLAZAMIENTO);
                else
                    escritor.write(c + DESPLAZAMIENTO);
            }
            lector.close();
            escritor.close();

        } catch (FileNotFoundException e) {
            System.out.println("ERROR: el fichero no existe o no es un archivo válido");
        } catch (IOException e) {
            System.out.println("Ocurrió un error al procesar el archivo");
        }
    }
}
