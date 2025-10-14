package pokemon;

import model.Pokemon;
import interfaces.Combatiente;

public class Bulbasaur extends Pokemon implements Combatiente {
    private int regeneracion;

    public Bulbasaur(int nivel) {
        super("Bulbasaur", "Planta", nivel);
        this.regeneracion = 0;
    }

    @Override
    public void atacar(Pokemon objetivo) {
        System.out.println(getNombre() + " usa Látigo Cepa!");
        int danio = calcularDanio(objetivo);
        objetivo.recibirDanio(danio);
        System.out.println("Causó " + danio + " puntos de daño!");

        // Regeneración pasiva
        if (regeneracion > 0) {
            int curacion = getVidaMaxima() / 10;
            curar(curacion);
            System.out.println(getNombre() + " se regenera " + curacion + " HP!");
            regeneracion--;
        }
    }

    @Override
    public void defender() {
        System.out.println(getNombre() + " usa Síntesis!");
        regeneracion = 3;
        System.out.println("¡Se regenerará vida durante 3 turnos!");
    }

    @Override
    public void usarHabilidadEspecial(Pokemon objetivo) {
        System.out.println(getNombre() + " usa RAYO SOLAR!");
        int danioEspecial = (int)(getAtaque() * 2.5);
        objetivo.recibirDanio(danioEspecial);
        System.out.println("¡Poder de la naturaleza! Causó " + danioEspecial + " puntos de daño!");
    }

    @Override
    public boolean puedeAtacar() {
        return estaVivo();
    }

    @Override
    public String obtenerDescripcionAtaque() {
        return "Látigo Cepa - Ataque con lianas";
    }

    private int calcularDanio(Pokemon objetivo) {
        int danioBase = getAtaque() - (objetivo.getDefensa() / 2);

        if (objetivo.getTipo().equals("Agua") || objetivo.getTipo().equals("Roca")) {
            danioBase = (int)(danioBase * 2.0);
            System.out.println("¡Es súper efectivo!");
        } else if (objetivo.getTipo().equals("Fuego") || objetivo.getTipo().equals("Planta")) {
            danioBase = (int)(danioBase * 0.5);
            System.out.println("No es muy efectivo...");
        }

        return Math.max(1, danioBase);
    }
}