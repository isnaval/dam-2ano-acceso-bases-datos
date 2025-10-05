package interfaces;

import model.Pokemon;

public interface Combatiente {
        void atacar(Pokemon objetivo);
        void defender();
        void usarHabilidadEspecial(Pokemon objetivo);
        boolean puedeAtacar();
        String obtenerDescripcionAtaque();
    }

