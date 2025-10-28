package R1_6_OpenAPI;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            System.out.println("Conectando a la API...");
            ApiService api = new ApiService();
            List<Language> idiomas = api.obtenerIdiomas();
            System.out.println("✅ " + idiomas.size() + " idiomas cargados\n");

            LanguageOperations ops = new LanguageOperations();
            Scanner sc = new Scanner(System.in);
            int opcion;

            do {
                System.out.println("\n1. Ver todos\n2. Buscar nombre\n3. Buscar código\n4. Filtrar letra\n5. Salir");
                System.out.print("Opción: ");
                opcion = sc.nextInt();
                sc.nextLine();

                switch (opcion) {
                    case 1:
                        ops.mostrarTodos(idiomas);
                        break;
                    case 2:
                        System.out.print("Nombre: ");
                        List<Language> r1 = ops.buscarPorNombre(idiomas, sc.nextLine());
                        r1.forEach(System.out::println);
                        break;
                    case 3:
                        System.out.print("Código ISO: ");
                        Language r2 = ops.buscarPorCodigo(idiomas, sc.nextLine());
                        System.out.println(r2 != null ? r2 : "No encontrado");
                        break;
                    case 4:
                        System.out.print("Letra: ");
                        ops.filtrarPorLetra(idiomas, sc.nextLine().charAt(0)).forEach(System.out::println);
                        break;
                    case 5:
                        System.out.println("Adiós!");
                        break;
                }
            } while (opcion != 5);

            sc.close();
        } catch (IOException | InterruptedException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
