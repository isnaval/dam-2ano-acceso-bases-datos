package model;

import java.util.ArrayList;
import java.util.List;

public class Carrito {
    private List<Producto> productos;
    private List<Integer> cantidades;

    public Carrito() {
        this.productos = new ArrayList<>();
        this.cantidades = new ArrayList<>();
    }

    public void agregarProducto(Producto producto, int cantidad) {
        // Buscar si el producto ya está en el carrito
        int indice = -1;
        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getCodigo().equals(producto.getCodigo())) {
                indice = i;
                break;
            }
        }

        if (indice >= 0) {
            // Si ya está, aumentar cantidad
            cantidades.set(indice, cantidades.get(indice) + cantidad);
        } else {
            // Si no está, agregarlo
            productos.add(producto);
            cantidades.add(cantidad);
        }

        System.out.println("✓ Agregado al carrito: " + cantidad + "x " + producto.getNombre());
    }

    public void quitarProducto(String codigo) {
        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getCodigo().equals(codigo)) {
                productos.remove(i);
                cantidades.remove(i);
                System.out.println("Producto eliminado del carrito");
                return;
            }
        }
        System.out.println("Producto no encontrado en el carrito");
    }

    public double calcularTotal() {
        double total = 0;
        for (int i = 0; i < productos.size(); i++) {
            total += productos.get(i).getPrecio() * cantidades.get(i);
        }
        return total;
    }

    public void mostrarCarrito() {
        System.out.println("\n=== CARRITO DE COMPRA ===");
        if (productos.isEmpty()) {
            System.out.println("El carrito está vacío");
        } else {
            for (int i = 0; i < productos.size(); i++) {
                Producto p = productos.get(i);
                int cant = cantidades.get(i);
                double subtotal = p.getPrecio() * cant;
                System.out.println(cant + "x " + p.getNombre() +
                        " - €" + p.getPrecio() + " = €" + subtotal);
            }
            System.out.println("----------------------");
            System.out.println("TOTAL: €" + calcularTotal());
        }
    }

    public void vaciar() {
        productos.clear();
        cantidades.clear();
        System.out.println("Carrito vaciado");
    }

    public boolean estaVacio() {
        return productos.isEmpty();
    }

    // Getters
    public List<Producto> getProductos() {
        return productos;
    }

    public List<Integer> getCantidades() {
        return cantidades;
    }

    public int getCantidadProductos() {
        int total = 0;
        for (int cant : cantidades) {
            total += cant;
        }
        return total;
    }
}