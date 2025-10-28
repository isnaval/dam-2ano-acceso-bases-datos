package R3_entities;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import util.HibernateUtil;

public class ClienteTest {
    @Test
    void createTablesTest() {

        // Crear 2 o 3 objetos Cliente de prueba
        Cliente cliente1 = new Cliente("Juan", "Pérez", "juan.perez@email.com", 30);
        Cliente cliente2 = new Cliente("María", "García", "maria.garcia@email.com", 25);
        Cliente cliente3 = new Cliente("Carlos", "López", "carlos.lopez@email.com", 35);

        // Obtener SessionFactory
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();  // ✅
        Session session = sessionFactory.openSession();

        // Iniciar transacción
        session.beginTransaction();

        // Persistir los clientes
        session.persist(cliente1);
        session.persist(cliente2);
        session.persist(cliente3);

        // Hacer commit de la transacción
        session.getTransaction().commit();

        // Cerrar sesión
        session.close();
        sessionFactory.close();
        HibernateUtil.shutdown();

        System.out.println("Test completado: 3 clientes insertados en la base de datos");
    }
}