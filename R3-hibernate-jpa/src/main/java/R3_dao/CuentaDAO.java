package R3_dao;

import R3_entities.Cuenta;

import java.util.List;

public interface  CuentaDAO {
    // CRUD Básico
    List<Cuenta> findAll();
    Cuenta findById(Long id);
    Cuenta create(Cuenta cuenta);
    Cuenta update(Cuenta cuenta);
    boolean deleteById(Long id);

    // Búsquedas específicas
    List<Cuenta> findByClienteId(Long clienteId);
    List<Cuenta> findByClienteApellido(String apellido);
    List<Cuenta> findByTipoCuenta(String tipo);
}
