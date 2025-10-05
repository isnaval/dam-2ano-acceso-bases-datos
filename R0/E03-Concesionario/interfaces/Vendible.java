package interfaces;

import model.Vehiculo;

public interface Vendible {
    boolean vender(Vehiculo vehiculo, String comprador);
    double calcularPrecioFinal(Vehiculo vehiculo);
    void mostrarDetallesVenta(Vehiculo vehiculo);
}