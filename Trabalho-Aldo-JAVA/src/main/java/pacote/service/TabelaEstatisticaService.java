package pacote.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import pacote.cdi.Transacao;
import pacote.modelo.TabelaEstatistica;
import pacote.repository.TabelaEstatisticaRepository;

public class TabelaEstatisticaService implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private TabelaEstatisticaRepository repository;

	@Transacao
	public void inserir(List<TabelaEstatistica> tabelaEstatisticas) {
		repository.inserir(tabelaEstatisticas);
	}

	@Transacao
	public int deletarTodos() {
		return repository.deletarTodos();
	}

	public List<TabelaEstatistica> listar() {
		return repository.listar();
	}
}
