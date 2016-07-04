package pacote.service;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import pacote.cdi.Transacao;
import pacote.modelo.Atendimento;
import pacote.modelo.Status;
import pacote.repository.AtendimentoRepository;

public class AtendimentoService implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private AtendimentoRepository repository;

	// @Inject
	// private AtendimentoJDBC repository;

	public List<Atendimento> listarAtendimentos() {
		return repository.listarAtendimentos();
	}

	public List<Atendimento> listarAtendimentosPorStatus(Status status) {
		return repository.listarAtendimentosPorStatus(status);
	}
	
	@Transacao
	public int deletarTodos() {
		return repository.deletarTodos();
	}

	public List<Integer> listarMesas() {
		return repository.listarMesas();
	}

	public Atendimento buscarPorId(int id) {
		return repository.buscarPorId(id);
	}

	public boolean registrarChegadaDoCliente(Atendimento atendimento) {
		atendimento.setStatus(Status.ESPERANDO);
		// atendimento.setHoraChegada(Calendar.getInstance());
		repository.persistir(atendimento);
		return false;
	}

	public boolean confirmaAtendimento(Atendimento atendimento) {
		atendimento.setStatus(Status.NA_MESA);
		atendimento.setHoraAtendimento(Calendar.getInstance());
		repository.atualizar(atendimento);
		return false;
	}

	public boolean cancelaEspera(Atendimento atendimento) {
		atendimento.setStatus(Status.CANCELADO);
		atendimento.setHoraSaida(Calendar.getInstance());
		repository.atualizar(atendimento);
		return false;
	}

	public boolean terminaAtendimento(Atendimento atendimento) {
		atendimento.setStatus(Status.ATENDIDO);
		atendimento.setHoraSaida(Calendar.getInstance());
		repository.atualizar(atendimento);
		return false;
	}
}
