package R2_1_MySQL;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCMain {
    private static final String URL= "jdbc:mysql://127.0.0.1:3306";
    private static final String USER = "root";
    private static final String PASS = "ismael";

    public static void main(String[] args) {
        Connection conexion = null;
        Statement statement = null;
        ResultSet resultSet = null;
        List<Cuenta> cuentas = new ArrayList<>();

        try{
            conexion = DriverManager.getConnection(URL,USER,PASS);
            System.out.println("Conexion con el servicor MySQL exitosa");

            statement = conexion.createStatement();
            System.out.println("Ejecutando instrucciones DDL ...");
            statement.executeUpdate("DROP DATABASE IF EXISTS bank");            statement.executeUpdate("CREATE DATABASE bank");
            System.out.println("Base de datos `bank` creada");
            statement.executeUpdate("Use bank");

            String createTableSQL = "CREATE TABLE cuentas(" + "id INT PRIMARY KEY AUTO_INCREMENT, " + "codigo VARCHAR(45) UNIQUE, " + "saldo INT)";
            statement.executeUpdate(createTableSQL);
            System.out.println("Tabla `cuentas` creada.");

            String insertDataSQL = "INSERT INTO cuentas (codigo, saldo) VALUES " + "('C001', 1500), ('C002', 23000), ('C003', 500)";
            int filasAfectadas = statement.executeUpdate(insertDataSQL);
            System.out.println(filasAfectadas + " cuentas insertadas");

            String querySQL = "SELECT id, codigo, saldo FROM cuentas";
            resultSet = statement.executeQuery(querySQL);
            System.out.println("\nCargando datos en objetos Cuenta...");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String codigo = resultSet.getString("codigo");
                int saldo = resultSet.getInt("saldo");
                Cuenta cuenta = new Cuenta(id, codigo, saldo);
                cuentas.add(cuenta);
            }

            System.out.println("Datos cargados en List<Cuenta>.");
            System.out.println("\n--- Contenido de la Colección de Cuentas ---");
            cuentas.forEach(System.out::println);
            System.out.println("---------------------------------------------");

        } catch (SQLException e) {
            System.out.println("Ocurrio una Excepción SQL: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (statement != null) statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (conexion != null) conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            System.out.println("\nRecursos de la base de datos cerrados.");
        }

    }

}
