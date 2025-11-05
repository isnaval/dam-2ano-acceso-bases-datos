package R3_3_ConsultasCompuestasORAndAge;

import R3_dao.ClienteDAOImpl;
import R3_entities.Cliente;
import java.util.List;
import java.util.Scanner;

/**
 * Ejercicio R3_3: Consultas Compuestas con OR y AND
 */
public class R3_3_ConsultasCompuestasORAndAge {

    private static final ClienteDAOImpl dao = new ClienteDAOImpl();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== CONSULTAS COMPUESTAS (OR / AND) ===\n");

        boolean continuar = true;
        while (continuar) {
            mostrarMenu();
            int opcion = R3_3_AuxiliarConsultas.leerEntero(scanner);

            switch (opcion) {
                case 1 -> consultaApellidoOR();
                case 2 -> consultaApellidoAndEdadMayor();
                case 3 -> consultaApellidoAndEdadMenor();
                case 4 -> consultaRangoEdad();
                case 5 -> consultaApellidoOrEdadMayor();
                case 6 -> mostrarTodos();
                case 0 -> continuar = false;
                default -> System.out.println("ERROR: Opción inválida");
            }
        }

        System.out.println("\nFIN");
        scanner.close();
    }

    // ========== MENÚ ==========

    private static void mostrarMenu() {
        System.out.println("\n===========================================");
        System.out.println(" 1. Apellido1 OR Apellido2");
        System.out.println(" 2. Apellido AND Edad Mayor");
        System.out.println(" 3. Apellido AND Edad Menor");
        System.out.println(" 4. Edad en Rango");
        System.out.println(" 5. Apellido OR Edad Mayor");
        System.out.println(" 6. Ver Todos");
        System.out.println(" 0. Salir");
        System.out.println("===========================================");
        System.out.print("Opción: ");
    }

    // ========== CONSULTAS PRINCIPALES ==========

    /**
     * 1. Buscar por apellido1 OR apellido2
     */
    private static void consultaApellidoOR() {
        R3_3_AuxiliarConsultas.mostrarEncabezado("Apellido1 OR Apellido2");

        String ap1 = R3_3_AuxiliarConsultas.leerTexto(scanner, "Primer apellido: ");
        String ap2 = R3_3_AuxiliarConsultas.leerTexto(scanner, "Segundo apellido: ");

        if (ap1.isEmpty() || ap2.isEmpty()) {
            System.out.println("ERROR: Ambos apellidos son obligatorios");
            return;
        }

        System.out.println("\nBuscando: " + ap1 + " OR " + ap2);
        List<Cliente> clientes = dao.findByLastNameOR(ap1, ap2);
        R3_3_AuxiliarConsultas.mostrarClientes(clientes);
    }

    /**
     * 2. Buscar por apellido AND edad mayor
     */
    private static void consultaApellidoAndEdadMayor() {
        R3_3_AuxiliarConsultas.mostrarEncabezado("Apellido AND Edad > X");

        String apellido = R3_3_AuxiliarConsultas.leerTexto(scanner, "Apellido: ");
        System.out.print("Edad mayor que: ");
        int edad = R3_3_AuxiliarConsultas.leerEntero(scanner);

        if (apellido.isEmpty() || edad < 0) {
            System.out.println("ERROR: Datos inválidos");
            return;
        }

        System.out.println("\nBuscando: " + apellido + " AND edad > " + edad);
        List<Cliente> clientes = dao.findByLastNameAndAgeGreaterThan(apellido, edad);
        R3_3_AuxiliarConsultas.mostrarClientes(clientes);
    }

    /**
     * 3. Buscar por apellido AND edad menor
     */
    private static void consultaApellidoAndEdadMenor() {
        R3_3_AuxiliarConsultas.mostrarEncabezado("Apellido AND Edad < X");

        String apellido = R3_3_AuxiliarConsultas.leerTexto(scanner, "Apellido: ");
        System.out.print("Edad menor que: ");
        int edad = R3_3_AuxiliarConsultas.leerEntero(scanner);

        if (apellido.isEmpty() || edad < 0) {
            System.out.println("ERROR: Datos inválidos");
            return;
        }

        System.out.println("\nBuscando: " + apellido + " AND edad < " + edad);
        List<Cliente> clientes = dao.findByLastNameAndAgeLessThan(apellido, edad);
        R3_3_AuxiliarConsultas.mostrarClientes(clientes);
    }

    /**
     * 4. Buscar por rango de edad
     */
    private static void consultaRangoEdad() {
        R3_3_AuxiliarConsultas.mostrarEncabezado("Edad BETWEEN X AND Y");

        System.out.print("Edad mínima: ");
        int min = R3_3_AuxiliarConsultas.leerEntero(scanner);
        System.out.print("Edad máxima: ");
        int max = R3_3_AuxiliarConsultas.leerEntero(scanner);

        if (min < 0 || max < 0 || min > max) {
            System.out.println("ERROR: Rango inválido");
            return;
        }

        System.out.println("\nBuscando edad entre " + min + " y " + max);
        List<Cliente> clientes = dao.findByAgeBetween(min, max);
        R3_3_AuxiliarConsultas.mostrarClientes(clientes);
    }

    /**
     * 5. Buscar por apellido OR edad mayor
     */
    private static void consultaApellidoOrEdadMayor() {
        R3_3_AuxiliarConsultas.mostrarEncabezado("Apellido OR Edad > X");

        String apellido = R3_3_AuxiliarConsultas.leerTexto(scanner, "Apellido: ");
        System.out.print("O edad mayor que: ");
        int edad = R3_3_AuxiliarConsultas.leerEntero(scanner);

        if (apellido.isEmpty() || edad < 0) {
            System.out.println("ERROR: Datos inválidos");
            return;
        }

        System.out.println("\nBuscando: " + apellido + " OR edad > " + edad);
        List<Cliente> clientes = dao.findByLastNameOrAgeGreaterThan(apellido, edad);
        R3_3_AuxiliarConsultas.mostrarClientes(clientes);
    }

    /**
     * 6. Mostrar todos los clientes
     */
    private static void mostrarTodos() {
        R3_3_AuxiliarConsultas.mostrarEncabezado("TODOS LOS CLIENTES");
        List<Cliente> clientes = dao.findAll();
        R3_3_AuxiliarConsultas.mostrarClientes(clientes);
    }
}