package R3_4_BuscarCuentasPorApellidoCliente;

import R3_dao.ClienteDAOImpl;
import R3_dao.CuentaDAOImpl;
import R3_entities.Cliente;
import R3_entities.Cuenta;

import java.util.List;
import java.util.Scanner;

/**
 * Ejercicio R3_4: Buscar Cuentas Por Apellido del Cliente
 */
public class R3_4_BuscarCuentasPorApellidoCliente {

    private static final CuentaDAOImpl cuentaDao = new CuentaDAOImpl();
    private static final ClienteDAOImpl clienteDao = new ClienteDAOImpl();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== BUSCAR CUENTAS POR APELLIDO DEL CLIENTE ===\n");

        boolean continuar = true;
        while (continuar) {
            mostrarMenu();
            int opcion = leerEntero();

            switch (opcion) {
                case 1 -> buscarCuentasPorApellido();
                case 2 -> mostrarTodasLasCuentas();
                case 3 -> buscarCuentasPorCliente();
                case 4 -> crearCuentasDePrueba();
                case 0 -> continuar = false;
                default -> System.out.println("ERROR: Opción inválida");
            }
        }

        System.out.println("\nFIN");
        scanner.close();
    }
}
