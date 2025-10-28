package R3_dao;

import R3_entities.Cliente;
import java.util.List;

/**
 * Interface DAO para la entidad Cliente
 * Define las operaciones CRUD (Create, Read, Update, Delete)
 */
public interface ClienteDAO {

    List<Cliente> findAll();
    Cliente findById(Long id);
    Cliente create(Cliente cliente);
    Cliente update (Cliente cliente);
    boolean deleteById(Long id);
    List<Cliente> findLastName(String lastName);

    List<Cliente> findByLastName(String lastName);
}
