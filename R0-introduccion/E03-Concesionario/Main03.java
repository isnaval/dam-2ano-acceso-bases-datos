import model.Concesionario;
import model.Vehiculo;
import vehiculos.Camion;
import vehiculos.Coche;
import vehiculos.Moto;

import java.util.Scanner;

public class Main03 {
    private static Scanner scanner = new Scanner(System.in);
    private static Concesionario concesionario;

    public static void main(String[] args) {
        System.out.println("=== SISTEMA DE CONCESIONARIO ===\n");

        // Inicializar concesionario
        concesionario = new Concesionario("AutoMax");

        // Cargar vehículos de ejemplo
        cargarVehiculosEjemplo();

        // Menú principal
        boolean continuar = true;
        while (continuar) {
            mostrarMenu();
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    verInventario();
                    break;
                case 2:
                    agregarVehiculo();
                    break;
                case 3:
                    venderVehiculo();
                    break;
                case 4:
                    buscarVehiculo();
                    break;
                case 5:
                    verHistorial();
                    break;
                case 6:
                    continuar = false;
                    System.out.println("¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        }

        scanner.close();
    }

    private static void cargarVehiculosEjemplo() {
        // Añadir algunos vehículos de ejemplo
        concesionario.agregarVehiculo(
                new Coche("Toyota", "Corolla", "ABC-123", 25000, 2023, 4, "Gasolina")
        );
        concesionario.agregarVehiculo(
                new Coche("Honda", "Civic", "DEF-456", 28000, 2023, 4, "Híbrido")
        );
        concesionario.agregarVehiculo(
                new Moto("Yamaha", "R6", "GHI-789", 12000, 2022, 600, "Deportiva")
        );
        concesionario.agregarVehiculo(
                new Camion("Mercedes", "Actros", "JKL-012", 80000, 2021, 20.5, 3)
        );

        System.out.println("\n4 vehículos cargados en el sistema\n");
    }

    private static void mostrarMenu() {
        System.out.println("\n--- MENÚ PRINCIPAL ---");
        System.out.println("1. Ver inventario");
        System.out.println("2. Agregar vehículo");
        System.out.println("3. Vender vehículo");
        System.out.println("4. Buscar vehículo");
        System.out.println("5. Ver historial de ventas");
        System.out.println("6. Salir");
        System.out.print("Opción: ");
    }

    private static void verInventario() {
        concesionario.mostrarVehiculosDisponibles();
    }

    private static void agregarVehiculo() {
        System.out.println("\n--- AGREGAR VEHÍCULO ---");
        System.out.println("1. Coche");
        System.out.println("2. Moto");
        System.out.println("3. Camión");
        System.out.print("Tipo de vehículo: ");
        int tipo = scanner.nextInt();
        scanner.nextLine();

        // Datos comunes
        System.out.print("Marca: ");
        String marca = scanner.nextLine();
        System.out.print("Modelo: ");
        String modelo = scanner.nextLine();
        System.out.print("Matrícula: ");
        String matricula = scanner.nextLine();
        System.out.print("Precio: ");
        double precio = scanner.nextDouble();
        System.out.print("Año: ");
        int anio = scanner.nextInt();
        scanner.nextLine();

        Vehiculo nuevoVehiculo = null;

        switch (tipo) {
            case 1: // Coche
                System.out.print("Número de puertas: ");
                int puertas = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Tipo de combustible: ");
                String combustible = scanner.nextLine();
                nuevoVehiculo = new Coche(marca, modelo, matricula, precio, anio, puertas, combustible);
                break;

            case 2: // Moto
                System.out.print("Cilindrada: ");
                int cilindrada = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Tipo de moto: ");
                String tipoMoto = scanner.nextLine();
                nuevoVehiculo = new Moto(marca, modelo, matricula, precio, anio, cilindrada, tipoMoto);
                break;

            case 3: // Camión
                System.out.print("Capacidad de carga (toneladas): ");
                double carga = scanner.nextDouble();
                System.out.print("Número de ejes: ");
                int ejes = scanner.nextInt();
                scanner.nextLine();
                nuevoVehiculo = new Camion(marca, modelo, matricula, precio, anio, carga, ejes);
                break;
        }

        if (nuevoVehiculo != null) {
            concesionario.agregarVehiculo(nuevoVehiculo);
        }
    }

    private static void venderVehiculo() {
        System.out.println("\n--- VENDER VEHÍCULO ---");
        concesionario.mostrarVehiculosDisponibles();

        System.out.print("Matrícula del vehículo a vender: ");
        String matricula = scanner.nextLine();

        Vehiculo vehiculo = concesionario.buscarPorMatricula(matricula);
        if (vehiculo != null) {
            concesionario.mostrarDetallesVenta(vehiculo);

            System.out.print("Nombre del comprador: ");
            String comprador = scanner.nextLine();

            concesionario.vender(vehiculo, comprador);
        } else {
            System.out.println("Vehículo no encontrado");
        }
    }

    private static void buscarVehiculo() {
        System.out.print("\nIngrese matrícula a buscar: ");
        String matricula = scanner.nextLine();

        Vehiculo vehiculo = concesionario.buscarPorMatricula(matricula);
        if (vehiculo != null) {
            System.out.println("\nVehículo encontrado:");
            System.out.println(vehiculo);
            concesionario.mostrarDetallesVenta(vehiculo);
        } else {
            System.out.println("Vehículo no encontrado");
        }
    }

    private static void verHistorial() {
        concesionario.mostrarHistorialVentas();
    }
}
