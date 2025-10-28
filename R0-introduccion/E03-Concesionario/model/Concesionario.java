package model;

import interfaces.Vendible;
import java.util.ArrayList;
import java.util.List;

public class Concesionario implements Vendible {
    private String nombre;
    private List<Vehiculo> inventario;
    private List<String> ventas;
    private double ganancias;

    public Concesionario(String nombre) {
        this.nombre = nombre;
        this.inventario = new ArrayList<>();
        this.ventas = new ArrayList<>();
        this.ganancias = 0;
    }

    public void agregarVehiculo(Vehiculo vehiculo) {
        inventario.add(vehiculo);
        System.out.println("Vehículo agregado: " + vehiculo.getMarca() +
                " " + vehiculo.getModelo());
    }

    @Override
    public boolean vender(Vehiculo vehiculo, String comprador) {
        if (vehiculo.isDisponible()) {
            vehiculo.setDisponible(false);
            double precioFinal = calcularPrecioFinal(vehiculo);
            ganancias += precioFinal;

            String venta = "Vendido: " + vehiculo.getMarca() + " " +
                    vehiculo.getModelo() + " a " + comprador +
                    " por €" + precioFinal;
            ventas.add(venta);

            System.out.println("¡Venta realizada!");
            System.out.println(venta);
            return true;
        } else {
            System.out.println("Este vehículo no está disponible");
            return false;
        }
    }

    @Override
    public double calcularPrecioFinal(Vehiculo vehiculo) {
        double impuesto = vehiculo.calcularImpuesto();
        return vehiculo.getPrecio() + impuesto;
    }

    @Override
    public void mostrarDetallesVenta(Vehiculo vehiculo) {
        System.out.println("\n=== DETALLES DE VENTA ===");
        System.out.println("Vehículo: " + vehiculo.getMarca() + " " + vehiculo.getModelo());
        System.out.println("Tipo: " + vehiculo.getTipo());
        System.out.println("Precio base: €" + vehiculo.getPrecio());
        System.out.println("Impuesto: €" + vehiculo.calcularImpuesto());
        System.out.println("PRECIO FINAL: €" + calcularPrecioFinal(vehiculo));
        System.out.println("========================\n");
    }

    public void mostrarInventario() {
        System.out.println("\n=== INVENTARIO DE " + nombre.toUpperCase() + " ===");
        if (inventario.isEmpty()) {
            System.out.println("No hay vehículos en el inventario");
        } else {
            for (int i = 0; i < inventario.size(); i++) {
                System.out.println((i + 1) + ". " + inventario.get(i));
            }
        }
        System.out.println("===========================\n");
    }

    public void mostrarVehiculosDisponibles() {
        System.out.println("\n=== VEHÍCULOS DISPONIBLES ===");
        int contador = 0;
        for (Vehiculo v : inventario) {
            if (v.isDisponible()) {
                contador++;
                System.out.println(contador + ". " + v);
            }
        }
        if (contador == 0) {
            System.out.println("No hay vehículos disponibles");
        }
        System.out.println("===========================\n");
    }

    public void mostrarHistorialVentas() {
        System.out.println("\n=== HISTORIAL DE VENTAS ===");
        if (ventas.isEmpty()) {
            System.out.println("No hay ventas registradas");
        } else {
            for (String venta : ventas) {
                System.out.println("- " + venta);
            }
        }
        System.out.println("Ganancias totales: €" + ganancias);
        System.out.println("===========================\n");
    }

    public Vehiculo buscarPorMatricula(String matricula) {
        for (Vehiculo v : inventario) {
            if (v.getMatricula().equalsIgnoreCase(matricula)) {
                return v;
            }
        }
        return null;
    }

    public List<Vehiculo> getInventario() { return inventario; }
    public double getGanancias() { return ganancias; }
}