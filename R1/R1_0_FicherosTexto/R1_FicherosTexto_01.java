import java.io.File;

public class R1_FicherosTexto_01 {
    public static void main(String[] args) {
        String ruta = ".";
        String extension = "txt";
        listarPorExtension(ruta, extension);
    }

    public static void listarPorExtension(String ruta, String extension) {
        File carpeta = new File(ruta);
        System.out.println("Buscando en: " + carpeta.getAbsolutePath());
        System.out.println("Es directorio? " + carpeta.isDirectory());

        if (carpeta.isDirectory()) {
            File[] archivos = carpeta.listFiles((dir, name) -> name.toLowerCase().endsWith("." + extension.toLowerCase()));
            System.out.println("Número de archivos .txt encontrados: " + (archivos != null ? archivos.length : 0));  // <-- Y esto

            if (archivos != null && archivos.length > 0) {  // <-- Cambia a esto para mejor feedback
                for (File f : archivos) {
                    System.out.println("Archivo encontrado: " + f.getName());  // Corrige el typo
                }
            } else {
                System.out.println("No se encontraron archivos con la extensión " + extension + " en la carpeta " + ruta);
            }
        } else {
            System.out.println("La ruta proporcionada no es una carpeta valida");
        }
    }
}
