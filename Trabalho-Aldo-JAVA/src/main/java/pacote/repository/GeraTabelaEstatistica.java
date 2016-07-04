package pacote.repository;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import pacote.modelo.Cliente;
import pacote.modelo.Status;
import pacote.modelo.TabelaEstatistica;
import pacote.modelo.TabelaEstatisticaMedia;

public class GeraTabelaEstatistica implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private Connection connection;

	public TabelaEstatisticaMedia medias() {
		String sqlAtendido = "SELECT avg(hora_atendimento - hora_chegada) "
				+ "AS media_tempo_espera, avg(hora_saida - hora_chegada) "
				+ "AS media_tempo_permanencia_atendido FROM atendimento where status = 'ATENDIDO'";
		String sqlCancelado = "SELECT avg(hora_saida - hora_chegada) "
				+ "AS media_tempo_permanencia_cancelado FROM atendimento where status = 'CANCELADO'";

		TabelaEstatisticaMedia media = new TabelaEstatisticaMedia();
		try {
			PreparedStatement comandoTabela = connection.prepareStatement(sqlAtendido);
			ResultSet resultadoTabela = comandoTabela.executeQuery();

			while (resultadoTabela.next()) {
				media.setMediaTempoEspera(resultadoTabela.getTimestamp("media_tempo_espera"));
				media.setMediaTempoAtendimento(resultadoTabela.getTimestamp("media_tempo_permanencia_atendido"));
			}

			comandoTabela = connection.prepareStatement(sqlCancelado);
			resultadoTabela = comandoTabela.executeQuery();

			while (resultadoTabela.next()) {
				media.setMediaTempoCancelamento(resultadoTabela.getTimestamp("media_tempo_permanencia_cancelado"));
			}

			comandoTabela.close();
			resultadoTabela.close();

			return media;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public List<TabelaEstatistica> geraListaAtendidos(Status status) {
		if (status.equals(Status.ATENDIDO)) {
			return geraListaAtendidos();
		} else {
			return geraListaCancelados();
		}
	}

	private List<TabelaEstatistica> geraListaAtendidos() {
		try {
			String sql = "select id_atendimento, id_cliente, (hora_atendimento - hora_chegada) as tempo_espera, "
					+ "(hora_saida - hora_chegada) as tempo_permanecia, status from atendimento where status = 'ATENDIDO'";

			PreparedStatement comandoTabela = connection.prepareStatement(sql);
			ResultSet resultadoTabela = comandoTabela.executeQuery();

			List<TabelaEstatistica> estatisticas = new ArrayList<>();
			while (resultadoTabela.next()) {
				TabelaEstatistica t = new TabelaEstatistica();

				int idCliente = resultadoTabela.getInt("id_cliente");
				PreparedStatement comandoCliente = connection
						.prepareStatement("SELECT * FROM cliente WHERE id_cliente = ?");
				comandoCliente.setInt(1, idCliente);
				ResultSet resutadoCliente = comandoCliente.executeQuery();
				while (resutadoCliente.next()) {
					Cliente cliente = new Cliente();
					cliente.setId(resutadoCliente.getInt("id_cliente"));
					cliente.setNome(resutadoCliente.getString("nome"));
					cliente.setTelefone(resutadoCliente.getString("telefone"));

					t.setCliente(cliente);
				}
				comandoCliente.close();
				resutadoCliente.close();

				t.setNumeroAtendimento(resultadoTabela.getInt("id_atendimento"));
				t.setTempoEspera(resultadoTabela.getTimestamp("tempo_espera"));
				t.setTempoPermanencia(resultadoTabela.getTimestamp("tempo_permanecia"));
				t.setStatus(Status.valueOf(resultadoTabela.getString("status")));

				estatisticas.add(t);
			}

			comandoTabela.close();
			resultadoTabela.close();
			return estatisticas;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private List<TabelaEstatistica> geraListaCancelados() {
		try {
			String sql = "select id_atendimento, id_cliente, (hora_saida - hora_chegada) as tempo_permanecia, status from atendimento where status = 'CANCELADO'";

			PreparedStatement comandoTabela = connection.prepareStatement(sql);
			ResultSet resultadoTabela = comandoTabela.executeQuery();

			List<TabelaEstatistica> estatisticas = new ArrayList<>();
			while (resultadoTabela.next()) {
				TabelaEstatistica t = new TabelaEstatistica();

				int idCliente = resultadoTabela.getInt("id_cliente");
				PreparedStatement comandoCliente = connection
						.prepareStatement("SELECT * FROM cliente WHERE id_cliente = ?");
				comandoCliente.setInt(1, idCliente);
				ResultSet resutadoCliente = comandoCliente.executeQuery();
				while (resutadoCliente.next()) {
					Cliente cliente = new Cliente();
					cliente.setId(resutadoCliente.getInt("id_cliente"));
					cliente.setNome(resutadoCliente.getString("nome"));
					cliente.setTelefone(resutadoCliente.getString("telefone"));

					t.setCliente(cliente);
				}
				comandoCliente.close();
				resutadoCliente.close();

				t.setNumeroAtendimento(resultadoTabela.getInt("id_atendimento"));
				t.setTempoPermanencia(resultadoTabela.getTimestamp("tempo_permanecia"));
				t.setStatus(Status.valueOf(resultadoTabela.getString("status")));

				estatisticas.add(t);
			}

			comandoTabela.close();
			resultadoTabela.close();

			return estatisticas;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
