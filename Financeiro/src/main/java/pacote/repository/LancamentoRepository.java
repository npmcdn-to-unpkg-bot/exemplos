package pacote.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import pacote.modelo.Lancamento;

public class LancamentoRepository implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public List<Lancamento> listarLancamento() {
		TypedQuery<Lancamento> query = manager.createQuery("from Lancamento", Lancamento.class);
		return query.getResultList();
	}

	public List<String> descricoesQueContem(String descricao) {
		String jpql = "SELECT DISTINCT descricao FROM Lancamento WHERE UPPER(descricao) LIKE UPPER(:descricao)";
		TypedQuery<String> query = manager.createQuery(jpql, String.class);
		query.setParameter("descricao", "%" + descricao + "%");
		return query.getResultList();
	}

	public List<String> descricoes() {
		String jpql = "SELECT DISTINCT descricao FROM Lancamento";
		TypedQuery<String> query = manager.createQuery(jpql, String.class);
		return query.getResultList();
	}

	public void adicionar(Lancamento lancamento) {
		manager.persist(lancamento);
	}

	public Lancamento guardar(Lancamento lancamento) {
		return manager.merge(lancamento);
	}

	public Lancamento porId(Long id) {
		return manager.find(Lancamento.class, id);
	}

	public void remover(Lancamento lancamento) {
		manager.remove(lancamento);
	}
}
