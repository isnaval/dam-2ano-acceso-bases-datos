package R3_1_BuscarClientesPorApellido;

import R3_dao.ClienteDAOImpl;

import java.util.Scanner;

public class R3_1_BuscarClientesPorApellido {
    private static final ClienteDAOImpl dao = new ClienteDAOImpl();
    private static final Scanner scanner = new Scanner(System.in);

  public static void main(String[] args){
      boolean continuar = true;
      mostrarBienvenida();
      while(continuar) {
          mostrarMenu();
          int opcion = leerOpcion();
          switch(opcion) {
              case 1 -> buscarPorApellido();
              case 2 -> mostrarTodosLosClientes();
              case 3 -> buscarPorApellidoParcial();
              case 4 -> mostrarEstadisticas();
              case 0 -> {
                  System.out.println("\n ¡Hasta luego!");
                  continuar = false;
              }
              default -> System.out.println("Opción no valida: intentalo otra vez");
          }
      }
    scanner.close();
  }




    private static void mostrarBienvenida() {
        System.out.println("=== BUSCAR CLIENTES POR APELLIDO ===");
    }

    private static void mostrarMenu() {
        System.out.println("\n--- MENÚ PRINCIPAL ---");
        System.out.println(" 1. Buscar por apellido exacto       ");
        System.out.println(" 2. Mostrar todos los clientes       ");
        System.out.println(" 3. Buscar por apellido parcial      ");
        System.out.println(" 4. Ver estadísticas                 ");
        System.out.println(" 0. Salir                            ");
        System.out.print("\nSelecciona una opción: ");
    }

    private static int leerOpcion() {
      try {
          return Integer.parseInt(scanner.nextLine());
      } catch (NumberFormatException e) {
          return -1;
        }
    }

    private static void buscarPorApellido() {
      System.out.println("Introduce el apellido exacto: ");
      String apellido = scanner.nextLine().trim();
      var clientes = dao.findByApellido(apellido);
      if (clientes.isEmpty()) {
          System.out.println("No se encontraron clientes con el apellido: " + apellido + ".");
      } else {
          System.out.println("\nClientes encontrados: ");
          clientes.forEach(System.out::println);
      }
    }

    private static void mostrarTodosLosClientes() {
      var clientes = dao.findAll();
      if (clientes.isEmpty()){
          System.out.println("No se encontraron registrados");
      } else {
          System.out.println("\nClientes encontrados: ");
          clientes.forEach(System.out::println);
      }
    }

    private static void buscarPorApellidoParcial() {
        System.out.println("Introduce el apellido parcial: ");
        String fragmento = scanner.nextLine().trim();
        var clientes = dao.findByApellidoParcial(fragmento);
        if(clientes.isEmpty()){
            System.out.println("No se encontraron coincidencias con '" + fragmento + "'.");
        } else {
            System.out.println("No se encontraron coincidencias con '" + fragmento + "'.");
            clientes.forEach(System.out::println);
        }
    }

    private static void mostrarEstadisticas() {
        long total = dao.countClientes();
        double promedio = dao.promedioEdad();
        System.out.println("\n--- ESTADÍSTICAS ---");
        System.out.println("Total de clientes: " + total);
        System.out.printf("Edad promedio: %.2f años%n", promedio);
    }
    
}
