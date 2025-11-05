package R3_dao;

import R3_entities.Cuenta;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import util.HibernateUtil;

import java.util.List;

public class CuentaDAOImpl implements CuentaDAO {


    // ========== CRUD BÁSICO ==========

    @Override
    public List<Cuenta> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            return session.createQuery("FROM Cuenta", Cuenta.class).list();
        } finally {
            session.close();
        }
    }

    @Override
    public Cuenta findById(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            return session.find(Cuenta.class, id);
        } finally {
            session.close();
        }
    }

    @Override
    public Cuenta create(Cuenta cuenta) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.persist(cuenta);
            session.getTransaction().commit();
            System.out.println("OK: Cuenta creada: " + cuenta.getNumeroCuenta());
        } catch (HibernateException e) {
            System.err.println("ERROR: al crear cuenta: " + e.getMessage());
            if (session.getTransaction() != null) session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return cuenta;
    }

    @Override
    public Cuenta update(Cuenta cuenta) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Cuenta cuentaActualizada = null;
        try {
            session.beginTransaction();
            cuentaActualizada = session.merge(cuenta);
            session.getTransaction().commit();
            System.out.println("OK: Cuenta actualizada: " + cuentaActualizada.getNumeroCuenta());
        } catch (HibernateException e) {
            System.err.println("ERROR: al actualizar: " + e.getMessage());
            if (session.getTransaction() != null) session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return cuentaActualizada;
    }

    @Override
    public boolean deleteById(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        boolean eliminado = false;
        try {
            session.beginTransaction();
            Cuenta cuenta = session.find(Cuenta.class, id);
            if (cuenta != null) {
                session.remove(cuenta);
                session.getTransaction().commit();
                eliminado = true;
                System.out.println("OK: Cuenta eliminada: " + cuenta.getNumeroCuenta());
            } else {
                System.out.println("No existe cuenta con ID: " + id);
            }
        } catch (HibernateException e) {
            System.err.println("ERROR: al eliminar: " + e.getMessage());
            if (session.getTransaction() != null) session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return eliminado;
    }

    // ========== BÚSQUEDAS ESPECÍFICAS ==========


    @Override
    public List<Cuenta> findByClienteId(Long clienteId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            return session.createQuery(
                            "FROM Cuenta WHERE cliente.id = :clienteId", Cuenta.class)
                    .setParameter("clienteId", clienteId)
                    .list();
        } finally {
            session.close();
        }
    }


    @Override
    public List<Cuenta> findByClienteApellido(String apellido) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            return session.createQuery(
                            "FROM Cuenta c WHERE c.cliente.lastName = :apellido", Cuenta.class)
                    .setParameter("apellido", apellido)
                    .list();
        } finally {
            session.close();
        }
    }


    @Override
    public List<Cuenta> findByTipoCuenta(String tipo) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            return session.createQuery(
                            "FROM Cuenta WHERE tipoCuenta = :tipo", Cuenta.class)
                    .setParameter("tipo", tipo)
                    .list();
        } finally {
            session.close();
        }
    }
}
