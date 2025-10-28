package model;

import interfaces.Enviable;

import java.util.ArrayList;
import java.util.List;

public class EmpresaPaqueteria implements Enviable {
    private String nombre;
    private List<Paquete> paquetes;
    private double ingresos;

    public EmpresaPaqueteria(String nombre) {
        this.nombre = nombre;
        this.paquetes = new ArrayList<>();
        this.ingresos = 0;
    }

    @Override
    public void enviarPaquete(Paquete paquete) {
        paquetes.add(paquete);
        double precio = paquete.calcularPrecio();
        ingresos += precio;

        System.out.println("\n✓ Paquete registrado");
        System.out.println("  Código: " + paquete.getCodigo());
        System.out.println("  Precio: €" + precio);
        System.out.println("  Tiempo estimado: " + paquete.getTiempoEntrega() + " días");
    }

    @Override
    public void entregarPaquete(String codigo) {
        Paquete paquete = rastrearPaquete(codigo);
        if (paquete != null) {
            if (!paquete.isEntregado()) {
                paquete.setEntregado(true);
                System.out.println("✓ Paquete " + codigo + " entregado a " +
                        paquete.getDestinatario());
            } else {
                System.out.println("Este paquete ya fue entregado");
            }
        } else {
            System.out.println("Paquete no encontrado");
        }
    }

    @Override
    public Paquete rastrearPaquete(String codigo) {
        for (Paquete p : paquetes) {
            if (p.getCodigo().equals(codigo)) {
                return p;
            }
        }
        return null;
    }

    public void mostrarPaquetes() {
        System.out.println("\n=== PAQUETES EN SISTEMA ===");
        if (paquetes.isEmpty()) {
            System.out.println("No hay paquetes registrados");
        } else {
            for (Paquete p : paquetes) {
                System.out.println("- " + p);
            }
        }
        System.out.println("Total paquetes: " + paquetes.size());
        System.out.println("Ingresos totales: €" + ingresos);
    }

    public void mostrarPaquetesPendientes() {
        System.out.println("\n=== PAQUETES PENDIENTES ===");
        int contador = 0;
        for (Paquete p : paquetes) {
            if (!p.isEntregado()) {
                System.out.println("- " + p);
                contador++;
            }
        }
        if (contador == 0) {
            System.out.println("No hay paquetes pendientes");
        }
    }
}