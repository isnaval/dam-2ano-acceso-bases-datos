package R3_dao;

import R3_entities.Cliente;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.List;

public class ClienteDAOImpl implements ClienteDAO {

    @Override
    public List<Cliente> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Cliente> clientes = session.createQuery("from Cliente", Cliente.class).list();
        session.close();
        return clientes;
    }

    @Override
    public Cliente findById(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Cliente cliente = session.find(Cliente.class, id);
        session.close();
        return cliente;
    }

    @Override
    public Cliente create(Cliente cliente) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.persist(cliente);
            session.getTransaction().commit();
            System.out.println("OK: Cliente creado: " + cliente.getFirstName() + " " + cliente.getLastName());
        } catch (HibernateException e) {
            System.err.println("ERROR al crear cliente: " + e.getMessage());
            if (session.getTransaction() != null) session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return cliente;
    }

    @Override
    public Cliente update(Cliente cliente) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Cliente clienteActualizado = null;
        try {
            session.beginTransaction();
            clienteActualizado = session.merge(cliente);
            session.getTransaction().commit();
            System.out.println("OK: Cliente actualizado: " + clienteActualizado.getFirstName() + " " + clienteActualizado.getLastName());
        } catch (HibernateException e) {
            System.err.println("ERROR al actualizar cliente: " + e.getMessage());
            if (session.getTransaction() != null) session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return clienteActualizado;
    }

    @Override
    public boolean deleteById(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        boolean eliminado = false;
        try {
            session.beginTransaction();
            Cliente cliente = session.find(Cliente.class, id);
            if (cliente != null) {
                session.remove(cliente);
                session.getTransaction().commit();
                eliminado = true;
                System.out.println("OK: Cliente eliminado: " + cliente.getFirstName() + " " + cliente.getLastName());
            } else {
                System.out.println("AVISO: No existe cliente con ID: " + id);
                session.getTransaction().rollback();
            }
        } catch (HibernateException e) {
            System.err.println("ERROR al eliminar cliente: " + e.getMessage());
            if (session.getTransaction() != null) session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return eliminado;
    }

    @Override
    public List<Cliente> findLastName(String lastName) {
        return List.of();
    }

    @Override
    public List<Cliente> findByLastName(String lastName) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Cliente> query = session.createQuery("from Cliente where lastName = :lastName", Cliente.class);
        query.setParameter("lastName", lastName);
        List<Cliente> clientes = query.list();
        session.close();
        return clientes;
    }

    public List<Cliente> findByApellido(String apellido) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Cliente> clientes = session.createQuery(
                        "from Cliente c where c.apellido = :apellido", Cliente.class)
                .setParameter("apellido", apellido)
                .list();
        session.close();
        return clientes;
    }

    public List<Cliente> findByApellidoParcial(String fragmento) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Cliente> clientes = session.createQuery(
                        "from Cliente c where lower(c.apellido) like :fragmento", Cliente.class)
                .setParameter("fragmento", "%" + fragmento.toLowerCase() + "%")
                .list();
        session.close();
        return clientes;
    }

    public long countClientes() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Long count = session.createQuery("select count(c) from Cliente c", Long.class).uniqueResult();
        session.close();
        return count != null ? count : 0;
    }

    public double promedioEdad() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Double avg = session.createQuery("select avg(c.edad) from Cliente c", Double.class).uniqueResult();
        session.close();
        return avg != null ? avg : 0.0;
    }
}
