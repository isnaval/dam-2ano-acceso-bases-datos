package pokemon;

import model.Pokemon;
import interfaces.Combatiente;

public class Squirtle extends Pokemon implements Combatiente {
    private boolean protegido;

    public Squirtle(int nivel) {
        super("Squirtle", "Agua", nivel);
        this.protegido = false;
    }

    @Override
    public void atacar(Pokemon objetivo) {
        System.out.println(getNombre() + " usa Pistola Agua!");
        int danio = calcularDanio(objetivo);
        objetivo.recibirDanio(danio);
        System.out.println("Causó " + danio + " puntos de daño!");
        protegido = false;
    }

    @Override
    public void defender() {
        System.out.println(getNombre() + " se retira en su caparazón!");
        protegido = true;
        System.out.println("¡Su defensa aumentó mucho!");
    }

    @Override
    public void usarHabilidadEspecial(Pokemon objetivo) {
        System.out.println(getNombre() + " usa HIDROBOMBA!");
        int danioEspecial = (int)(getAtaque() * 2.2);
        objetivo.recibirDanio(danioEspecial);
        System.out.println("¡Torrente de agua masivo! Causó " + danioEspecial + " puntos de daño!");
    }

    @Override
    public boolean puedeAtacar() {
        return estaVivo();
    }

    @Override
    public String obtenerDescripcionAtaque() {
        return "Pistola Agua - Chorro de agua a presión";
    }

    @Override
    public void recibirDanio(int danio) {
        if (protegido) {
            danio = danio / 3;
            System.out.println("¡El caparazón reduce el daño!");
        }
        super.recibirDanio(danio);
        protegido = false;
    }

    private int calcularDanio(Pokemon objetivo) {
        int danioBase = getAtaque() - (objetivo.getDefensa() / 2);

        if (objetivo.getTipo().equals("Fuego") || objetivo.getTipo().equals("Roca")) {
            danioBase = (int)(danioBase * 2.0);
            System.out.println("¡Es súper efectivo!");
        } else if (objetivo.getTipo().equals("Planta") || objetivo.getTipo().equals("Agua")) {
            danioBase = (int)(danioBase * 0.5);
            System.out.println("No es muy efectivo...");
        }

        return Math.max(1, danioBase);
    }
}