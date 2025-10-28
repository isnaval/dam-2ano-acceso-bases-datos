package interfaces;

import model.Producto;

public interface Gestionable {
    void agregarProducto(Producto producto);

    Producto buscarProducto(String codigo);

    void actualizarStock(String codigo, int nuevoStock);

    void mostrarInventario();

    boolean realizarVenta(String codigo, int cantidad);

    double obtenerTotalVentas();
}