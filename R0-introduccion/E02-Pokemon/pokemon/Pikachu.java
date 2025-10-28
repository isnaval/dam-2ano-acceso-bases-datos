package pokemon;

import interfaces.Combatiente;
import model.Pokemon;

public class Pikachu extends Pokemon implements Combatiente {
    private int paralisisTurnos;

    public Pikachu(int nivel) {
        super("Pikachu", "Eléctrico", nivel);
        this.paralisisTurnos = 0;
    }

    @Override
    public void atacar(Pokemon objetivo) {
        System.out.println(getNombre() + " usa Impactrueno!");
        int danio = calcularDanio(objetivo);
        objetivo.recibirDanio(danio);
        System.out.println("Causó " + danio + " puntos de daño!");
    }

    @Override
    public void defender() {
        System.out.println(getNombre() + " usa Agilidad!");
        System.out.println("Su velocidad aumentó!");
    }

    @Override
    public void usarHabilidadEspecial(Pokemon objetivo) {
        System.out.println(getNombre() + " usa TRUENO!");
        int danioEspecial = (int)(getAtaque() * 1.8);
        objetivo.recibirDanio(danioEspecial);
        System.out.println("¡Ataque eléctrico masivo! Causó " + danioEspecial + " puntos de daño!");

        if (Math.random() < 0.3) {
            System.out.println("¡" + objetivo.getNombre() + " está paralizado!");
        }
    }

    @Override
    public boolean puedeAtacar() {
        return estaVivo();
    }

    @Override
    public String obtenerDescripcionAtaque() {
        return "Impactrueno - Ataque eléctrico básico";
    }

    private int calcularDanio(Pokemon objetivo) {
        int danioBase = getAtaque() - (objetivo.getDefensa() / 2);

        if (objetivo.getTipo().equals("Agua") || objetivo.getTipo().equals("Volador")) {
            danioBase = (int)(danioBase * 1.5);
            System.out.println("¡Es súper efectivo!");
        } else if (objetivo.getTipo().equals("Tierra") || objetivo.getTipo().equals("Eléctrico")) {
            danioBase = (int)(danioBase * 0.5);
            System.out.println("No es muy efectivo...");
        }

        return Math.max(1, danioBase);
    }
}