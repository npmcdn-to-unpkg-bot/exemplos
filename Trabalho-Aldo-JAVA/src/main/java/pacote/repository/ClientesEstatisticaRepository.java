package pacote.repository;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import pacote.modelo.Cliente;
import pacote.modelo.ClientesEstatistica;

public class ClientesEstatisticaRepository implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public void persistir(ClientesEstatistica clientesEstatistica) {
		// Objeto transiente
		if (clientesEstatistica.getCliente() != null && clientesEstatistica.getCliente().getId() > 0) {
			clientesEstatistica.setCliente(manager.find(Cliente.class, clientesEstatistica.getCliente().getId()));
		}

		manager.persist(clientesEstatistica);
	}
}
