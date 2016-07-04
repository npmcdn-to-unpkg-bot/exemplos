package pacote.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import pacote.modelo.Cliente;

public class ClienteRepository implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public List<Cliente> listarClientes() {
		TypedQuery<Cliente> query = manager.createQuery("from Cliente", Cliente.class);
		return query.getResultList();
	}
}
