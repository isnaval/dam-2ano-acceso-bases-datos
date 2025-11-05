package R3_4_BuscarCuentasPorApellidoCliente;

import R3_dao.ClienteDAOImpl;
import R3_dao.CuentaDAO;
import R3_dao.CuentaDAOImpl;
import R3_entities.Cliente;
import R3_entities.Cuenta;

import java.util.List;
import java.util.Scanner;


public class R3_4_AuxliarConsultas  {

    private static final Scanner scanner = new Scanner(System.in);
    private static final CuentaDAO cuentaDao = new CuentaDAOImpl();

    private static void mostrarMenu() {
        System.out.println("\n===========================================");
        System.out.println(" 1. Buscar cuentas por apellido");
        System.out.println(" 2. Ver todas las cuentas");
        System.out.println(" 3. Ver cuentas de un cliente");
        System.out.println(" 4. Crear cuentas de prueba");
        System.out.println(" 0. Salir");
        System.out.println("===========================================");
        System.out.print("Opción: ");
    }


    private static void buscarCuentasPorApellido() {
        System.out.println("\n========================================");
        System.out.println("  BUSCAR CUENTAS POR APELLIDO");
        System.out.println("========================================");

        System.out.print("Apellido del cliente: ");
        String apellido = scanner.nextLine().trim();

        if (apellido.isEmpty()) {
            System.out.println("ERROR: El apellido no puede estar vacío");
            return;
        }

        System.out.println("\nBuscando cuentas de: " + apellido + "...\n");

        List<Cuenta> cuentas = cuentaDao.findByClienteApellido(apellido);

        if (cuentas.isEmpty()) {
            System.out.println("No se encontraron cuentas para: " + apellido);
        } else {
            System.out.println("Encontradas " + cuentas.size() + " cuenta(s):\n");
            mostrarCuentas(cuentas);
        }
    }


    private static void mostrarTodasLasCuentas() {
        System.out.println("\n========================================");
        System.out.println("  TODAS LAS CUENTAS");
        System.out.println("========================================\n");

        List<Cuenta> cuentas = cuentaDao.findAll();

        if (cuentas.isEmpty()) {
            System.out.println("No hay cuentas en la base de datos");
        } else {
            System.out.println("Total: " + cuentas.size() + " cuenta(s)\n");
            mostrarCuentas(cuentas);
        }
    }


    private static void buscarCuentasPorCliente() {
        System.out.println("\n========================================");
        System.out.println("  CUENTAS POR CLIENTE (ID)");
        System.out.println("========================================");

        System.out.print("ID del cliente: ");
        long clienteId = leerEntero();

        Cliente cliente = clienteDao.findById(clienteId);

        if (cliente == null) {
            System.out.println("ERROR: No existe cliente con ID: " + clienteId);
            return;
        }

        System.out.println("\nCliente: " + cliente.getFirstName() + " " + cliente.getLastName());

        List<Cuenta> cuentas = cuentaDao.findByClienteId(clienteId);

        if (cuentas.isEmpty()) {
            System.out.println("Este cliente no tiene cuentas");
        } else {
            System.out.println("Cuentas: " + cuentas.size() + "\n");
            mostrarCuentas(cuentas);
        }
    }


    private static void crearCuentasDePrueba() {
        System.out.println("\n========================================");
        System.out.println("  CREAR CUENTAS DE PRUEBA");
        System.out.println("========================================\n");

        List<Cliente> clientes = clienteDao.findAll();

        if (clientes.isEmpty()) {
            System.out.println("ERROR: No hay clientes en la BD");
            System.out.println("Primero crea clientes");
            return;
        }

        int cuentasCreadas = 0;

        // Crear 2 cuentas para los primeros 3 clientes
        for (int i = 0; i < Math.min(3, clientes.size()); i++) {
            Cliente cliente = clientes.get(i);

            // Cuenta de ahorro
            Cuenta ahorro = new Cuenta(
                    "ES" + (1000 + i) + "0000000000",
                    1000.0 + (i * 500),
                    "AHORRO",
                    cliente
            );
            cuentaDao.create(ahorro);
            cuentasCreadas++;

            // Cuenta corriente
            Cuenta corriente = new Cuenta(
                    "ES" + (2000 + i) + "0000000000",
                    2000.0 + (i * 1000),
                    "CORRIENTE",
                    cliente
            );
            cuentaDao.create(corriente);
            cuentasCreadas++;
        }

        System.out.println("\n✅ Se crearon " + cuentasCreadas + " cuentas");
    }


    private static void mostrarCuentas(@org.jetbrains.annotations.NotNull List<Cuenta> cuentas) {
        System.out.println("-----------------------------------------------------------------------");
        System.out.printf("%-5s %-20s %-12s %-15s %-20s%n",
                "ID", "NUM. CUENTA", "SALDO", "TIPO", "CLIENTE");
        System.out.println("-----------------------------------------------------------------------");

        for (Cuenta c : cuentas) {
            System.out.printf("%-5d %-20s %-12.2f %-15s %-20s%n",
                    c.getId(),
                    c.getNumeroCuenta(),
                    c.getSaldo(),
                    c.getTipoCuenta(),
                    c.getCliente().getFirstName() + " " + c.getCliente().getLastName()
            );
        }
        System.out.println("-----------------------------------------------------------------------\n");
    }


    private static int leerEntero() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
