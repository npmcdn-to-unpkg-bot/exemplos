package pacote.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import pacote.modelo.Atendimento;
import pacote.service.AtendimentoService;

@Named
@ViewScoped
public class AtendimentosBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private AtendimentoService service;
	private List<Atendimento> atendimentos;
	private Atendimento atendimentoSelecionado;

	public void init() {
		this.atendimentos = service.listarAtendimentos();
	}

	public void confirmaAtendimento(SelectEvent event) {
		Integer mesa = (Integer) event.getObject();
		atendimentoSelecionado.setMesa(mesa);

		service.confirmaAtendimento(atendimentoSelecionado);
	}

	public void cancelarAtendimento() {
		service.cancelaEspera(atendimentoSelecionado);
	}

	public void finalizarAtendimento() {
		service.terminaAtendimento(atendimentoSelecionado);
	}

	public boolean isAtendimentoSelecionadoComStatusNaMesa() {
		if (atendimentoSelecionado != null) {
			return atendimentoSelecionado.getStatus().getDescricao().equals("NA MESA");			
		}
		return false;
	}

	public List<Atendimento> getAtendimentos() {
		return atendimentos;
	}

	public Atendimento getAtendimentoSelecionado() {
		return atendimentoSelecionado;
	}

	public void setAtendimentoSelecionado(Atendimento atendimentoSelecionado) {
		this.atendimentoSelecionado = atendimentoSelecionado;
	}
}
