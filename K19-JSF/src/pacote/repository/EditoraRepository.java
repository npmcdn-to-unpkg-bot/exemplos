package pacote.repository;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import pacote.cdi.RepositoryQualifier;
import pacote.cdi.RepositoryType;
import pacote.modelo.Editora;

@RepositoryQualifier(tipo = RepositoryType.EDITORA)
public class EditoraRepository implements Serializable, Repository<Editora> {
	private static final long serialVersionUID = 1L;

	private EntityManager manager;

	public EditoraRepository(EntityManager manager) {
		this.manager = manager;
	}

	@Override
	public void persistir(Editora editora) {
		manager.persist(editora);
	}

	@Override
	public void atualizar(Editora editora) {
		manager.merge(editora);
	}

	@Override
	public void remover(Editora editora) {
		manager.remove(editora);
	}

	@Override
	public Editora busca(Long id) {
		return manager.find(Editora.class, id);
	}

	@Override
	public List<Editora> buscaTodas() {
		TypedQuery<Editora> query = manager.createQuery("from Editora", Editora.class);
		return query.getResultList();
	}
}
