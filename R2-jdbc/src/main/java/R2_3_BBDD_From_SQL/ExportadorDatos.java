package R2_3_BBDD_From_SQL;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class ExportadorDatos {

    // ⚠️ Configuración de la conexión a MySQL
    private static final String URL = "jdbc:mysql://localhost:3306/hogwarts_db";
    private static final String USER = "root";
    private static final String PASS = "ismael";

    /**
     * Exporta todos los datos de la tabla Estudiante a un archivo CSV.
     * @param rutaArchivo La ruta donde se creará el archivo CSV.
     * @return true si la exportación fue exitosa, false en caso contrario.
     */
    public boolean exportarEstudiantesACsv(String rutaArchivo) {
        String consultaSQL = "SELECT id_estudiante, nombre, apellido, id_casa, anio_curso, fecha_nacimiento FROM Estudiante";

        // Objeto File para manejar la ruta del archivo
        File outputFile = new File(rutaArchivo);
        File parentDir = outputFile.getParentFile();

        // 1. Lógica para verificar y crear el directorio
        if (parentDir != null && !parentDir.exists()) {
            System.out.println("Intentando crear carpeta: " + parentDir.getAbsolutePath());
            if (parentDir.mkdirs()) {
                System.out.println("Carpeta de destino creada exitosamente.");
            } else {
                System.err.println("Error: No se pudo crear la carpeta de destino. Verifica permisos.");
                return false;
            }
        }

        try (Connection conexion = DriverManager.getConnection(URL, USER, PASS);
             Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(consultaSQL);
             PrintWriter writer = new PrintWriter(new FileWriter(outputFile))) {

            // 2. Escribir el encabezado (nombres de columnas)
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            StringBuilder header = new StringBuilder();
            for (int i = 1; i <= columnCount; i++) {
                header.append(metaData.getColumnLabel(i));
                if (i < columnCount) {
                    header.append(",");
                }
            }
            writer.println(header.toString());

            // 3. Escribir los datos
            while (rs.next()) {
                StringBuilder row = new StringBuilder();
                for (int i = 1; i <= columnCount; i++) {
                    row.append(rs.getString(i));
                    if (i < columnCount) {
                        row.append(",");
                    }
                }
                writer.println(row.toString());
            }

            System.out.println("✅ Exportación de Estudiantes completada en: " + outputFile.getAbsolutePath());
            return true;

        } catch (SQLException e) {
            System.err.println("Error de base de datos durante la exportación: " + e.getMessage());
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            System.err.println("Error de E/S al escribir el archivo (Final): " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        ExportadorDatos exportador = new ExportadorDatos();

        // 🎯 RUTA AJUSTADA para garantizar que se crea dentro de la carpeta R2-jdbc
        // Usamos el nombre del proyecto como parte de la ruta relativa:
        String rutaDestino = "R2-jdbc/src/main/resources/estudiantes_exportados.csv";

        exportador.exportarEstudiantesACsv(rutaDestino);
    }
}