package E01_Animales.model;

public class Animal {
    protected String nombre;
    protected int edad;
    protected double peso;


    public Animal(String nombre, int edad, double peso) {
        this.nombre = nombre;
        this.edad = edad;
        this.peso = peso;
    }

    public Animal() {

    }

    public void hacerSonido() {

    }

    public void moverse() {

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre + ", Edad: " + edad + " años, Peso: " + peso + " kg";
    }


}
