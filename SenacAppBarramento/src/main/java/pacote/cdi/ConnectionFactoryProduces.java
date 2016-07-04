package pacote.cdi;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactoryProduces implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static Connection getConnection() {
		String url = "jdbc:postgresql://localhost/senac_app";
		try {
			Class.forName("org.postgresql.Driver");
			System.out.println("Abrindo Connection");
			return DriverManager.getConnection(url, "postgres", "12345");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static void closeConnection(Connection connection) {		
		try {
			connection.close();
			System.out.println("Fechando Connection");
		} catch (SQLException e) {
			try {
				System.out.println("Rollback Connection");
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
				throw new RuntimeException(e);
			}
		}
	}
}
