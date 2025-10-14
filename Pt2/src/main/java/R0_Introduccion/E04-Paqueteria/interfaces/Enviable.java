package interfaces;

import model.Paquete;

public interface Enviable {
    void enviarPaquete(Paquete paquete);

    void entregarPaquete(String codigo);

    Paquete rastrearPaquete(String codigo);
}