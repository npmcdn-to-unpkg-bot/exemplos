package pacote.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import pacote.modelo.Operadora;

public class OperadoraRepository {

	public List<Operadora> listar() {
		EntityManager manager = EntityManagerProduces.getEntityManager();

		TypedQuery<Operadora> query = manager.createQuery("from Operadora", Operadora.class);
		List<Operadora> contatos = query.getResultList();

		EntityManagerProduces.closeEntityManager(manager);

		return contatos;
	}
}
