package R2_2_JDBC_MySQL;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;

public class HogwartsDAO {
    // ⚠️ Asegúrate que USER y PASS sean tus credenciales de MySQL
    private static final String URL = "jdbc:mysql://localhost:3306/hogwarts_db";
    private static final String USER = "root";
    private static final String PASS = "ismael"; // Tu contraseña

    // CONEXIÓN
    public static Connection conectar() {
        Connection conexion = null;
        try {
            // Se usa el Driver de MySQL y se pasan URL, USER y PASS
            conexion = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("Conexion exitosa a la BBDD MySQL: hogwarts_db");
            return conexion;
        } catch (SQLException e) {
            System.err.println("Error al conectar MySQL. Asegúrate que el servidor esté ON y la BD exista: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    // ====================================================================
    // REQUISITOS DE CONSULTA
    // ====================================================================

    // Estudiantes por casa (R1 - CORREGIDO: "año_curso" -> "anio_curso")
    public List<Estudiante> consultaEstudiantesPorCasa(Connection conexion, String nombreCasa) {
        String consultaSQL = "SELECT e.* FROM Estudiante e JOIN Casa c ON e.id_casa = c.id_casa WHERE c.nombre_casa = ?";
        List<Estudiante> estudiantes = new ArrayList<>();
        try (PreparedStatement consulta = conexion.prepareStatement(consultaSQL)) {
            consulta.setString(1, nombreCasa);
            try (ResultSet resultados = consulta.executeQuery()) {
                while (resultados.next()) {
                    estudiantes.add(new Estudiante(
                            resultados.getInt("id_estudiante"),
                            resultados.getString("nombre"),
                            resultados.getString("apellido"),
                            resultados.getInt("id_casa"),
                            resultados.getInt("anio_curso"),
                            resultados.getString("fecha_nacimiento")
                    ));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error R1: " + e.getMessage());
        }
        return estudiantes;
    }

    // Asignaturas Obligatorias (R2 - CORREGIDO: "obligatoria" -> "es_obligatoria" en SQL y lectura)
    public List<Asignatura> listadoAsignaturasObligatorias(Connection conexion) {
        // ✅ CORREGIDO: Usamos el nombre de columna correcto 'es_obligatoria' en el WHERE
        String consultaSQL = "SELECT * FROM Asignatura WHERE es_obligatoria = 1";
        List<Asignatura> asignaturas = new ArrayList<>();
        try (Statement consulta = conexion.createStatement();
             ResultSet resultados = consulta.executeQuery(consultaSQL)) {
            while (resultados.next()) {
                asignaturas.add(new Asignatura(
                        resultados.getString("nombre_asignatura"),
                        resultados.getInt("id_asignatura"),
                        resultados.getString("aula"),
                        resultados.getBoolean("es_obligatoria"),
                        resultados.getInt("id_profesor")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error R2: " + e.getMessage());
        }
        return asignaturas;
    }

    // Mascota de un estudiante (R3 - CORREGIDO: "nombre_mascota" -> "nombre")
    public Mascota obtenerMascota(Connection conexion, String nombreEstudiante, String apellidoEstudiante) {
        String consultaSQL = "SELECT m.* FROM Mascota m JOIN Estudiante e ON m.id_estudiante = e.id_estudiante WHERE e.nombre = ? AND e.apellido = ?";
        Mascota mascota = null;
        try (PreparedStatement consulta = conexion.prepareStatement(consultaSQL)) {
            consulta.setString(1, nombreEstudiante);
            consulta.setString(2, apellidoEstudiante);
            try (ResultSet resultados = consulta.executeQuery()) {
                if (resultados.next()) {
                    mascota = new Mascota(
                            resultados.getInt("id_mascota"),
                            resultados.getString("nombre"),
                            resultados.getString("tipo"),
                            resultados.getInt("id_estudiante")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error R3: " + e.getMessage());
        }
        return mascota;
    }

    // Estudiantes sin mascota (R4 - CORREGIDO: "año_curso" -> "anio_curso")
    public List<Estudiante> estudiantesSinMascota(Connection conexion) {
        String consultaSQL = "SELECT e.* FROM Estudiante e LEFT JOIN Mascota m ON e.id_estudiante = m.id_estudiante WHERE m.id_mascota IS NULL";
        List<Estudiante> estudiantes = new ArrayList<>();
        try (Statement consulta = conexion.createStatement();
             ResultSet resultados = consulta.executeQuery(consultaSQL)) {
            while (resultados.next()) {
                estudiantes.add(new Estudiante(
                        resultados.getInt("id_estudiante"),
                        resultados.getString("nombre"),
                        resultados.getString("apellido"),
                        resultados.getInt("id_casa"),
                        resultados.getInt("anio_curso"),
                        resultados.getString("fecha_nacimiento")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error R4: " + e.getMessage());
        }
        return estudiantes;
    }

    // Promedio de calificaciones (R5 - Correcto)
    public double promedioCalificaciones(Connection conexion, String nombre, String apellido) {
        String consultaSQL = "SELECT AVG(c.calificacion) AS promedio FROM Calificacion c JOIN Estudiante e ON c.id_estudiante = e.id_estudiante WHERE e.nombre = ? AND e.apellido = ?";
        double promedio = 0.0;
        try (PreparedStatement consulta = conexion.prepareStatement(consultaSQL)) {
            consulta.setString(1, nombre);
            consulta.setString(2, apellido);
            try (ResultSet resultados = consulta.executeQuery()) {
                if (resultados.next()) {
                    promedio = resultados.getDouble("promedio");
                }
            }
        } catch (SQLException e) {
            System.err.println("Error R5: " + e.getMessage());
        }
        return promedio;
    }

    // Número de estudiantes por casa (R6 - Correcto)
    public Map<String, Integer> numeroEstudiantesPorCasa(Connection conexion) {
        String consultaSQL = "SELECT c.nombre_casa, COUNT(e.id_estudiante) AS total_estudiantes " +
                "FROM Casa c JOIN Estudiante e ON c.id_casa = e.id_casa GROUP BY c.nombre_casa";
        Map<String, Integer> conteoPorCasa = new LinkedHashMap<>();
        try (Statement consulta = conexion.createStatement();
             ResultSet resultados = consulta.executeQuery(consultaSQL)) {
            while (resultados.next()) {
                conteoPorCasa.put(resultados.getString("nombre_casa"), resultados.getInt("total_estudiantes"));
            }
        } catch (SQLException e) {
            System.err.println("Error R6: " + e.getMessage());
        }
        return conteoPorCasa;
    }

    // ====================================================================
    // REQUISITOS DE CONSULTA Y MODIFICACIÓN
    // ====================================================================

    // Estudiantes matriculados en una asignatura (R7 - CORREGIDO: "año_curso" y tabla "Estudiante_Asignatura" a "Matricula")
    public List<Estudiante> estudiantesMatriculadosEn(Connection conexion, String nombreAsignatura) {
        String consultaSQL = "SELECT e.id_estudiante, e.nombre, e.apellido, e.id_casa, e.anio_curso, e.fecha_nacimiento " +
                "FROM Estudiante e JOIN Matricula ea ON e.id_estudiante = ea.id_estudiante " +
                "JOIN Asignatura a ON ea.id_asignatura = a.id_asignatura " +
                "WHERE a.nombre_asignatura = ?"; // Ya estaba corregido

        List<Estudiante> estudiantes = new ArrayList<>();

        try (PreparedStatement consulta = conexion.prepareStatement(consultaSQL)) {
            consulta.setString(1, nombreAsignatura);
            try (ResultSet resultados = consulta.executeQuery()) {
                while (resultados.next()) {
                    estudiantes.add(new Estudiante(
                            resultados.getInt("id_estudiante"),
                            resultados.getString("nombre"),
                            resultados.getString("apellido"),
                            resultados.getInt("id_casa"),
                            resultados.getInt("anio_curso"),
                            resultados.getString("fecha_nacimiento")
                    ));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error R7: " + e.getMessage());
        }
        return estudiantes;
    }

    // Insertar nuevo estudiante (R8 - CORREGIDO: "año_curso" -> "anio_curso")
    public boolean insertarEstudiante(Connection conexion, String nombre, String apellido, int idCasa) {
        // ✅ CORREGIDO: Usamos el nombre de columna correcto 'anio_curso'
        String insertSQL = "INSERT INTO Estudiante (nombre, apellido, id_casa, anio_curso, fecha_nacimiento) " +
                "VALUES (?, ?, ?, 1, DATE(NOW()))"; // DATE(NOW()) para MySQL

        try (PreparedStatement consulta = conexion.prepareStatement(insertSQL)) {
            consulta.setString(1, nombre);
            consulta.setString(2, apellido);
            consulta.setInt(3, idCasa);
            return consulta.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error R8: " + e.getMessage());
            return false;
        }
    }

    // Modificar el aula de una asignatura (R9 - Correcto)
    public boolean modificarAulaAsignatura(Connection conexion, String nombreAsignatura, String nuevaAula) {
        String updateSQL = "UPDATE Asignatura SET aula = ? WHERE nombre_asignatura = ?"; // Ya estaba corregido

        try (PreparedStatement consulta = conexion.prepareStatement(updateSQL)) {
            consulta.setString(1, nuevaAula);
            consulta.setString(2, nombreAsignatura);
            return consulta.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error R9: " + e.getMessage());
            return false;
        }
    }

    // Desmatricular a un estudiante de una asignatura (R10 - CORREGIDO: tabla "Estudiante_Asignatura" a "Matricula")
    public boolean desmatricularEstudiante(Connection conexion, String nombreEstudiante, String apellidoEstudiante, String nombreAsignatura) {
        // ✅ CORREGIDO: Usamos el nombre de tabla correcto 'Matricula'
        String deleteSQL = "DELETE FROM Matricula " +
                "WHERE id_estudiante = (SELECT id_estudiante FROM Estudiante WHERE nombre = ? AND apellido = ?) " +
                "AND id_asignatura = (SELECT id_asignatura FROM Asignatura WHERE nombre_asignatura = ?)";

        try (PreparedStatement consulta = conexion.prepareStatement(deleteSQL)) {
            consulta.setString(1, nombreEstudiante);
            consulta.setString(2, apellidoEstudiante);
            consulta.setString(3, nombreAsignatura);
            return consulta.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error R10: " + e.getMessage());
            return false;
        }
    }


    // ====================================================================
    // MÉTODO MAIN PARA PRUEBAS (Tal como lo solicitaste)
    // ====================================================================
    public static void main(String[] args) {
        Connection conn = conectar(); // R0: Conexión
        HogwartsDAO dao = new HogwartsDAO();

        if (conn != null) {

            // ====================================================================
            // PRUEBAS DE CONSULTA
            // ====================================================================

            // --- PRUEBA 1: Estudiantes de Gryffindor ---
            System.out.println("\n--- 1. Estudiantes de Gryffindor (R1) ---");
            List<Estudiante> estudiantesGryffindor = dao.consultaEstudiantesPorCasa(conn, "Gryffindor");
            estudiantesGryffindor.forEach(System.out::println);

            // --- PRUEBA 2: Asignaturas Obligatorias ---
            System.out.println("\n--- 2. Asignaturas Obligatorias (R2) ---");
            List<Asignatura> asignaturas = dao.listadoAsignaturasObligatorias(conn);
            asignaturas.forEach(System.out::println);

            // --- PRUEBA 3: Mascota de Hermione Granger ---
            System.out.println("\n--- 3. Mascota de Hermione Granger (R3) ---");
            Mascota mascota = dao.obtenerMascota(conn, "Hermione", "Granger");
            System.out.println(mascota != null ? "Mascota: " + mascota.getNombre() : "Mascota no encontrada.");

            // --- PRUEBA 4: Estudiantes sin Mascota ---
            System.out.println("\n--- 4. Estudiantes sin Mascota (R4) ---");
            List<Estudiante> sinMascota = dao.estudiantesSinMascota(conn);
            sinMascota.forEach(System.out::println);

            // --- PRUEBA 5: Promedio de Calificaciones (Harry Potter) ---
            System.out.println("\n--- 5. Promedio de Calificaciones de Harry Potter (R5) ---");
            double avg = dao.promedioCalificaciones(conn, "Harry", "Potter");
            System.out.printf("El promedio de Harry Potter es: %.2f\n", avg);

            // --- PRUEBA 6: Número de Estudiantes por Casa ---
            System.out.println("\n--- 6. Número de Estudiantes por Casa (R6) ---");
            Map<String, Integer> conteo = dao.numeroEstudiantesPorCasa(conn);
            conteo.forEach((casa, total) ->
                    System.out.printf("%s: %d estudiantes\n", casa, total)
            );

            // ====================================================================
            // PRUEBAS DE MODIFICACIÓN Y R7
            // ====================================================================

            // --- PRUEBA 7: Estudiantes matriculados en 'Defensa Contra las Artes Oscuras' ---
            System.out.println("\n--- 7. Matriculados en D.C.A.O. (R7) ---");
            String asignaturaDCAO = "Defensa Contra las Artes Oscuras";
            List<Estudiante> matriculadosInicial = dao.estudiantesMatriculadosEn(conn, asignaturaDCAO);
            System.out.println("Inicialmente matriculados en DCAO: " + matriculadosInicial.size());

            // --- PRUEBA 8: Insertar Nuevo Estudiante (Ej: Luna Lovegood) ---
            System.out.println("\n--- 8. Insertar Nuevo Estudiante (R8) ---");
            boolean insertado = dao.insertarEstudiante(conn, "Luna", "Lovegood", 3);
            System.out.println("Insertado 'Luna Lovegood': " + (insertado ? "Éxito" : "Fallo"));

            // --- PRUEBA 9: Modificar Aula de 'Adivinación' ---
            System.out.println("\n--- 9. Modificar Aula Asignatura (R9) ---");
            String asigModificar = "Adivinación";
            String nuevaAula = "Torre Norte (Nueva)";
            boolean modificado = dao.modificarAulaAsignatura(conn, asigModificar, nuevaAula);
            System.out.println("Aula de '" + asigModificar + "' modificada a '" + nuevaAula + "': " + (modificado ? "Éxito" : "Fallo"));

            // --- PRUEBA 10: Desmatricular a un estudiante (ej. Harry Potter de D.C.A.O.) ---
            System.out.println("\n--- 10. Desmatricular Estudiante (R10) ---");
            String estudianteDesmatricular = "Harry";
            String apellidoDesmatricular = "Potter";

            boolean desmatriculado = dao.desmatricularEstudiante(conn, estudianteDesmatricular, apellidoDesmatricular, asignaturaDCAO);
            System.out.println("Desmatriculado " + estudianteDesmatricular + " de DCAO: " + (desmatriculado ? "Éxito" : "Fallo"));

            // VERIFICACIÓN FINAL DEL DELETE (R10)
            System.out.println("\n--- Verificación Final R10 ---");
            List<Estudiante> matriculadosFinal = dao.estudiantesMatriculadosEn(conn, asignaturaDCAO);
            System.out.println("Matriculados restantes en DCAO: " + matriculadosFinal.size());


            // Cierre de la conexión al finalizar las pruebas
            try {
                conn.close();
                System.out.println("\nConexión MySQL cerrada.");
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }
}