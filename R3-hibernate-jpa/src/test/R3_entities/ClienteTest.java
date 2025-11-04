package R3_entities;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import util.HibernateUtil;

public class ClienteTest {
    @Test
    void createTablesTest() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        session.beginTransaction();

        // Usar timestamp para emails únicos
        long timestamp = System.currentTimeMillis();

        // Crear clientes de prueba con emails únicos
        session.persist(new Cliente("Juan", "Pérez", "juan" + timestamp + "@email.com", 30));
        session.persist(new Cliente("María", "García", "maria" + timestamp + "@email.com", 25));
        session.persist(new Cliente("Carlos", "López", "carlos" + timestamp + "@email.com", 35));

        session.getTransaction().commit();
        session.close();

        System.out.println("✅ Test completado: 3 clientes insertados en la base de datos");
        System.out.println("✅ Emails únicos generados con timestamp: " + timestamp);
    }
}