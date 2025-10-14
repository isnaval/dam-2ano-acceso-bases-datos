package model;

import java.util.ArrayList;
import java.util.List;

public class Entrenador {

    private String nombre;
    private List<Pokemon> equipo;
    private int medallas;

    public Entrenador(String nombre) {
        this.nombre = nombre;
        this.equipo = new ArrayList<>();
        this.medallas = 0;
    }

    public boolean agregarPokemon(Pokemon pokemon) {
        if (equipo.size() < 6) {
            equipo.add(pokemon);
            return true;
        }
        return false;
    }

    public Pokemon getPokemon(int indice) {
        if (indice >= 0 && indice < equipo.size()) {
            return equipo.get(indice);
        }
        return null;
    }

    public void mostrarEquipo() {
        System.out.println("=== Equipo de " + nombre + " ===");
        for (int i = 0; i < equipo.size(); i++) {
            System.out.println((i + 1) + ". " + equipo.get(i));
        }
    }

    public boolean tienesPokemonVivos() {
        for (Pokemon p : equipo) {
            if (p.estaVivo()) return true;
        }
        return false;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public List<Pokemon> getEquipo() { return equipo; }

    public int getMedallas() { return medallas; }
    public void ganarMedalla() { this.medallas++; }
}
