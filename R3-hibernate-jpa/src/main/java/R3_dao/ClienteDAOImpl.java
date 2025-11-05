package R3_dao;

import R3_entities.Cliente;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.List;

public class ClienteDAOImpl implements ClienteDAO {

    // ========== CRUD BÁSICO ==========

    @Override
    public List<Cliente> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            return session.createQuery("FROM Cliente", Cliente.class).list();
        } finally {
            session.close();
        }
    }

    @Override
    public Cliente findById(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            return session.find(Cliente.class, id);
        } finally {
            session.close();
        }
    }

    @Override
    public Cliente create(Cliente cliente) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.persist(cliente);
            session.getTransaction().commit();
            System.out.println("✅ Cliente creado: " + cliente.getFirstName() + " " + cliente.getLastName());
        } catch (HibernateException e) {
            System.err.println("❌ Error al crear cliente: " + e.getMessage());
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
            System.out.println("✅ Cliente actualizado: " + clienteActualizado.getFirstName());
        } catch (HibernateException e) {
            System.err.println("❌ Error al actualizar: " + e.getMessage());
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
                System.out.println("✅ Cliente eliminado: " + cliente.getFirstName());
            } else {
                System.out.println("⚠️ No existe cliente con ID: " + id);
            }
        } catch (HibernateException e) {
            System.err.println("❌ Error al eliminar: " + e.getMessage());
            if (session.getTransaction() != null) session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return eliminado;
    }

    // ========== BÚSQUEDAS SIMPLES ==========

    @Override
    public List<Cliente> findByLastName(String lastName) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            return session.createQuery(
                            "FROM Cliente WHERE lastName = :lastName", Cliente.class)
                    .setParameter("lastName", lastName)
                    .list();
        } finally {
            session.close();
        }
    }

    @Override
    public List<Cliente> findByApellidoParcial(String fragmento) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            return session.createQuery(
                            "FROM Cliente WHERE LOWER(lastName) LIKE :fragmento", Cliente.class)
                    .setParameter("fragmento", "%" + fragmento.toLowerCase() + "%")
                    .list();
        } finally {
            session.close();
        }
    }

    @Override
    public List<Cliente> findByApellido(String apellido) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            return session.createQuery(
                            "FROM Cliente WHERE lastName = :apellido", Cliente.class)
                    .setParameter("apellido", apellido)
                    .list();
        } finally {
            session.close();
        }
    }

    // ========== ESTADÍSTICAS ==========

    @Override
    public long countClientes() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Long count = session.createQuery(
                            "SELECT COUNT(c) FROM Cliente c", Long.class)
                    .uniqueResult();
            return count != null ? count : 0;
        } finally {
            session.close();
        }
    }

    @Override
    public double promedioEdad() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Double avg = session.createQuery(
                            "SELECT AVG(c.edad) FROM Cliente c", Double.class)
                    .uniqueResult();
            return avg != null ? avg : 0.0;
        } finally {
            session.close();
        }
    }

    // ========== CONSULTAS COMPUESTAS (R3_3) ==========

    @Override
    public List<Cliente> findByLastNameOR(String apellido1, String apellido2) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            return session.createQuery(
                            "FROM Cliente WHERE lastName = :ap1 OR lastName = :ap2", Cliente.class)
                    .setParameter("ap1", apellido1)
                    .setParameter("ap2", apellido2)
                    .list();
        } finally {
            session.close();
        }
    }

    @Override
    public List<Cliente> findByLastNameAndAgeGreaterThan(String apellido, int edad) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            return session.createQuery(
                            "FROM Cliente WHERE lastName = :ap AND edad > :edad", Cliente.class)
                    .setParameter("ap", apellido)
                    .setParameter("edad", edad)
                    .list();
        } finally {
            session.close();
        }
    }

    @Override
    public List<Cliente> findByLastNameAndAgeLessThan(String apellido, int edad) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            return session.createQuery(
                            "FROM Cliente WHERE lastName = :ap AND edad < :edad", Cliente.class)
                    .setParameter("ap", apellido)
                    .setParameter("edad", edad)
                    .list();
        } finally {
            session.close();
        }
    }

    @Override
    public List<Cliente> findByAgeBetween(int edadMin, int edadMax) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            return session.createQuery(
                            "FROM Cliente WHERE edad BETWEEN :min AND :max", Cliente.class)
                    .setParameter("min", edadMin)
                    .setParameter("max", edadMax)
                    .list();
        } finally {
            session.close();
        }
    }

    @Override
    public List<Cliente> findByLastNameOrAgeGreaterThan(String apellido, int edad) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            return session.createQuery(
                            "FROM Cliente WHERE lastName = :ap OR edad > :edad", Cliente.class)
                    .setParameter("ap", apellido)
                    .setParameter("edad", edad)
                    .list();
        } finally {
            session.close();
        }
    }
}