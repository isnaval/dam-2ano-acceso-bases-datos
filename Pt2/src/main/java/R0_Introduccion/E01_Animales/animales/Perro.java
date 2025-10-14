package E01_Animales.animales;

import E01_Animales.model.Animal;

public class Perro extends Animal {

        private String raza;


        public Perro(String nombre, int edad, double peso, String raza) {
            super();
            this.raza = raza;
        }

        public void hacerSonido() {
            System.out.println(nombre + " dice: ¡Guau guau!");
        }

        public void moverse() {
            System.out.println(nombre + " corre moviendo la cola");
        }

        public String getRaza() {
            return raza;
        }

        public void setRaza(String raza) {
            this.raza = raza;
        }

        @Override
        public String toString() {
            return "Perro - " + super.toString() + ", Raza: " + raza;
        }
}
