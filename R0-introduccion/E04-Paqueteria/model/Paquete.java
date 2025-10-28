package model;

public abstract class Paquete {
    protected String codigo;
    protected double peso;
    protected String origen;
    protected String destino;
    protected String remitente;
    protected String destinatario;
    protected boolean entregado;

    public Paquete(String codigo, double peso, String origen, String destino,
                   String remitente, String destinatario) {
        this.codigo = codigo;
        this.peso = peso;
        this.origen = origen;
        this.destino = destino;
        this.remitente = remitente;
        this.destinatario = destinatario;
        this.entregado = false;
    }

    public abstract double calcularPrecio();

    public abstract int getTiempoEntrega(); // días

    public abstract String getTipo();

    public String getCodigo() {
        return codigo;
    }

    public double getPeso() {
        return peso;
    }

    public String getOrigen() {
        return origen;
    }

    public String getDestino() {
        return destino;
    }

    public String getRemitente() {
        return remitente;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public boolean isEntregado() {
        return entregado;
    }

    public void setEntregado(boolean entregado) {
        this.entregado = entregado;
    }

    @Override
    public String toString() {
        return getTipo() + " [" + codigo + "] " + peso + "kg - " +
                origen + " → " + destino + " - " +
                (entregado ? "ENTREGADO" : "EN TRÁNSITO");
    }
}