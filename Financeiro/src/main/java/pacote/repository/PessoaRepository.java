package pacote.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import pacote.modelo.Pessoa;

public class PessoaRepository implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public List<Pessoa> todos() {
		TypedQuery<Pessoa> query = manager.createQuery("from Pessoa", Pessoa.class);
		return query.getResultList();
	}

	public List<String> nomesPessoas() {
		TypedQuery<String> query = manager.createQuery("select distinct nome from Pessoa", String.class);
		return query.getResultList();
	}

	public void guardar(Pessoa pessoa) {
		manager.merge(pessoa);
	}

	public void remover(Pessoa pessoa) {
		manager.remove(pessoa);
	}

	public Pessoa porId(Long id) {
		return manager.find(Pessoa.class, id);
	}
}
