package paquetes;

import model.Paquete;

public class PaqueteNormal extends Paquete {

    public PaqueteNormal(String codigo, double peso, String origen,
                         String destino, String remitente, String destinatario) {
        super(codigo, peso, origen, destino, remitente, destinatario);
    }

    @Override
    public double calcularPrecio() {
        // €5 base + €2 por kg
        return 5 + (peso * 2);
    }

    @Override
    public int getTiempoEntrega() {
        return 5; // 5 días
    }

    @Override
    public String getTipo() {
        return "NORMAL";
    }
}