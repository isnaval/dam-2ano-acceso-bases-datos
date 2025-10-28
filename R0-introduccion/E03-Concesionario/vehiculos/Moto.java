package vehiculos;

import model.Vehiculo;

public class Moto extends Vehiculo {
    private int cilindrada;
    private String tipoMoto; // Deportiva, Scooter, Custom

    public Moto(String marca, String modelo, String matricula,
                double precio, int anio, int cilindrada, String tipoMoto) {
        super(marca, modelo, matricula, precio, anio);
        this.cilindrada = cilindrada;
        this.tipoMoto = tipoMoto;
    }

    @Override
    public String getTipo() {
        return "Moto";
    }

    @Override
    public double calcularImpuesto() {
        // Impuesto del 15% para motos
        return precio * 0.15;
    }

    public int getCilindrada() { return cilindrada; }
    public void setCilindrada(int cilindrada) { this.cilindrada = cilindrada; }

    public String getTipoMoto() { return tipoMoto; }
    public void setTipoMoto(String tipoMoto) { this.tipoMoto = tipoMoto; }
}