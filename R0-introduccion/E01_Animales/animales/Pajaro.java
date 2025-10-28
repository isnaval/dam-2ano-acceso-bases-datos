package E01_Animales.animales;

import E01_Animales.model.Animal;

public class Pajaro extends Animal {
    private boolean puedeVolar;

    public Pajaro() {
    }

    public Pajaro(String nombre, int edad, double peso, boolean puedeVolar) {
        super(nombre, edad, peso);
        this.puedeVolar = puedeVolar;
    }

    @Override
    public void hacerSonido() {
        System.out.println(nombre + " dice: ¡Pío pío!");
    }

    @Override
    public void moverse() {
        if (puedeVolar) {
            System.out.println(nombre + " vuela por el cielo");
        } else {
            System.out.println(nombre + " camina saltando");
        }
    }

    public boolean isPuedeVolar() {
        return puedeVolar;
    }

    public void setPuedeVolar(boolean puedeVolar) {
        this.puedeVolar = puedeVolar;
    }

    @Override
    public String toString() {
        return "Pájaro - " + super.toString() + ", ¿Puede volar?: " + (puedeVolar ? "Sí" : "No");
    }
}