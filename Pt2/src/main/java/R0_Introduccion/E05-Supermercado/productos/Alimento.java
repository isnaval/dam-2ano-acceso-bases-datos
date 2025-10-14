package productos;

import model.Producto;

public class Alimento extends Producto {
    private String fechaCaducidad;
    private String categoria; // lácteos, carnes, frutas, verduras, etc.

    public Alimento(String codigo, String nombre, double precio, int stock,
                    String fechaCaducidad, String categoria) {
        super(codigo, nombre, precio, stock);
        this.fechaCaducidad = fechaCaducidad;
        this.categoria = categoria;
    }

    // Getters y Setters
    public String getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(String fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return super.toString() + " - Alimento (" + categoria + ") - Caduca: " + fechaCaducidad;
    }
}