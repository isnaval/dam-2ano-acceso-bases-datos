import pokemon.*;
import model.*;
import interfaces.Combatiente;
import java.util.Scanner;
import java.util.Random;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static Entrenador jugador;
    private static Random random = new Random();

    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════╗");
        System.out.println("║       MUNDO POKEMON - JAVA         ║");
        System.out.println("╚════════════════════════════════════╝\n");

        inicializarJuego();
        menuPrincipal();

        scanner.close();
    }

    private static void inicializarJuego() {
        System.out.print("¿Cuál es tu nombre, entrenador? ");
        String nombre = scanner.nextLine();
        jugador = new Entrenador(nombre);

        System.out.println("\n¡Bienvenido " + nombre + "!");
        System.out.println("El Profesor Oak te ha dado 3 Pokemon iniciales:\n");

        // Pokemon iniciales
        Pikachu pikachu = new Pikachu(10);
        Charmander charmander = new Charmander(8);
        Squirtle squirtle = new Squirtle(9);

        jugador.agregarPokemon(pikachu);
        jugador.agregarPokemon(charmander);
        jugador.agregarPokemon(squirtle);

        System.out.println("✓ " + pikachu.getNombre() + " se unió a tu equipo!");
        System.out.println("✓ " + charmander.getNombre() + " se unió a tu equipo!");
        System.out.println("✓ " + squirtle.getNombre() + " se unió a tu equipo!");

        esperarEnter();
    }

    private static void menuPrincipal() {
        boolean continuar = true;

        while (continuar) {
            limpiarPantalla();
            System.out.println("\n╔════════════════════════════════════╗");
            System.out.println("║         MENÚ PRINCIPAL               ║");
            System.out.println("╚══════════════════════════════════════╝");
            System.out.println("│ 1. 👥 Ver equipo Pokemon");
            System.out.println("│ 2. ⚔️  Batalla Pokemon");
            System.out.println("│ 3. 🏥 Centro Pokemon (Curar)");
            System.out.println("│ 4. 💪 Entrenar Pokemon");
            System.out.println("│ 5. 🏆 Ver medallas");
            System.out.println("│ 6. 🚪 Salir");
            System.out.println("└───────────────────────────────────────");
            System.out.print("\nElige una opción: ");

            int opcion = leerOpcion();

            switch (opcion) {
                case 1:
                    verEquipo();
                    break;
                case 2:
                    iniciarBatalla();
                    break;
                case 3:
                    centroPokemon();
                    break;
                case 4:
                    entrenarPokemon();
                    break;
                case 5:
                    verMedallas();
                    break;
                case 6:
                    continuar = false;
                    System.out.println("\n¡Hasta pronto, Maestro Pokemon!");
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        }
    }

    private static void verEquipo() {
        limpiarPantalla();
        System.out.println("\n╔════════════════════════════════════╗");
        System.out.println("║         TU EQUIPO POKEMON            ║");
        System.out.println("╚══════════════════════════════════════╝\n");
        jugador.mostrarEquipo();
        esperarEnter();
    }

    private static void iniciarBatalla() {
        limpiarPantalla();
        System.out.println("\n╔════════════════════════════════════╗");
        System.out.println("║         BATALLA POKEMON              ║");
        System.out.println("╚══════════════════════════════════════╝\n");

        // Verificar si hay Pokemon vivos
        if (!jugador.tienesPokemonVivos()) {
            System.out.println("❌ Todos tus Pokemon están debilitados!");
            System.out.println("Ve al Centro Pokemon primero.");
            esperarEnter();
            return;
        }

        // Elegir Pokemon
        jugador.mostrarEquipo();
        System.out.print("\nElige tu Pokemon (1-" + jugador.getEquipo().size() + "): ");
        int indice = leerOpcion() - 1;

        Pokemon miPokemon = jugador.getPokemon(indice);
        if (miPokemon == null || !miPokemon.estaVivo()) {
            System.out.println("Pokemon no válido o debilitado");
            esperarEnter();
            return;
        }

        // Crear oponente
        Pokemon oponente = crearOponenteAleatorio();

        System.out.println("\n💥 ¡Un " + oponente.getNombre() + " salvaje apareció!");
        esperarEnter();

        // Batalla
        batallar(miPokemon, oponente);
    }

    private static void batallar(Pokemon miPokemon, Pokemon oponente) {
        int turno = 1;

        while (miPokemon.estaVivo() && oponente.estaVivo()) {
            limpiarPantalla();
            System.out.println("\n╔════════ TURNO " + turno + " ════════╗");
            System.out.println("\n🔵 Tu " + miPokemon);
            System.out.println("🔴 " + oponente + " salvaje\n");

            System.out.println("┌─── ¿Qué hacer? ────────────┐");
            System.out.println("│ 1. ⚔️  Atacar");
            System.out.println("│ 2. 💥 Habilidad Especial");
            System.out.println("│ 3. 🛡️  Defender");
            System.out.println("│ 4. 🏃 Huir");
            System.out.println("└────────────────────────────┘");
            System.out.print("Opción: ");

            int accion = leerOpcion();

            // Ejecutar acción del jugador
            if (miPokemon instanceof Combatiente) {
                Combatiente combatiente = (Combatiente) miPokemon;

                switch (accion) {
                    case 1:
                        combatiente.atacar(oponente);
                        break;
                    case 2:
                        combatiente.usarHabilidadEspecial(oponente);
                        break;
                    case 3:
                        combatiente.defender();
                        break;
                    case 4:
                        System.out.println("\n¡Escapaste con éxito!");
                        esperarEnter();
                        return;
                    default:
                        System.out.println("Acción no válida, pierdes el turno");
                }
            }

            esperarEnter();

            // Turno del oponente
            if (oponente.estaVivo()) {
                System.out.println("\n💢 ¡" + oponente.getNombre() + " ataca!");
                int danio = oponente.getAtaque() - (miPokemon.getDefensa() / 2);
                danio = Math.max(1, danio);
                miPokemon.recibirDanio(danio);
                System.out.println("Te causó " + danio + " puntos de daño!");
                esperarEnter();
            }

            turno++;
        }

        // Resultado
        limpiarPantalla();
        if (!miPokemon.estaVivo()) {
            System.out.println("\n😢 " + miPokemon.getNombre() + " fue derrotado...");
        } else {
            System.out.println("\n🎉 ¡VICTORIA!");
            System.out.println("¡Derrotaste al " + oponente.getNombre() + " salvaje!");
            System.out.println("+" + (oponente.getNivel() * 10) + " puntos de experiencia");

            // Posibilidad de ganar medalla
            if (random.nextDouble() < 0.3) {
                jugador.ganarMedalla();
                System.out.println("\n🏆 ¡Ganaste una medalla!");
            }
        }
        esperarEnter();
    }

    private static void centroPokemon() {
        limpiarPantalla();
        System.out.println("\n╔════════════════════════════════════╗");
        System.out.println("║         CENTRO POKEMON               ║");
        System.out.println("╚══════════════════════════════════════╝\n");

        System.out.println("🏥 Enfermera Joy: ¡Bienvenido!");
        System.out.println("Voy a curar a tus Pokemon...\n");

        for (int i = 0; i < 3; i++) {
            System.out.print(".");
            try { Thread.sleep(500); } catch (Exception e) {}
        }

        for (Pokemon p : jugador.getEquipo()) {
            p.curarCompleto();
        }

        System.out.println("\n\n✅ ¡Todos tus Pokemon están curados!");
        System.out.println("¡Vuelve cuando lo necesites!");
        esperarEnter();
    }

    private static void entrenarPokemon() {
        limpiarPantalla();
        System.out.println("\n╔════════════════════════════════════╗");
        System.out.println("║      ENTRENAMIENTO POKEMON           ║");
        System.out.println("╚══════════════════════════════════════╝\n");

        jugador.mostrarEquipo();
        System.out.print("\n¿Qué Pokemon quieres entrenar? (1-" +
                jugador.getEquipo().size() + "): ");
        int indice = leerOpcion() - 1;

        Pokemon pokemon = jugador.getPokemon(indice);
        if (pokemon != null) {
            System.out.println("\n💪 Entrenando a " + pokemon.getNombre() + "...");

            for (int i = 0; i < 3; i++) {
                System.out.print(".");
                try { Thread.sleep(500); } catch (Exception e) {}
            }

            pokemon.setNivel(pokemon.getNivel() + 1);
            pokemon.curarCompleto();

            System.out.println("\n\n🎉 ¡" + pokemon.getNombre() +
                    " subió al nivel " + pokemon.getNivel() + "!");
            System.out.println("📈 Todas sus estadísticas mejoraron!");
        } else {
            System.out.println("Pokemon no válido");
        }
        esperarEnter();
    }

    private static void verMedallas() {
        limpiarPantalla();
        System.out.println("\n╔════════════════════════════════════╗");
        System.out.println("║         TUS MEDALLAS                 ║");
        System.out.println("╚══════════════════════════════════════╝\n");

        System.out.println("Entrenador: " + jugador.getNombre());
        System.out.println("Medallas ganadas: " + jugador.getMedallas());

        if (jugador.getMedallas() > 0) {
            System.out.print("\n");
            for (int i = 0; i < jugador.getMedallas(); i++) {
                System.out.print("🏆 ");
            }
            System.out.println();
        }

        if (jugador.getMedallas() >= 8) {
            System.out.println("\n⭐ ¡Eres un Maestro Pokemon!");
        }

        esperarEnter();
    }

    private static Pokemon crearOponenteAleatorio() {
        int tipo = random.nextInt(4);
        int nivel = 5 + random.nextInt(8); // Nivel 5-12

        switch (tipo) {
            case 0:
                return new Pikachu(nivel);
            case 1:
                return new Charmander(nivel);
            case 2:
                return new Squirtle(nivel);
            default:
                return new Bulbasaur(nivel);
        }
    }

    private static void limpiarPantalla() {
        for (int i = 0; i < 50; i++) System.out.println();
    }

    private static void esperarEnter() {
        System.out.print("\nPresiona ENTER para continuar...");
        scanner.nextLine();
    }

    private static int leerOpcion() {
        try {
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer
            return opcion;
        } catch (Exception e) {
            scanner.nextLine(); // Limpiar buffer en caso de error
            return -1;
        }
    }
}