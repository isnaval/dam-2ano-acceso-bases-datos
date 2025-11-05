package R3_3_ConsultasCompuestasORAndAge;

import R3_entities.Cliente;
import java.util.List;
import java.util.Scanner;

/**
 * Clase con funciones auxiliares para R3_3
 */
public class R3_3_AuxiliarConsultas {

    public static int leerEntero(Scanner scanner) {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public static String leerTexto(Scanner scanner, String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine().trim();
    }

    public static void mostrarClientes(List<Cliente> clientes) {
        if (clientes.isEmpty()) {
            System.out.println("No se encontraron resultados\n");
            return;
        }

        System.out.println("Encontrados: " + clientes.size() + " cliente(s)\n");
        System.out.println("-----------------------------------------------------");
        System.out.printf("%-5s %-15s %-15s %-5s%n", "ID", "NOMBRE", "APELLIDO", "EDAD");
        System.out.println("-----------------------------------------------------");

        for (Cliente c : clientes) {
            System.out.printf("%-5d %-15s %-15s %-5d%n",
                    c.getId(), c.getFirstName(), c.getLastName(), c.getEdad());
        }
        System.out.println("-----------------------------------------------------\n");
    }

    public static void mostrarEncabezado(String titulo) {
        System.out.println("\n" + "=".repeat(40));
        System.out.println("  " + titulo);
        System.out.println("=".repeat(40));
    }
}