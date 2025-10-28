package R1_5_GestionLibros;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static OperacionesJSON operaciones = new OperacionesJSON();
    private static List<Book> libros;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            libros = operaciones.cargarLibros();

            if (libros.isEmpty()) {
                libros.add(new Book("978-1", "Harry Potter", "J.K. Rowling", 223, 1997));
                libros.add(new Book("978-2", "1984", "George Orwell", 326, 1949));
                operaciones.guardarLibros(libros);
            }

            menu();
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private static void menu() throws IOException {
        int opcion;

        do {
            System.out.println("\n=== GESTIÓN DE LIBROS ===");
            System.out.println("1. Ver libros\n2. Buscar\n3. Agregar\n4. Salir");
            System.out.print("Opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1: verLibros(); break;
                case 2: buscar(); break;
                case 3: agregar(); break;
                case 4:
                    operaciones.guardarLibros(libros);
                    System.out.println("Guardado. Adiós!");
                    break;
                default: System.out.println("Opción inválida");
            }
        } while (opcion != 4);

        scanner.close();
    }
    private static void verLibros() {
        System.out.println("\n=== LIBROS (" + libros.size() + ") ===");
        for (int i = 0; i < libros.size(); i++) {
            System.out.println((i + 1) + ". " + libros.get(i));
        }
    }

    private static void buscar() {
        System.out.print("Buscar (título/autor): ");
        String criterio = scanner.nextLine();

        List<Book> resultados = operaciones.buscarLibros(libros, criterio);

        if (resultados.isEmpty()) {
            System.out.println("Sin resultados.");
        } else {
            resultados.forEach(System.out::println);
        }
    }

    private static void agregar() throws IOException {
        System.out.print("ISBN: ");
        String isbn = scanner.nextLine();

        if (operaciones.libroExiste(libros, isbn)) {
            System.out.println("ISBN ya existe.");
            return;
        }

        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Autor: ");
        String autor = scanner.nextLine();
        System.out.print("Páginas: ");
        int paginas = scanner.nextInt();
        System.out.print("Año: ");
        int ano = scanner.nextInt();
        scanner.nextLine();

        libros.add(new Book(isbn, titulo, autor, paginas, ano));
        System.out.println("Libro agregado.");
    }


}
