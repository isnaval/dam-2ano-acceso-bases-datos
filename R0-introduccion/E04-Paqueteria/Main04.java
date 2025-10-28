import model.EmpresaPaqueteria;
import model.Paquete;
import paquetes.PaqueteFragil;
import paquetes.PaqueteNormal;
import paquetes.PaqueteUrgente;

import java.util.Scanner;

public class Main04 {
    private static Scanner scanner = new Scanner(System.in);
    private static EmpresaPaqueteria empresa;
    private static int contadorCodigo = 1000;

    public static void main(String[] args) {
        System.out.println("=== SISTEMA DE PAQUETERÍA ===\n");

        empresa = new EmpresaPaqueteria("EnvíosRápidos");

        // Menú principal
        boolean continuar = true;
        while (continuar) {
            mostrarMenu();
            int opcion = scanner.nextInt();
            scanner.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1:
                    registrarPaquete();
                    break;
                case 2:
                    rastrearPaquete();
                    break;
                case 3:
                    entregarPaquete();
                    break;
                case 4:
                    verTodosPaquetes();
                    break;
                case 5:
                    verPendientes();
                    break;
                case 6:
                    continuar = false;
                    System.out.println("¡Adiós!");
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        }

        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println("\n--- MENÚ ---");
        System.out.println("1. Registrar paquete");
        System.out.println("2. Rastrear paquete");
        System.out.println("3. Entregar paquete");
        System.out.println("4. Ver todos los paquetes");
        System.out.println("5. Ver pendientes");
        System.out.println("6. Salir");
        System.out.print("Opción: ");
    }

    private static void registrarPaquete() {
        System.out.println("\n--- REGISTRAR PAQUETE ---");

        // Generar código automático
        String codigo = "PKG" + contadorCodigo++;

        // Datos comunes
        System.out.print("Peso (kg): ");
        double peso = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Ciudad origen: ");
        String origen = scanner.nextLine();

        System.out.print("Ciudad destino: ");
        String destino = scanner.nextLine();

        System.out.print("Remitente: ");
        String remitente = scanner.nextLine();

        System.out.print("Destinatario: ");
        String destinatario = scanner.nextLine();

        // Tipo de paquete
        System.out.println("\nTipo de paquete:");
        System.out.println("1. Normal (5 días)");
        System.out.println("2. Urgente (1 día)");
        System.out.println("3. Frágil (3 días)");
        System.out.print("Tipo: ");
        int tipo = scanner.nextInt();
        scanner.nextLine();

        Paquete paquete = null;

        switch (tipo) {
            case 1:
                paquete = new PaqueteNormal(codigo, peso, origen, destino,
                        remitente, destinatario);
                break;
            case 2:
                paquete = new PaqueteUrgente(codigo, peso, origen, destino,
                        remitente, destinatario);
                break;
            case 3:
                System.out.print("¿Qué contiene? ");
                String contenido = scanner.nextLine();
                paquete = new PaqueteFragil(codigo, peso, origen, destino,
                        remitente, destinatario, contenido);
                break;
            default:
                System.out.println("Tipo no válido");
                return;
        }

        empresa.enviarPaquete(paquete);
    }

    private static void rastrearPaquete() {
        System.out.print("\nIngrese código del paquete: ");
        String codigo = scanner.nextLine();

        Paquete paquete = empresa.rastrearPaquete(codigo);
        if (paquete != null) {
            System.out.println("\n=== INFORMACIÓN DEL PAQUETE ===");
            System.out.println("Código: " + paquete.getCodigo());
            System.out.println("Tipo: " + paquete.getTipo());
            System.out.println("Peso: " + paquete.getPeso() + " kg");
            System.out.println("Ruta: " + paquete.getOrigen() + " → " + paquete.getDestino());
            System.out.println("Remitente: " + paquete.getRemitente());
            System.out.println("Destinatario: " + paquete.getDestinatario());
            System.out.println("Estado: " + (paquete.isEntregado() ? "ENTREGADO" : "EN TRÁNSITO"));
            System.out.println("Precio: €" + paquete.calcularPrecio());
            System.out.println("Tiempo de entrega: " + paquete.getTiempoEntrega() + " días");
        } else {
            System.out.println("Paquete no encontrado");
        }
    }

    private static void entregarPaquete() {
        System.out.print("\nCódigo del paquete a entregar: ");
        String codigo = scanner.nextLine();
        empresa.entregarPaquete(codigo);
    }

    private static void verTodosPaquetes() {
        empresa.mostrarPaquetes();
    }

    private static void verPendientes() {
        empresa.mostrarPaquetesPendientes();
    }
}