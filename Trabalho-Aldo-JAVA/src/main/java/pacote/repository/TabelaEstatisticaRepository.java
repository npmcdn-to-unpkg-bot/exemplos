package pacote.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import pacote.modelo.TabelaEstatistica;

public class TabelaEstatisticaRepository implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public void inserir(List<TabelaEstatistica> itens) {
		for (TabelaEstatistica tabelaEstatistica : itens) {
			manager.persist(tabelaEstatistica);
		}
	}

	public List<TabelaEstatistica> listar() {
		TypedQuery<TabelaEstatistica> query = manager.createQuery("from TabelaEstatistica", TabelaEstatistica.class);
		return query.getResultList();
	}
	
	public int deletarTodos() {
		Query query = manager.createQuery("delete from TabelaEstatistica");
		return query.executeUpdate();
	}
}
