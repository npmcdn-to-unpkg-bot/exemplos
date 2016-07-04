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
import br.com.caelum.modelo.Contato;

public class ContatoDao {
	private Connection connection;

	public ContatoDao() {
		this.connection = new ConnectionFactory().getConnection();
	}

	public void adiciona(Contato contato) {
		String sql = "INSERT INTO contatos (nome, email, endereco, dataNascimento) VALUES (?, ?, ?, ?)";

		try {
			PreparedStatement statement = (PreparedStatement) this.connection.prepareStatement(sql);

			statement.setString(1, contato.getNome());
			statement.setString(2, contato.getEmail());
			statement.setString(3, contato.getEndereco());
			statement.setDate(4, new Date(contato.getDataNascimento().getTimeInMillis()));

			statement.execute();
			statement.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {

		}
	}

	public void altera(Contato contato) {
		String sql = "UPDATE contatos SET nome = ?, email = ?, endereco = ?, dataNascimento = ? WHERE id = ?";

		try {
			PreparedStatement statement = (PreparedStatement) this.connection.prepareStatement(sql);

			statement.setString(1, contato.getNome());
			statement.setString(2, contato.getEmail());
			statement.setString(3, contato.getEndereco());
			statement.setDate(4, new Date(contato.getDataNascimento().getTimeInMillis()));

			statement.setLong(5, contato.getId());

			statement.execute();
			statement.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {

		}
	}

	public List<Contato> getLista() {
		try {
			String sql = "SELECT * FROM contatos";
			PreparedStatement statement = (PreparedStatement) this.connection.prepareStatement(sql);
			ResultSet resultadoBusca = statement.executeQuery();

			List<Contato> contatos = pega(resultadoBusca);

			resultadoBusca.close();
			statement.close();

			return contatos;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private List<Contato> pega(ResultSet resultadoBusca) {
		List<Contato> contatos = new ArrayList<>();
		try {
			while (resultadoBusca.next()) {

				Contato contato = new Contato();
				contato.setId(resultadoBusca.getLong("id"));
				contato.setNome(resultadoBusca.getString("nome"));
				contato.setEmail(resultadoBusca.getString("email"));
				contato.setEndereco(resultadoBusca.getString("endereco"));

				Calendar calendar = Calendar.getInstance();
				calendar.setTime(resultadoBusca.getDate("dataNascimento"));
				contato.setDataNascimento(calendar);

				contatos.add(contato);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return contatos;
	}

	public Contato buscarPorId(Long id) {
		try {
			String sql = "SELECT * FROM contatos WHERE id = " + id;
			PreparedStatement statement = (PreparedStatement) this.connection.prepareStatement(sql);
			ResultSet result = statement.executeQuery();

			List<Contato> contatos = pega(result);

			result.close();
			statement.close();

			return contatos.size() <= 0 ? null : contatos.get(0);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void deletar(Contato contato) {
		try {
			String sql = "DELETE FROM contatos WHERE id = ?";
			PreparedStatement statement = (PreparedStatement) this.connection.prepareStatement(sql);

			statement.setLong(1, contato.getId());

			statement.execute();

			statement.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
