package pacote.repository;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import pacote.modelo.Atendimento;
import pacote.modelo.Cliente;
import pacote.modelo.Funcionario;
import pacote.modelo.Status;

public class AtendimentoJDBC implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private Connection connection;

	public List<Atendimento> listarAtendimentos() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT a.id_atendimento, a.quantidade_pessoas, a.hora_chegada, ");
		sql.append("a.hora_atendimento, a.mesa, a.hora_saida, a.status, ");
		sql.append("c.id_cliente, c.nome AS nomeCliente, c.telefone, ");
		sql.append("f.id_funcionario, f.nome AS nomeFuncionario, f.funcao ");
		sql.append("FROM atendimento a ");
		sql.append("INNER JOIN cliente c ON a.id_cliente = c.id_cliente ");
		sql.append("INNER JOIN funcionario f ON f.id_funcionario = a.id_funcionario ORDER BY a.id_atendimento");

		List<Atendimento> atendimentos = new ArrayList<>();
		try {
			PreparedStatement comando = connection.prepareStatement(sql.toString());
			ResultSet result = comando.executeQuery();

			while (result.next()) {
				// Pega Atendimento
				Atendimento atendimento = new Atendimento();
				atendimento.setId(result.getInt("id_atendimento"));
				atendimento.setQuantidadePessoas(result.getInt("quantidade_pessoas"));

				Calendar horaChegada = Calendar.getInstance();
				horaChegada.setTime(result.getTimestamp("hora_chegada"));
				atendimento.setHoraChegada(horaChegada);

				if (result.getDate("hora_atendimento") != null) {
					Calendar horaAtendimento = Calendar.getInstance();
					horaAtendimento.setTime(result.getTimestamp("hora_atendimento"));
					atendimento.setHoraAtendimento(horaAtendimento);
				}

				if (result.getDate("hora_saida") != null) {
					Calendar horaSaida = Calendar.getInstance();
					horaSaida.setTime(result.getTimestamp("hora_saida"));
					atendimento.setHoraSaida(horaSaida);
				}

				atendimento.setStatus(Status.valueOf(result.getString("status")));

				if (result.getInt("mesa") != 0) {
					atendimento.setMesa(result.getInt("mesa"));
				}

				// Pega Cliente
				Cliente cliente = new Cliente();
				cliente.setId(result.getInt("id_cliente"));
				cliente.setNome(result.getString("nomeCliente"));
				cliente.setTelefone(result.getString("telefone"));
				atendimento.setCliente(cliente);

				// Pega Funcionario
				Funcionario funcionario = new Funcionario();
				funcionario.setId(result.getInt("id_funcionario"));
				funcionario.setNome(result.getString("nomeFuncionario"));
				funcionario.setFuncao(result.getString("funcao"));
				atendimento.setFuncionario(funcionario);

				atendimentos.add(atendimento);
			}

			result.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return atendimentos;
	}

	public List<Integer> listarMesas() {
		String sql = "SELECT mesa FROM atendimento WHERE status = 'NA_MESA'";

		List<Integer> mesas = new ArrayList<>();
		try {
			PreparedStatement comando = connection.prepareStatement(sql);
			ResultSet result = comando.executeQuery();

			while (result.next()) {
				if (result.getInt("mesa") != 0) {
					mesas.add(result.getInt("mesa"));
				}
			}
			result.close();
			return mesas;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void persistir(Atendimento atendimento) {
		String sql = "INSERT INTO atendimento (id_cliente, "
				+ "id_funcionario, quantidade_pessoas, hora_chegada, status) VALUES (?, ?, ?, ?, ?)";

		// Por enquanto
		atendimento.getFuncionario().setId(1);
		try {
			PreparedStatement comando = connection.prepareStatement(sql);

			// Inseri Cliente se ele ainda não existir, CastateType.Persiste na
			// mão
			if (atendimento.getCliente().getId() == 0) {
				PreparedStatement comandoCliente = connection
						.prepareStatement("INSERT INTO cliente (nome, telefone) VALUES (?, ?)");
				comandoCliente.setString(1, atendimento.getCliente().getNome());
				comandoCliente.setString(2, atendimento.getCliente().getTelefone());
				comandoCliente.execute();

				comandoCliente = connection.prepareStatement("SELECT max(id_cliente) AS id_cliente FROM cliente");
				ResultSet resultCliente = comandoCliente.executeQuery();
				while (resultCliente.next()) {
					atendimento.getCliente().setId(resultCliente.getInt("id_cliente"));
				}
			}

			comando.setInt(1, atendimento.getCliente().getId());
			comando.setInt(2, atendimento.getFuncionario().getId());
			comando.setInt(3, atendimento.getQuantidadePessoas());
			comando.setTimestamp(4, new Timestamp(atendimento.getHoraChegada().getTime().getTime()));
			comando.setString(5, atendimento.getStatus().toString());

			comando.execute();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public Atendimento atualizar(Atendimento atendimento) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE atendimento SET ");
		sql.append("(hora_atendimento, hora_saida, status, mesa) = ");
		sql.append("(?, ?, ?, ?) WHERE id_atendimento = ?");

		try {
			PreparedStatement comando = connection.prepareStatement(sql.toString());

			if (atendimento.getHoraAtendimento() != null) {
				comando.setTimestamp(1, new Timestamp(atendimento.getHoraAtendimento().getTime().getTime()));
			} else {
				comando.setNull(1, Types.TIMESTAMP);
			}

			if (atendimento.getHoraSaida() != null) {
				comando.setTimestamp(2, new Timestamp(atendimento.getHoraSaida().getTime().getTime()));
			} else {
				comando.setNull(2, Types.TIMESTAMP);
			}

			comando.setString(3, atendimento.getStatus().toString());

			if (atendimento.getMesa() != null) {
				comando.setInt(4, atendimento.getMesa());
			} else {
				comando.setNull(4, Types.INTEGER);
			}

			comando.setInt(5, atendimento.getId());

			comando.execute();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return null;
	}

	public Atendimento buscarPorId(int id) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT a.id_atendimento, a.quantidade_pessoas, a.hora_chegada, ");
		sql.append("a.hora_atendimento, a.mesa, a.hora_saida, a.status, ");
		sql.append("c.id_cliente, c.nome AS nomeCliente, c.telefone, ");
		sql.append("f.id_funcionario, f.nome AS nomeFuncionario, f.funcao ");
		sql.append("FROM atendimento a ");
		sql.append("INNER JOIN cliente c ON a.id_cliente = c.id_cliente ");
		sql.append("INNER JOIN funcionario f ON f.id_funcionario = a.id_funcionario ");
		sql.append("WHERE a.id_atendimento = ?");

		try {
			PreparedStatement comando = connection.prepareStatement(sql.toString());
			comando.setInt(1, id);
			ResultSet result = comando.executeQuery();

			Atendimento atendimento = null;
			while (result.next()) {
				// Pega Atendimento
				atendimento = new Atendimento();
				atendimento.setId(result.getInt("id_atendimento"));
				atendimento.setQuantidadePessoas(result.getInt("quantidade_pessoas"));

				Calendar horaChegada = Calendar.getInstance();
				horaChegada.setTime(result.getTimestamp("hora_chegada"));
				atendimento.setHoraChegada(horaChegada);

				if (result.getDate("hora_atendimento") != null) {
					Calendar horaAtendimento = Calendar.getInstance();
					horaAtendimento.setTime(result.getTimestamp("hora_atendimento"));
					atendimento.setHoraAtendimento(horaAtendimento);
				}

				if (result.getDate("hora_saida") != null) {
					Calendar horaSaida = Calendar.getInstance();
					horaSaida.setTime(result.getTimestamp("hora_saida"));
					atendimento.setHoraSaida(horaSaida);
				}

				atendimento.setStatus(Status.valueOf(result.getString("status")));

				if (result.getInt("mesa") != 0) {
					atendimento.setMesa(result.getInt("mesa"));
				}

				// Pega Cliente
				Cliente cliente = new Cliente();
				cliente.setId(result.getInt("id_cliente"));
				cliente.setNome(result.getString("nomeCliente"));
				cliente.setTelefone(result.getString("telefone"));
				atendimento.setCliente(cliente);

				// Pega Funcionario
				Funcionario funcionario = new Funcionario();
				funcionario.setId(result.getInt("id_funcionario"));
				funcionario.setNome(result.getString("nomeFuncionario"));
				funcionario.setFuncao(result.getString("funcao"));
				atendimento.setFuncionario(funcionario);
			}
			result.close();

			return atendimento;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
