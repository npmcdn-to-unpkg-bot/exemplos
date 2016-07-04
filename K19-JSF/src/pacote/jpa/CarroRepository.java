package pacote.jpa;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import pacote.modelo.Carro;

public class CarroRepository implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public void adiciona(Carro carro) {
		manager.persist(carro);
	}

	public List<Carro> buscaTodos() {
		TypedQuery<Carro> query = manager.createQuery("from Carro", Carro.class);
		return query.getResultList();
	}
}
