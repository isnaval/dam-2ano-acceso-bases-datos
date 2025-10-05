package E01_Animales;

import E01_Animales.animales.Gato;
import E01_Animales.animales.Pajaro;
import E01_Animales.animales.Perro;
import E01_Animales.model.Animal;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Perro perro1 = new Perro("Rex", 5, 25.5, "Labrador");
        Perro perro2 = new Perro("Luna", 3, 15.2, "Beagle");
        Gato gato1 = new Gato("Michi", 2, 4.5, "Naranja");
        Gato gato2 = new Gato("Shadow", 4, 5.0, "Negro");
        Pajaro pajaro1 = new Pajaro("Tweety", 1, 0.5, true);
        Pajaro pajaro2 = new Pajaro("Pingu", 2, 15.0, false);

        List<Animal> animales = new ArrayList<>();
        animales.add(perro1);
        animales.add(perro2);
        animales.add(gato1);
        animales.add(gato2);
        animales.add(pajaro1);
        animales.add(pajaro2);

        System.out.println("=== DEMOSTRACIÓN DE POLIMORFISMO ===\n");

        for (Animal animal : animales) {
            System.out.println(animal.toString());
            animal.hacerSonido();
            animal.moverse();
            System.out.println("---");
        }
    }
}