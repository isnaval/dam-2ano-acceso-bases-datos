package E01_Animales.animales;


import E01_Animales.model.Animal;

public class Gato extends Animal {
    private String colorPelaje;

    public Gato() {
    }

    public Gato(String nombre, int edad, double peso, String colorPelaje) {
        super(nombre, edad, peso);
        this.colorPelaje = colorPelaje;
    }

    @Override
    public void hacerSonido() {
        System.out.println(nombre + " dice: ¡Miau miau!");
    }

    @Override
    public void moverse() {
        System.out.println(nombre + " camina sigilosamente");
    }

    public String getColorPelaje() {
        return colorPelaje;
    }

    public void setColorPelaje(String colorPelaje) {
        this.colorPelaje = colorPelaje;
    }

    @Override
    public String toString() {
        return "Gato - " + super.toString() + ", Color pelaje: " + colorPelaje;
    }
}