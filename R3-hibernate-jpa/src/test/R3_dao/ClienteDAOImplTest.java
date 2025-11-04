package R3_dao;

import R3_entities.Cliente;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Clase de test para ClienteDAOImpl
 * Prueba todas las operaciones CRUD del DAO
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ClienteDAOImplTest {

    private static ClienteDAOImpl dao;

    /**
     * Se ejecuta una vez antes de todos los tests
     * Crea la instancia del DAO que usaremos en todos los tests
     */
    @BeforeAll
    public static void setUpClass() {
        dao = new ClienteDAOImpl();
        System.out.println("\n========================================");
        System.out.println("🧪 INICIANDO TESTS DE ClienteDAOImpl");
        System.out.println("========================================\n");
    }

    /**
     * Test del método findAll()
     * Debe mostrar todos los clientes de la base de datos
     */
    @Test
    @Order(1)
    public void testFindAll() {
        System.out.println("\n--- Test 1: findAll() ---");

        List<Cliente> clientes = dao.findAll();

        // Mostramos los clientes
        System.out.println("Total de clientes: " + clientes.size());
        clientes.forEach(System.out::println);

        // Verificamos que haya al menos 1 cliente
        Assertions.assertTrue(clientes.size() > 0, "Debería haber al menos 1 cliente");
    }

    /**
     * Test del método findById()
     * Busca clientes por diferentes IDs
     */
    @Test
    @Order(2)
    public void testFindById() {
        System.out.println("\n--- Test 2: findById() ---");

        // Buscar cliente con ID 1
        Cliente cliente1 = dao.findById(1L);
        System.out.println("Cliente ID 1: " + cliente1);
        assertNotNull(cliente1, "El cliente con ID 1 debería existir");

        // Buscar cliente con ID 2
        Cliente cliente2 = dao.findById(2L);
        System.out.println("Cliente ID 2: " + cliente2);

        // Buscar cliente con ID que no existe
        Cliente clienteNoExiste = dao.findById(999L);
        System.out.println("Cliente ID 999: " + clienteNoExiste);
        Assertions.assertNull(clienteNoExiste, "El cliente con ID 999 no debería existir");
    }

    /**
     * Test del método create()
     * Crea un nuevo cliente en la base de datos
     */
    @Test
    @Order(3)
    void testCreate() {
        System.out.println("--- Test 3: create() ---");

        long timestamp = System.currentTimeMillis();
        String uniqueEmail = "test" + timestamp + "@email.com";

        System.out.println("Creando cliente: Test Usuario");
        System.out.println("Email único: " + uniqueEmail + "\n");

        Cliente nuevo = new Cliente("Test", "Usuario", uniqueEmail, 25);
        dao.create(nuevo);

        assertNotNull(nuevo.getId(), "El cliente debería tener un ID asignado");
        System.out.println("✅ Cliente creado: " + nuevo.getFirstName() + " " + nuevo.getLastName());
        System.out.println("Cliente creado con ID: " + nuevo.getId() + "\n");
    }


    @Test
    @Order(4)
    public void testUpdate() {
        System.out.println("\n--- Test 4: update() ---");

        // Buscar un cliente existente
        Cliente cliente = dao.findById(1L);

        if (cliente != null) {
            System.out.println("Cliente antes de actualizar: " + cliente);

            // Modificar algún dato
            int edadAnterior = cliente.getEdad();
            cliente.setEdad(edadAnterior + 1);

            // Actualizar en la BD
            Cliente clienteActualizado = dao.update(cliente);

            System.out.println("Cliente después de actualizar: " + clienteActualizado);

            // Verificar que se actualizó
            Assertions.assertEquals(edadAnterior + 1, clienteActualizado.getEdad(),
                    "La edad debería haberse incrementado en 1");
        } else {
            System.out.println("⚠️ No se encontró el cliente con ID 1");
        }
    }


    @Test
    @Order(5)
    public void testDeleteById() {
        System.out.println("\n--- Test 5: deleteById() ---");

        // Primero creamos un cliente temporal para eliminarlo
        Cliente clienteTemporal = new Cliente(
                "Temporal",
                "Borrar",
                "temporal@email.com",
                99
        );
        Cliente clienteCreado = dao.create(clienteTemporal);
        Long idParaBorrar = clienteCreado.getId();

        System.out.println("Cliente temporal creado con ID: " + idParaBorrar);

        // Intentar borrar el cliente que existe
        boolean eliminado = dao.deleteById(idParaBorrar);
        System.out.println("¿Se eliminó el cliente con ID " + idParaBorrar + "? " + eliminado);
        Assertions.assertTrue(eliminado, "El cliente debería haberse eliminado");

        // Intentar borrar un cliente que no existe
        boolean eliminadoNoExiste = dao.deleteById(999L);
        System.out.println("¿Se eliminó el cliente con ID 999? " + eliminadoNoExiste);
        Assertions.assertFalse(eliminadoNoExiste, "No se debería poder eliminar un cliente que no existe");
    }

    @Test
    @Order(6)
    public void testFindByLastName() {
        System.out.println("\n--- Test 6: findByLastName() ---");

        // Buscar por un apellido que existe
        String apellidoExiste = "García";
        List<Cliente> clientesGarcia = dao.findByLastName(apellidoExiste);
        System.out.println("Clientes con apellido '" + apellidoExiste + "': " + clientesGarcia.size());
        clientesGarcia.forEach(System.out::println);

        // Buscar por un apellido que no existe
        String apellidoNoExiste = "NoExiste";
        List<Cliente> clientesNoExiste = dao.findByLastName(apellidoNoExiste);
        System.out.println("Clientes con apellido '" + apellidoNoExiste + "': " + clientesNoExiste.size());
        Assertions.assertEquals(0, clientesNoExiste.size(),
                "No debería haber clientes con apellido 'NoExiste'");
    }


    @AfterAll
    public static void tearDownClass() {
        System.out.println("\n========================================");
        System.out.println("✅ TESTS COMPLETADOS");
        System.out.println("========================================\n");
    }
}