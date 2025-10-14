package pokemon;

import model.Pokemon;
import interfaces.Combatiente;

public class Charmander extends Pokemon implements Combatiente {
    private boolean enfurecido;

    public Charmander(int nivel) {
        super("Charmander", "Fuego", nivel);
        this.enfurecido = false;
    }

    @Override
    public void atacar(Pokemon objetivo) {
        System.out.println(getNombre() + " usa Ascuas!");
        int danio = calcularDanio(objetivo);

        if (enfurecido) {
            danio = (int)(danio * 1.2);
            System.out.println("¡Charmander está enfurecido!");
        }

        objetivo.recibirDanio(danio);
        System.out.println("Causó " + danio + " puntos de daño!");
    }

    @Override
    public void defender() {
        System.out.println(getNombre() + " aumenta su fuego interno!");
        enfurecido = true;
        System.out.println("¡Su próximo ataque será más fuerte!");
    }

    @Override
    public void usarHabilidadEspecial(Pokemon objetivo) {
        System.out.println(getNombre() + " usa LANZALLAMAS!");
        int danioEspecial = (int)(getAtaque() * 2.0);
        objetivo.recibirDanio(danioEspecial);
        System.out.println("¡Ataque de fuego devastador! Causó " + danioEspecial + " puntos de daño!");

        // Puede quemar al oponente
        if (Math.random() < 0.25) {
            System.out.println("¡" + objetivo.getNombre() + " está quemado!");
        }
    }

    @Override
    public boolean puedeAtacar() {
        return estaVivo();
    }

    @Override
    public String obtenerDescripcionAtaque() {
        return "Ascuas - Ataque de fuego básico";
    }

    private int calcularDanio(Pokemon objetivo) {
        int danioBase = getAtaque() - (objetivo.getDefensa() / 2);

         if (objetivo.getTipo().equals("Planta") || objetivo.getTipo().equals("Hielo")) {
            danioBase = (int)(danioBase * 2.0);
            System.out.println("¡Es súper efectivo!");
        } else if (objetivo.getTipo().equals("Agua") || objetivo.getTipo().equals("Roca")) {
            danioBase = (int)(danioBase * 0.5);
            System.out.println("No es muy efectivo...");
        }

        enfurecido = false;
        return Math.max(1, danioBase);
    }
}