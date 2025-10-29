package R2_4_OpenAPI;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

public class DbExporter {

    // ✅ RUTA ABSOLUTA FIJA
    private static final String RESOURCES_PATH = "C:\\Users\\ismae\\OneDrive\\Documentos\\CURSOS\\014 DAM\\2 AÑO\\Acceso a datos\\EJERCICIOS\\R2-jdbc\\src\\main\\resources";

    public void export(List<User> users, String filename) throws Exception {
        File directory = new File(RESOURCES_PATH);

        // Crear carpeta si no existe
        if (!directory.exists()) {
            directory.mkdirs();
        }

        File dbFile = new File(directory, filename);
        String url = "jdbc:sqlite:" + dbFile.getAbsolutePath();

        try (Connection conn = DriverManager.getConnection(url)) {
            // Crear tabla
            String createTableSQL = """
                CREATE TABLE IF NOT EXISTS users (
                    id INTEGER PRIMARY KEY,
                    name TEXT NOT NULL,
                    username TEXT NOT NULL,
                    email TEXT NOT NULL,
                    phone TEXT,
                    website TEXT
                )
                """;

            try (Statement stmt = conn.createStatement()) {
                stmt.execute(createTableSQL);
                System.out.println("✅ Tabla 'users' creada");
            }

            // Insertar datos
            String insertSQL = "INSERT INTO users (id, name, username, email, phone, website) VALUES (?, ?, ?, ?, ?, ?)";

            try (PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
                for (User user : users) {
                    pstmt.setInt(1, user.getId());
                    pstmt.setString(2, user.getName());
                    pstmt.setString(3, user.getUsername());
                    pstmt.setString(4, user.getEmail());
                    pstmt.setString(5, user.getPhone());
                    pstmt.setString(6, user.getWebsite());
                    pstmt.executeUpdate();
                }
            }

            System.out.println("✅ Base de datos SQLite guardada: " + dbFile.getAbsolutePath());
            System.out.println("💾 Total de registros insertados: " + users.size());
        }
    }
}