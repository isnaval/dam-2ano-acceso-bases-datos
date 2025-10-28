package vehiculos;

import model.Vehiculo;

public class Coche extends Vehiculo {
    private int numeroPuertas;
    private String tipoCombustible;

    public Coche(String marca, String modelo, String matricula,
                 double precio, int anio, int numeroPuertas, String tipoCombustible) {
        super(marca, modelo, matricula, precio, anio);
        this.numeroPuertas = numeroPuertas;
        this.tipoCombustible = tipoCombustible;
    }

    @Override
    public String getTipo() {
        return "Coche";
    }

    @Override
    public double calcularImpuesto() {
        // Impuesto del 21% para coches
        return precio * 0.21;
    }

    public int getNumeroPuertas() { return numeroPuertas; }
    public void setNumeroPuertas(int numeroPuertas) { this.numeroPuertas = numeroPuertas; }

    public String getTipoCombustible() { return tipoCombustible; }
    public void setTipoCombustible(String tipoCombustible) { this.tipoCombustible = tipoCombustible; }
}
