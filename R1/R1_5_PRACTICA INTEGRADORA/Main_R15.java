import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main_R15 {
    private static List<Book> bookList = new ArrayList<>();

    public static void main(String[] args) {
        Path path = Paths.get("C:/Users/ismae/OneDrive/Documentos/CURSOS/014 DAM/2 AÑO/Acceso a datos/EJERCICIOS/R1.5_PRACTICA_INTEGRADORA/book.json");

        List<Book> booksFromJSON = OperacionesJSON.leerArrayObjetosJson(path);

        if (booksFromJSON.isEmpty()) {
            Book book1 = new Book("978-0140449136", "The Odyssey", "Homer", 560, 1996);
            Book book2 = new Book("978-0451524935", "1984", "George Orwell", 328, 1950);
            Book book3 = new Book("978-0061120084", "To Kill a Mockingbird", "Harper Lee", 336, 2006);
            Book book4 = new Book("978-0743273565", "The Great Gatsby", "F. Scott Fitzgerald", 180, 2004);
            Book book5 = new Book("978-0307277671", "The Road", "Cormac McCarthy", 287, 2006);
            Book book6 = new Book("978-0385472579", "The Things They Carried", "Tim O'Brien", 246, 1990);
            Book book7 = new Book("978-0679783275", "Pride and Prejudice", "Jane Austen", 279, 2000);
            Book book8 = new Book("978-0451208637", "Crime and Punishment", "Fyodor Dostoevsky", 430, 2002);

            bookList.add(book1);
            bookList.add(book2);
            bookList.add(book3);
            bookList.add(book4);
            bookList.add(book5);
            bookList.add(book6);
            bookList.add(book7);
            bookList.add(book8);
        } else {
            bookList = booksFromJSON;
        }

        Scanner scanner = new Scanner(System.in);
        int option;
        do {
            System.out.println("\n--- Menú ---");
            System.out.println("1. Ver todos los libros");
            System.out.println("2. Buscar libro por título o autor");
            System.out.println("3. Agregar un nuevo libro");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    showBooks();
                    break;
                case 2:
                    searchBook(scanner);
                    break;
                case 3:
                    addBook(scanner, path);
                    break;
                case 4:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción inválida, intente nuevamente.");
            }
        } while (option != 4);

        scanner.close();
        OperacionesJSON.escribirListaObjetosJson(bookList, path);     }

    public static List<Book> searchBookByTitleOrAuthor(String titleOrAuthor) {
        return bookList.stream().filter(b -> b.getTitle().equals(titleOrAuthor) || b.getAuthor().equals(titleOrAuthor)).toList();
    }

    public static void addBook(Scanner scanner, Path path) {
        System.out.print("Ingrese el ISBN: ");
        String isbn = scanner.nextLine();

        System.out.print("Ingrese el título: ");
        String title = scanner.nextLine();

        System.out.print("Ingrese el autor: ");
        String author = scanner.nextLine();

        System.out.print("Ingrese el número de páginas: ");
        int numPages = scanner.nextInt();
        scanner.nextLine();  // Limpia newline

        System.out.print("Ingrese el año de publicación: ");
        int year = scanner.nextInt();
        scanner.nextLine();  // Limpia newline

        Book newBook = new Book(isbn, title, author, numPages, year);

        // ¡AQUÍ LLAMAMOS AL MÉTODO NUEVO!
        bookList = OperacionesJSON.añadirLibroObjetoJson(newBook, path);
    }

    public static void showBooks() {
        if (bookList.isEmpty()) {
            System.out.println("No hay libros almacenados.");
        } else {
            for (Book book : bookList) {
                System.out.println(book);
            }
        }
    }

    public static void searchBook(Scanner scanner) {
        System.out.print("Ingrese el título o autor para buscar: ");
        String query = scanner.nextLine();
        List<Book> resultados = searchBookByTitleOrAuthor(query);

        if (resultados.isEmpty()) {
            System.out.println("No se encontraron libros que coincidan con la búsqueda.");
        } else {
            System.out.println("Resultados de la búsqueda:");
            for (Book book : resultados) {
                System.out.println(book);
            }
        }
    }
}
