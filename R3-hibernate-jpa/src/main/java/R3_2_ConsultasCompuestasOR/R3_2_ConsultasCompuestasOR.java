package R3_2_ConsultasCompuestasOR;

import R3_dao.ClienteDAOImpl;
import R3_entities.Cliente;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.List;
import java.util.Scanner;

public class R3_2_ConsultasCompuestasOR {
    private static final Scanner sc = new Scanner(System.in);
    private static final ClienteDAOImpl clienteDAO = new ClienteDAOImpl();

    public static void main(String [] args) {
        System.out.println("=== CONSULTAS COMPUESTAS (OR) ===");
        System.out.print("Introduce un apellido (o deja vacío): ");
        String apellido = sc.nextLine().trim();
        System.out.print("Introduce un pais (o deja en vacío): ");
        String pais = sc.nextLine().trim();
        System.out.print("Introduce una edad mínima (o deja vacío): ");
        String edadTexto = sc.nextLine().trim();
        Integer edadMin = edadTexto.isEmpty() ? null : Integer.parseInt(edadTexto);
    
        List<Cliente> resultados = buscarCompuesto(apellido, pais, edadMin);
        System.out.println("\n--- RESULTADOS ---");
        if(resultados.isEmpty()) {
            System.out.println("No se encontraron clientes con esos criterios de busqueda");
                    } else {
            resultados.forEach(System.out::println);
        }
        HibernateUtil.shutdown();
    }

    private static List<Cliente> buscarCompuesto(String apellido, String pais, Integer edadMin) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        StringBuilder hql = new StringBuilder("from Cliente c where 1=0");
        if (!apellido.isEmpty()) hql.append(" or lower(c.apellido) like :apellido");
        if (!pais.isEmpty()) hql.append(" or lower(c.pais) like :pais");
        if (edadMin != null) hql.append(" or c.edad >= :edadMin");

        Query<Cliente> query = session.createQuery(hql.toString(), Cliente.class);

        if (!apellido.isEmpty()) query.setParameter("apellido", "%" + apellido.toLowerCase() + "%");
        if (!pais.isEmpty()) query.setParameter("pais", "%" + pais.toLowerCase() + "%");
        if (edadMin != null) query.setParameter("edadMin", edadMin);

        List<Cliente> lista = query.list();
        session.close();

        return lista;
    }



}
