package br.com.caelum.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


import br.com.caelum.jdbc.ConnectionFactory;
import br.com.caelum.modelo.Funcionario;

public class FuncionarioDao {
	private Connection connection;

	public FuncionarioDao() {
		this.connection = new ConnectionFactory().getConnection();
	}

	public void adiciona(Funcionario funcionario) {
		String sql = "INSERT INTO funcionarios (nome, usuario, senha) VALUES (?, ?, ?)";

		try {
			PreparedStatement statement = (PreparedStatement) this.connection.prepareStatement(sql);

			statement.setString(1, funcionario.getNome());
			statement.setString(2, funcionario.getUsuario());
			statement.setString(3, funcionario.getSenha());

			statement.execute();
			statement.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {

		}
	}

	public void altera(Funcionario funcionario) {
		String sql = "UPDATE funcionarios SET nome = ?, usuario = ?, senha = ? WHERE id = ?";

		try {
			PreparedStatement statement = (PreparedStatement) this.connection.prepareStatement(sql);

			statement.setString(1, funcionario.getNome());
			statement.setString(2, funcionario.getUsuario());
			statement.setString(3, funcionario.getSenha());

			statement.setLong(4, funcionario.getId());

			statement.execute();
			statement.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {

		}
	}

	public List<Funcionario> getLista() {
		try {
			String sql = "SELECT * FROM funcionarios";
			PreparedStatement statement = (PreparedStatement) this.connection.prepareStatement(sql);
			ResultSet resultadoBusca = statement.executeQuery();

			List<Funcionario> funcionarios = pega(resultadoBusca);

			resultadoBusca.close();
			statement.close();

			return funcionarios;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private List<Funcionario> pega(ResultSet resultadoBusca) {
		List<Funcionario> funcionarios = new ArrayList<>();
		try {
			while (resultadoBusca.next()) {

				Funcionario funcionario = new Funcionario();
				funcionario.setId(resultadoBusca.getLong("id"));
				funcionario.setNome(resultadoBusca.getString("nome"));
				funcionario.setUsuario(resultadoBusca.getString("usuario"));
				funcionario.setSenha(resultadoBusca.getString("senha"));

				funcionarios.add(funcionario);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return funcionarios;
	}

	public Funcionario buscarPorId(Long id) {
		try {
			String sql = "SELECT * FROM funcionarios WHERE id = " + id;
			PreparedStatement statement = (PreparedStatement) this.connection.prepareStatement(sql);
			ResultSet result = statement.executeQuery();

			List<Funcionario> funcionarios = pega(result);

			result.close();
			statement.close();

			return funcionarios.size() <= 0 ? null : funcionarios.get(0);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void deletar(Funcionario funcionario) {
		try {
			String sql = "DELETE FROM funcionarios WHERE id = ?";
			PreparedStatement statement = (PreparedStatement) this.connection.prepareStatement(sql);

			statement.setLong(1, funcionario.getId());

			statement.execute();

			statement.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
