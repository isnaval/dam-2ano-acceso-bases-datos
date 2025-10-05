package model;

import interfaces.Gestionable;

import java.util.ArrayList;
import java.util.List;

public class Supermercado implements Gestionable {
    private List<Producto> productos;
    private double totalVentas;

    public Supermercado() {
        this.productos = new ArrayList<>();
        this.totalVentas = 0;
    }

    @Override
    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }

    @Override
    public Producto buscarProducto(String codigo) {
        for (Producto p : productos) {
            if (p.getCodigo().equals(codigo)) {
                return p;
            }
        }
        return null;
    }

    @Override
    public void actualizarStock(String codigo, int nuevoStock) {
        Producto p = buscarProducto(codigo);
        if (p != null) {
            p.setStock(nuevoStock);
        }
    }

    @Override
    public void mostrarInventario() {
        System.out.println("\n=== PRODUCTOS DISPONIBLES ===");
        for (Producto p : productos) {
            System.out.println(p);
        }
    }

    @Override
    public boolean realizarVenta(String codigo, int cantidad) {
        Producto p = buscarProducto(codigo);
        if (p != null && p.getStock() >= cantidad) {
            p.reducirStock(cantidad);
            double total = p.getPrecio() * cantidad;
            totalVentas += total;
            System.out.println("Vendido: " + cantidad + "x " + p.getNombre());
            System.out.println("Total: €" + total);
            return true;
        }
        return false;
    }

    @Override
    public double obtenerTotalVentas() {
        return totalVentas;
    }
}