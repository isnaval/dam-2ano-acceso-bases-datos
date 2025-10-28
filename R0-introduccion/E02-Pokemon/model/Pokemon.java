package model;

public class Pokemon {

    private String nombre;
    private String tipo;
    private int nivel;
    private int vida;
    private int vidaMaxima;
    private int ataque;
    private int defensa;
    private int velocidad;

    public Pokemon(String nombre, String tipo, int nivel) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.nivel = nivel;
        this.vidaMaxima = 100 + (nivel * 10);
        this.vida = this.vidaMaxima;
        this.ataque = 50 + (nivel * 5);
        this.defensa = 30 + (nivel * 3);
        this.velocidad = 40 + (nivel * 2);
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public int getNivel() { return nivel; }
    public void setNivel(int nivel) {
        this.nivel = nivel;
        actualizarEstadisticas();
    }

    public int getVida() { return vida; }
    public void setVida(int vida) {
        this.vida = Math.max(0, Math.min(vida, vidaMaxima));
    }

    public int getVidaMaxima() { return vidaMaxima; }
    public int getAtaque() { return ataque; }
    public int getDefensa() { return defensa; }
    public int getVelocidad() { return velocidad; }

    private void actualizarEstadisticas() {
        this.vidaMaxima = 100 + (nivel * 10);
        this.ataque = 50 + (nivel * 5);
        this.defensa = 30 + (nivel * 3);
        this.velocidad = 40 + (nivel * 2);
    }

    public boolean estaVivo() {
        return vida > 0;
    }

    public void recibirDanio(int danio) {
        this.vida = Math.max(0, this.vida - danio);
    }

    public void curar(int cantidad) {
        this.vida = Math.min(vidaMaxima, this.vida + cantidad);
    }

    public void curarCompleto() {
        this.vida = this.vidaMaxima;
    }

    @Override
    public String toString() {
        return String.format("%s (Tipo: %s, Nivel: %d, HP: %d/%d)",
                nombre, tipo, nivel, vida, vidaMaxima);
    }


}
