package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {

	private static final String URL = "jdbc:mysql://root:0xXF9X7DAdSc7hp5at06@containers-us-west-101.railway.app:5634/railway";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "0xXF9X7DAdSc7hp5at06";
    
    public static Connection conectar() {
    	Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.out.println("Driver JDBC não encontrado: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
        return connection;
    }

    public static void desconectar(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar a conexão: " + e.getMessage());
            }
        }
    }
}

