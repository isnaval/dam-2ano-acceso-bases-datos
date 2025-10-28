package paquetes;

import model.Paquete;

public class PaqueteUrgente extends Paquete {

    public PaqueteUrgente(String codigo, double peso, String origen,
                          String destino, String remitente, String destinatario) {
        super(codigo, peso, origen, destino, remitente, destinatario);
    }

    @Override
    public double calcularPrecio() {
        // €10 base + €5 por kg
        return 10 + (peso * 5);
    }

    @Override
    public int getTiempoEntrega() {
        return 1; // 1 día
    }

    @Override
    public String getTipo() {
        return "URGENTE";
    }
}