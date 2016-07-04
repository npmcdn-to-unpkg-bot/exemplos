package pacote.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import pacote.cdi.Transacao;
import pacote.modelo.Atendimento;
import pacote.modelo.Cliente;
import pacote.modelo.Funcionario;
import pacote.modelo.Status;

public class AtendimentoRepository implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public List<Atendimento> listarAtendimentos() {
		TypedQuery<Atendimento> query = manager
				.createQuery("SELECT a FROM Atendimento a INNER JOIN a.cliente c ORDER BY a.id", Atendimento.class);
		return query.getResultList();
	}

	public List<Atendimento> listarAtendimentosPorStatus(Status status) {
		TypedQuery<Atendimento> query = manager.createQuery(
				"SELECT a FROM Atendimento a INNER JOIN a.cliente c WHERE a.status = :status ORDER BY a.id",
				Atendimento.class);
		query.setParameter("status", status);
		return query.getResultList();
	}

	public List<Integer> listarMesas() {
		TypedQuery<Integer> query = manager.createQuery("SELECT mesa FROM Atendimento WHERE status = 'NA_MESA'",
				Integer.class);
		return query.getResultList();
	}

	@Transacao
	public void persistir(Atendimento atendimento) {
		// Objeto transiente
		if (atendimento.getCliente() != null && atendimento.getCliente().getId() > 0) {
			atendimento.setCliente(manager.find(Cliente.class, atendimento.getCliente().getId()));
		}
		// Objeto transiente
		if (atendimento.getFuncionario() != null && atendimento.getFuncionario().getId() > 0) {
			atendimento.setFuncionario(manager.find(Funcionario.class, atendimento.getFuncionario().getId()));
		}
		atendimento.setFuncionario(manager.find(Funcionario.class, 1));
		manager.persist(atendimento);
	}
	
	public int deletarTodos() {
		Query query = manager.createQuery("delete from Atendimento");
		return query.executeUpdate();
	}

	@Transacao
	public Atendimento atualizar(Atendimento atendimento) {
		return manager.merge(atendimento);
	}

	public Atendimento buscarPorId(int id) {
		return manager.find(Atendimento.class, id);
	}
}
