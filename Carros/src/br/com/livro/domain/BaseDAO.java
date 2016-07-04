package br.com.livro.domain;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDAO implements Serializable {
	private static final long serialVersionUID = 1L;

	public BaseDAO() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public Connection getConnection() {
		String url = "jdbc:mysql://localhost/livro";
		try {
			System.out.println("Abrindo Connection");
			return DriverManager.getConnection(url, "livro", "livro123");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void closeConnection(Connection connection) {
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
