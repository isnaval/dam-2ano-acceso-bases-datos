import model.Carrito;
import model.Producto;
import model.Supermercado;
import productos.Alimento;
import productos.Bebida;

import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static Supermercado supermercado;
    private static Carrito carrito;

    public static void main(String[] args) {
        System.out.println("=== SUPERMERCADO ===\n");

        supermercado = new Supermercado();
        carrito = new Carrito();
        cargarProductos();

        boolean continuar = true;
        while (continuar) {
            mostrarMenu();
            int opcion = scanner.nextInt();
            scanner.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1:
                    verProductos();
                    break;
                case 2:
                    agregarAlCarrito();
                    break;
                case 3:
                    verCarrito();
                    break;
                case 4:
                    quitarDelCarrito();
                    break;
                case 5:
                    pagar();
                    break;
                case 6:
                    verVentasTotales();
                    break;
                case 7:
                    continuar = false;
                    System.out.println("¡Gracias por su visita!");
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        }

        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println("\n--- MENÚ PRINCIPAL ---");
        System.out.println("1. Ver productos");
        System.out.println("2. Agregar al carrito");
        System.out.println("3. Ver carrito");
        System.out.println("4. Quitar del carrito");
        System.out.println("5. Pagar");
        System.out.println("6. Ver ventas totales");
        System.out.println("7. Salir");
        System.out.print("Opción: ");
    }

    private static void cargarProductos() {
        // Alimentos
        supermercado.agregarProducto(
                new Alimento("A01", "Leche", 1.50, 20, "15/01/2025", "Lácteos")
        );
        supermercado.agregarProducto(
                new Alimento("A02", "Pan", 0.90, 30, "10/01/2025", "Panadería")
        );
        supermercado.agregarProducto(
                new Alimento("A03", "Manzanas", 2.00, 15, "20/01/2025", "Frutas")
        );
        supermercado.agregarProducto(
                new Alimento("A04", "Pollo", 5.50, 10, "12/01/2025", "Carnes")
        );

        // Bebidas
        supermercado.agregarProducto(
                new Bebida("B01", "Coca Cola", 1.80, 50, 2.0, "Refresco")
        );
        supermercado.agregarProducto(
                new Bebida("B02", "Agua", 0.60, 100, 1.5, "Agua")
        );
        supermercado.agregarProducto(
                new Bebida("B03", "Zumo Naranja", 2.50, 25, 1.0, "Jugo")
        );
        supermercado.agregarProducto(
                new Bebida("B04", "Cerveza", 1.20, 40, 0.33, "Alcohol")
        );

        // Productos básicos
        supermercado.agregarProducto(
                new Producto("P01", "Papel Higiénico", 3.50, 40)
        );
        supermercado.agregarProducto(
                new Producto("P02", "Detergente", 4.80, 20)
        );

        System.out.println("10 productos cargados en el sistema\n");
    }

    private static void verProductos() {
        supermercado.mostrarInventario();
    }

    private static void agregarAlCarrito() {
        System.out.print("\nCódigo del producto: ");
        String codigo = scanner.nextLine();

        Producto producto = supermercado.buscarProducto(codigo);
        if (producto != null) {
            System.out.println("\nProducto encontrado:");
            System.out.println(producto);
            System.out.print("Cantidad a comprar: ");
            int cantidad = scanner.nextInt();
            scanner.nextLine();

            if (cantidad > 0 && cantidad <= producto.getStock()) {
                carrito.agregarProducto(producto, cantidad);
            } else if (cantidad > producto.getStock()) {
                System.out.println("Stock insuficiente. Disponible: " + producto.getStock());
            } else {
                System.out.println("Cantidad no válida");
            }
        } else {
            System.out.println("Producto no encontrado");
        }
    }

    private static void verCarrito() {
        carrito.mostrarCarrito();
        if (!carrito.estaVacio()) {
            System.out.println("Productos en carrito: " + carrito.getCantidadProductos());
        }
    }

    private static void quitarDelCarrito() {
        if (carrito.estaVacio()) {
            System.out.println("El carrito está vacío");
            return;
        }

        carrito.mostrarCarrito();
        System.out.print("\nCódigo del producto a quitar: ");
        String codigo = scanner.nextLine();
        carrito.quitarProducto(codigo);
    }

    private static void pagar() {
        if (carrito.estaVacio()) {
            System.out.println("El carrito está vacío");
            return;
        }

        carrito.mostrarCarrito();

        System.out.println("\n--- OPCIONES DE PAGO ---");
        System.out.println("1. Efectivo");
        System.out.println("2. Tarjeta");
        System.out.println("3. Cancelar");
        System.out.print("Opción: ");
        int metodoPago = scanner.nextInt();
        scanner.nextLine();

        if (metodoPago == 3) {
            System.out.println("Compra cancelada");
            return;
        }

        if (metodoPago == 1 || metodoPago == 2) {
            // Verificar stock antes de procesar
            boolean stockOk = true;
            for (int i = 0; i < carrito.getProductos().size(); i++) {
                Producto p = carrito.getProductos().get(i);
                int cant = carrito.getCantidades().get(i);
                if (p.getStock() < cant) {
                    System.out.println("Stock insuficiente para: " + p.getNombre());
                    stockOk = false;
                }
            }

            if (stockOk) {
                // Procesar venta
                double total = carrito.calcularTotal();

                for (int i = 0; i < carrito.getProductos().size(); i++) {
                    Producto p = carrito.getProductos().get(i);
                    int cant = carrito.getCantidades().get(i);
                    supermercado.realizarVenta(p.getCodigo(), cant);
                }

                System.out.println("\n=======================");
                System.out.println("    TICKET DE COMPRA   ");
                System.out.println("=======================");
                carrito.mostrarCarrito();
                System.out.println("-----------------------");
                System.out.println("Método de pago: " +
                        (metodoPago == 1 ? "Efectivo" : "Tarjeta"));
                System.out.println("=======================");
                System.out.println("¡Gracias por su compra!");

                carrito.vaciar();
            } else {
                System.out.println("No se pudo completar la compra");
            }
        } else {
            System.out.println("Opción no válida");
        }
    }

    private static void verVentasTotales() {
        System.out.println("\n=== RESUMEN DE VENTAS ===");
        System.out.println("Total vendido hoy: €" + supermercado.obtenerTotalVentas());
    }
}