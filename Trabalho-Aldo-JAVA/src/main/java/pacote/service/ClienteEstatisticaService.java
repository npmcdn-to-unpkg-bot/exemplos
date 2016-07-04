package pacote.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import pacote.cdi.Transacao;
import pacote.modelo.ClientesEstatistica;
import pacote.repository.ClientesEstatisticaRepository;

public class ClienteEstatisticaService implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private ClientesEstatisticaRepository repository;

	@Transacao
	public void persistir(List<ClientesEstatistica> clientes) {
		for (ClientesEstatistica clientesEstatistica : clientes) {
			repository.persistir(clientesEstatistica);
		}
	}
}
