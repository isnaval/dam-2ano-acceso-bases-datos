package productos;

import model.Producto;

public class Bebida extends Producto {
    private double litros;
    private String tipo; // refresco, agua, jugo, alcohol, etc.

    public Bebida(String codigo, String nombre, double precio, int stock,
                  double litros, String tipo) {
        super(codigo, nombre, precio, stock);
        this.litros = litros;
        this.tipo = tipo;
    }

    // Getters y Setters
    public double getLitros() {
        return litros;
    }

    public void setLitros(double litros) {
        this.litros = litros;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return super.toString() + " - Bebida (" + litros + "L) - Tipo: " + tipo;
    }
}