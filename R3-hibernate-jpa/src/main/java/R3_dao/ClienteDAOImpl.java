package R3_dao;

import R3_entities.Cliente;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.List;

/**
 * Implementación del DAO para la entidad Cliente
 * Proporciona acceso a datos usando Hibernate
 */
public class ClienteDAOImpl implements ClienteDAO {

    /**
     * Recupera todos los clientes de la base de datos
     * @return Lista de todos los clientes
     */
    @Override
    public List<Cliente> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        // Consulta HQL (Hibernate Query Language)
        // "from Cliente" es equivalente a "SELECT * FROM clientes" en SQL
        List<Cliente> clientes = session.createQuery("from Cliente", Cliente.class).list();

        // Alternativa en 2 pasos (comentada):
        // Query<Cliente> query = session.createQuery("from Cliente", Cliente.class);
        // List<Cliente> clientes = query.list();

        session.close();
        return clientes;
    }

    /**
     * Busca un cliente por su ID
     * Implementado de 2 formas diferentes para practicar
     * @param id - ID del cliente
     * @return Cliente encontrado o null
     */
    @Override
    public Cliente findById(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Cliente cliente = null;

        // OPCIÓN 1: Usando HQL con parámetro
        /*
        Query<Cliente> query = session.createQuery(
            "from Cliente where id = :id",
            Cliente.class
        );
        query.setParameter("id", id);
        cliente = query.uniqueResult();
        */

        // OPCIÓN 2: Usando el método find() de Session (MÁS SIMPLE)
        cliente = session.find(Cliente.class, id);

        session.close();
        return cliente;
    }

    /**
     * Crea un nuevo cliente en la base de datos
     * @param cliente - Cliente a insertar
     * @return Cliente insertado con su ID generado
     */
    @Override
    public Cliente create(Cliente cliente) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();

            // persist() guarda el objeto en la BD
            session.persist(cliente);

            session.getTransaction().commit();

            System.out.println("✅ Cliente creado: " + cliente.getFirstName() + " " + cliente.getLastName());

        } catch (HibernateException e) {
            System.err.println("❌ Error al crear cliente: " + e.getMessage());
            e.printStackTrace();

            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }

        } finally {
            session.close();
        }

        return cliente;
    }

    /**
     * Actualiza un cliente existente en la base de datos
     * @param cliente - Cliente con los datos actualizados
     * @return Cliente actualizado
     */
    @Override
    public Cliente update(Cliente cliente) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Cliente clienteActualizado = null;

        try {
            session.beginTransaction();

            // merge() actualiza el objeto en la BD
            // La diferencia con persist() es que merge() puede trabajar con objetos desconectados
            clienteActualizado = session.merge(cliente);

            session.getTransaction().commit();

            System.out.println("✅ Cliente actualizado: " + clienteActualizado.getFirstName() + " " + clienteActualizado.getLastName());

        } catch (HibernateException e) {
            System.err.println("❌ Error al actualizar cliente: " + e.getMessage());
            e.printStackTrace();

            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }

        } finally {
            session.close();
        }

        return clienteActualizado;
    }

    /**
     * Elimina un cliente de la base de datos por su ID
     * @param id - ID del cliente a eliminar
     * @return true si se eliminó, false si no existía o hubo error
     */
    @Override
    public boolean deleteById(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        boolean eliminado = false;

        try {
            session.beginTransaction();

            // Primero buscamos el cliente
            Cliente cliente = session.find(Cliente.class, id);

            if (cliente != null) {
                // Si existe, lo eliminamos
                session.remove(cliente);
                session.getTransaction().commit();
                eliminado = true;

                System.out.println("✅ Cliente eliminado: " + cliente.getFirstName() + " " + cliente.getLastName());
            } else {
                System.out.println("⚠️ No existe cliente con ID: " + id);
                session.getTransaction().rollback();
            }

        } catch (HibernateException e) {
            System.err.println("❌ Error al eliminar cliente: " + e.getMessage());
            e.printStackTrace();

            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }

        } finally {
            session.close();
        }

        return eliminado;
    }

    @Override
    public List<Cliente> findLastName(String lastName) {
        return List.of();
    }

    /**
     * Busca clientes por apellido (lastName)
     * @param lastName - Apellido a buscar
     * @return Lista de clientes con ese apellido
     */
    @Override
    public List<Cliente> findByLastName(String lastName) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        // Consulta HQL con parámetro
        // :lastName es un parámetro que reemplazaremos con setParameter()
        Query<Cliente> query = session.createQuery(
                "from Cliente where lastName = :lastName",
                Cliente.class
        );

        // Asignamos el valor al parámetro
        query.setParameter("lastName", lastName);

        // Obtenemos la lista de resultados
        List<Cliente> clientes = query.list();

        session.close();
        return clientes;
    }
}