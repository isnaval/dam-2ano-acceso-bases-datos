package model;

public abstract class Vehiculo {
    protected String marca;
    protected String modelo;
    protected String matricula;
    protected double precio;
    protected int anio;
    protected boolean disponible;

    public Vehiculo(String marca, String modelo, String matricula, double precio, int anio) {
        this.marca = marca;
        this.modelo = modelo;
        this.matricula = matricula;
        this.precio = precio;
        this.anio = anio;
        this.disponible = true;
    }

    public abstract String getTipo();
    public abstract double calcularImpuesto();

    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public String getMatricula() { return matricula; }
    public void setMatricula(String matricula) { this.matricula = matricula; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    public int getAnio() { return anio; }
    public void setAnio(int anio) { this.anio = anio; }

    public boolean isDisponible() { return disponible; }
    public void setDisponible(boolean disponible) { this.disponible = disponible; }

    @Override
    public String toString() {
        return getTipo() + " - " + marca + " " + modelo + " (" + anio + ") - " +
                matricula + " - €" + precio + " - " +
                (disponible ? "Disponible" : "Vendido");
    }
}
