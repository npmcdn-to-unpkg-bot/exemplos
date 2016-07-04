package pacote.cdi;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;

public class ConnectionFactoryProduces implements Serializable {
	private static final long serialVersionUID = 1L;

	@Produces
	@RequestScoped
	public Connection getConnection() {
		String url = "jdbc:postgresql://localhost/trabalho_aldo";
		try {
			Class.forName("org.postgresql.Driver");
			System.out.println("Abrindo Connection");
			return DriverManager.getConnection(url, "postgres", "12345");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void closeConnection(@Disposes Connection connection) {		
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
