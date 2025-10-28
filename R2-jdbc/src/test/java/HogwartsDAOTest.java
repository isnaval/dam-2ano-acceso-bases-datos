
import R2_2_JDBC_MySQL.Asignatura;
import R2_2_JDBC_MySQL.Estudiante;
import R2_2_JDBC_MySQL.HogwartsDAO;
import R2_2_JDBC_MySQL.Mascota;
import org.junit.jupiter.api.*;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class HogwartsDAOTest {
    private static Connection conexion;
    private static HogwartsDAO dao;

    @BeforeAll
    static void setUp() {
        conexion = HogwartsDAO.conectar();
        dao = new HogwartsDAO();
        assertNotNull(conexion, "La conexión a la base de datos no debe ser nula.");
    }

    @AfterAll
    static void tearDown() {
        if (conexion != null) {
            try {
                conexion.close();
                System.out.println("\nConexión SQLite cerrada después de todas las pruebas.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // ====================================================================
    // PRUEBAS DE CONSULTA (SELECT)
    // ====================================================================

    @Test
    @Order(1)
    @DisplayName("R1: Obtener Estudiantes por Casa (Gryffindor)")
    void testR1_consultaEstudiantesPorCasa() {
        List<Estudiante> estudiantes = dao.consultaEstudiantesPorCasa(conexion, "Gryffindor");
        // Asume que debe haber al menos 3 estudiantes conocidos en Gryffindor
        assertTrue(estudiantes.size() >= 3, "Debería encontrar al menos 3 estudiantes de Gryffindor.");
        // Verifica que todos son de la casa esperada (idCasa = 1 para Gryffindor)
        assertTrue(estudiantes.stream().allMatch(e -> e.getIdCasa() == 1), "Todos deben ser de Gryffindor.");
    }

    @Test
    @Order(2)
    @DisplayName("R2: Listado de Asignaturas Obligatorias")
    void testR2_listadoAsignaturasObligatorias() {
        List<Asignatura> asignaturas = dao.listadoAsignaturasObligatorias(conexion);
        // Asume que hay al menos 2 asignaturas obligatorias
        assertTrue(asignaturas.size() >= 2, "Debería encontrar al menos 2 asignaturas obligatorias.");
        // Verifica que todas son obligatorias
        assertTrue(asignaturas.stream().allMatch(Asignatura::isEsObligatoria), "Todas deben ser obligatorias.");
    }

    @Test
    @Order(3)
    @DisplayName("R3: Obtener Mascota de un Estudiante (Hermione Granger)")
    void testR3_obtenerMascota() {
        Mascota mascota = dao.obtenerMascota(conexion, "Hermione", "Granger");
        assertNotNull(mascota, "Hermione Granger debería tener una mascota (Crookshanks).");
        assertEquals("Crookshanks", mascota.getNombre(), "El nombre de la mascota debe ser 'Crookshanks'.");
    }

    @Test
    @Order(4)
    @DisplayName("R4: Listado de Estudiantes sin Mascota")
    void testR4_estudiantesSinMascota() {
        List<Estudiante> sinMascota = dao.estudiantesSinMascota(conexion);
        assertFalse(sinMascota.isEmpty(), "Debería haber estudiantes sin mascota.");
        // Se puede añadir una verificación de tamaño si se conoce el dato exacto
    }

    @Test
    @Order(5)
    @DisplayName("R5: Promedio de Calificaciones (Harry Potter)")
    void testR5_promedioCalificaciones() {
        double promedio = dao.promedioCalificaciones(conexion, "Harry", "Potter");
        // Asume que Harry tiene calificaciones, el promedio debe ser mayor a 0
        assertTrue(promedio > 0.0, "El promedio de calificaciones debe ser un valor positivo.");
    }

    @Test
    @Order(6)
    @DisplayName("R6: Número de Estudiantes por Casa (Conteo)")
    void testR6_numeroEstudiantesPorCasa() {
        Map<String, Integer> conteo = dao.numeroEstudiantesPorCasa(conexion);
        assertFalse(conteo.isEmpty(), "El conteo por casa no debe estar vacío.");
        assertTrue(conteo.containsKey("Slytherin"), "El conteo debe incluir Slytherin.");
        assertTrue(conteo.get("Gryffindor") > 0, "Debe haber estudiantes en Gryffindor.");
    }

    @Test
    @Order(7)
    @DisplayName("R7: Estudiantes Matriculados en Asignatura")
    void testR7_estudiantesMatriculadosEn() {
        List<Estudiante> matriculados = dao.estudiantesMatriculadosEn(conexion, "Defensa Contra las Artes Oscuras");
        assertTrue(matriculados.size() >= 1, "Debería haber estudiantes matriculados en DCAO.");
        // Guarda el conteo inicial para la prueba R10
        Assertions.assertEquals(3, matriculados.size(), "Debe haber 3 estudiantes matriculados inicialmente en DCAO.");
    }

    // ====================================================================
    // PRUEBAS DE MODIFICACIÓN (INSERT, UPDATE, DELETE)
    // ====================================================================

    @Test
    @Order(8)
    @DisplayName("R8: Insertar Nuevo Estudiante (Luna Lovegood)")
    void testR8_insertarEstudiante() {
        // ID 3 corresponde a Ravenclaw
        boolean resultado = dao.insertarEstudiante(conexion, "Luna", "Lovegood", 3);
        assertTrue(resultado, "La inserción de un nuevo estudiante debe ser exitosa.");

        // Verificación: Se puede buscar a Luna Lovegood para confirmar la inserción
        List<Estudiante> ravenclaw = dao.consultaEstudiantesPorCasa(conexion, "Ravenclaw");
        assertTrue(ravenclaw.stream().anyMatch(e -> e.getNombre().equals("Luna")), "Luna Lovegood debe aparecer en Ravenclaw.");
    }

    @Test
    @Order(9)
    @DisplayName("R9: Modificar Aula de Asignatura (Adivinación)")
    void testR9_modificarAulaAsignatura() {
        String asigModificar = "Adivinación";
        String nuevaAula = "Torre Norte (TEST)";
        boolean resultado = dao.modificarAulaAsignatura(conexion, asigModificar, nuevaAula);
        assertTrue(resultado, "La modificación del aula debe ser exitosa.");

        // Verificación: Se necesitaría un método adicional en DAO para obtener el aula por nombre,
        // pero por simplicidad, confiamos en el 'assertTrue(resultado)' por ahora.
    }

    @Test
    @Order(10)
    @DisplayName("R10: Desmatricular Estudiante (Harry Potter de DCAO)")
    void testR10_desmatricularEstudiante() {
        // Contar matriculados antes
        int antes = dao.estudiantesMatriculadosEn(conexion, "Defensa Contra las Artes Oscuras").size();

        // Desmatricular a Harry Potter
        boolean desmatriculado = dao.desmatricularEstudiante(conexion, "Harry", "Potter", "Defensa Contra las Artes Oscuras");
        assertTrue(desmatriculado, "La desmatriculación de Harry Potter debe ser exitosa.");

        // Contar matriculados después
        int despues = dao.estudiantesMatriculadosEn(conexion, "Defensa Contra las Artes Oscuras").size();

        // Debe haber un estudiante menos
        assertEquals(antes - 1, despues, "Debe haber exactamente un estudiante menos después de desmatricular.");
    }
}