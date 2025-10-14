package vehiculos;

import model.Vehiculo;

public class Camion extends Vehiculo {
    private double capacidadCarga; // en toneladas
    private int numeroEjes;

    public Camion(String marca, String modelo, String matricula,
                  double precio, int anio, double capacidadCarga, int numeroEjes) {
        super(marca, modelo, matricula, precio, anio);
        this.capacidadCarga = capacidadCarga;
        this.numeroEjes = numeroEjes;
    }

    @Override
    public String getTipo() {
        return "Camión";
    }

    @Override
    public double calcularImpuesto() {
        // Impuesto del 25% para camiones
        return precio * 0.25;
    }

    public double getCapacidadCarga() { return capacidadCarga; }
    public void setCapacidadCarga(double capacidadCarga) {
        this.capacidadCarga = capacidadCarga;
    }

    public int getNumeroEjes() { return numeroEjes; }
    public void setNumeroEjes(int numeroEjes) { this.numeroEjes = numeroEjes; }
}