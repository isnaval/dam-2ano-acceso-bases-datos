package paquetes;

import model.Paquete;

public class PaqueteFragil extends Paquete {
    private String contenido;

    public PaqueteFragil(String codigo, double peso, String origen,
                         String destino, String remitente, String destinatario,
                         String contenido) {
        super(codigo, peso, origen, destino, remitente, destinatario);
        this.contenido = contenido;
    }

    @Override
    public double calcularPrecio() {
        // €8 base + €4 por kg + seguro
        double precio = 8 + (peso * 4);
        precio += 10; // Seguro adicional
        return precio;
    }

    @Override
    public int getTiempoEntrega() {
        return 3; // 3 días (manejo cuidadoso)
    }

    @Override
    public String getTipo() {
        return "FRÁGIL";
    }

    public String getContenido() {
        return contenido;
    }
}