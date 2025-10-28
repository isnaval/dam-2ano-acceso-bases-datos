import interfaces.Combatiente;
import model.Entrenador;
import model.Pokemon;
import pokemon.Bulbasaur;
import pokemon.Charmander;
import pokemon.Pikachu;
import pokemon.Squirtle;

import java.util.Scanner;

public class Main02 {
    private static Scanner scanner = new Scanner(System.in);
    private static Entrenador jugador;

    public static void main(String[] args) {
        System.out.println("=== MUNDO POKEMON ===\n");


        System.out.print("¿Cuál es tu nombre? ");
        String nombre = scanner.nextLine();
        jugador = new Entrenador(nombre);


        jugador.agregarPokemon(new Pikachu(10));
        jugador.agregarPokemon(new Charmander(8));
        jugador.agregarPokemon(new Squirtle(9));

        System.out.println("\nTienes 3 Pokemon: Pikachu, Charmander y Squirtle");


        boolean continuar = true;
        while (continuar) {
            System.out.println("\n--- MENÚ ---");
            System.out.println("1. Ver equipo");
            System.out.println("2. Batalla");
            System.out.println("3. Curar Pokemon");
            System.out.println("4. Salir");
            System.out.print("Opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    verEquipo();
                    break;
                case 2:
                    batalla();
                    break;
                case 3:
                    curarPokemon();
                    break;
                case 4:
                    continuar = false;
                    System.out.println("¡Adiós!");
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        }

        scanner.close();
    }

    private static void verEquipo() {
        System.out.println("\n=== TU EQUIPO ===");
        jugador.mostrarEquipo();
    }

    private static void batalla() {
        System.out.println("\n=== BATALLA ===");

        jugador.mostrarEquipo();
        System.out.print("Elige tu Pokemon (1-3): ");
        int indice = scanner.nextInt() - 1;
        scanner.nextLine();

        Pokemon miPokemon = jugador.getPokemon(indice);
        if (miPokemon == null || !miPokemon.estaVivo()) {
            System.out.println("Pokemon no válido o debilitado");
            return;
        }

        Pokemon oponente = new Bulbasaur(7);
        System.out.println("\n¡Apareció un " + oponente.getNombre() + " salvaje!");

        while (miPokemon.estaVivo() && oponente.estaVivo()) {
            System.out.println("\nTu " + miPokemon.getNombre() +
                    ": " + miPokemon.getVida() + "/" +
                    miPokemon.getVidaMaxima() + " HP");
            System.out.println(oponente.getNombre() + " salvaje: " +
                    oponente.getVida() + "/" +
                    oponente.getVidaMaxima() + " HP");

            System.out.println("\n1. Atacar");
            System.out.println("2. Huir");
            System.out.print("¿Qué hacer? ");

            int accion = scanner.nextInt();
            scanner.nextLine();

            if (accion == 1) {

                if (miPokemon instanceof Combatiente) {
                    Combatiente c = (Combatiente) miPokemon;
                    c.atacar(oponente);
                }

                if (oponente.estaVivo()) {
                    System.out.println("\n" + oponente.getNombre() + " te ataca!");
                    int danio = oponente.getAtaque() / 2;
                    miPokemon.recibirDanio(danio);
                    System.out.println("Te hizo " + danio + " de daño");
                }
            } else {
                System.out.println("¡Escapaste!");
                return;
            }
        }

        if (miPokemon.estaVivo()) {
            System.out.println("\n¡GANASTE!");
        } else {
            System.out.println("\n¡Perdiste!");
        }
    }

    private static void curarPokemon() {
        System.out.println("\n=== CENTRO POKEMON ===");
        System.out.println("Curando a todos tus Pokemon...");

        for (Pokemon p : jugador.getEquipo()) {
            p.curarCompleto();
        }

        System.out.println("¡Todos curados!");
    }
}