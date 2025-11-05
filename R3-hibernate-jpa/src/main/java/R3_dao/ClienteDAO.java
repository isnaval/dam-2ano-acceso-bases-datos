package R3_dao;

import R3_entities.Cliente;
import java.util.List;

/**
 * DATA ACCESS OBJECT
 */

/**
 * Interface DAO para la entidad Cliente
 * Define las operaciones CRUD (Create, Read, Update, Delete)
 */

public interface ClienteDAO {

    // ========== CRUD BÁSICO ==========
    List<Cliente> findAll();
    Cliente findById(Long id);
    Cliente create(Cliente cliente);
    Cliente update(Cliente cliente);
    boolean deleteById(Long id);

    // ========== BÚSQUEDAS SIMPLES ==========
    List<Cliente> findByLastName(String lastName);
    List<Cliente> findByApellido(String apellido);
    List<Cliente> findByApellidoParcial(String fragmento);

    // ========== ESTADÍSTICAS ==========
    long countClientes();
    double promedioEdad();

    // ========== CONSULTAS COMPUESTAS ==========
    List<Cliente> findByLastNameOR(String apellido1, String apellido2);
    List<Cliente> findByLastNameAndAgeGreaterThan(String apellido, int edad);
    List<Cliente> findByLastNameAndAgeLessThan(String apellido, int edad);
    List<Cliente> findByAgeBetween(int edadMin, int edadMax);
    List<Cliente> findByLastNameOrAgeGreaterThan(String apellido, int edad);

}
