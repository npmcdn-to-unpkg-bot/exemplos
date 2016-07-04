package pacote.bean;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import pacote.modelo.Atendimento;
import pacote.modelo.Cliente;
import pacote.modelo.Usuario;
import pacote.service.AtendimentoService;
import pacote.util.FacesUtils;

@Named
@ViewScoped
public class AtendimentoBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private Atendimento atendimento;
	@Inject
	private AtendimentoService service;
	@Inject
	private Usuario usuario;

	public AtendimentoBean() {
		System.out.println("AtendimentoBean");
	}

	public void inicialiazar() {
		this.atendimento = new Atendimento();
	}

	public void clienteSelecionado(SelectEvent event) {
		Cliente cliente = (Cliente) event.getObject();
		atendimento.setCliente(cliente);
	}

	public void adicionarNaFila() {
		try {
			atendimento.getFuncionario().setId(usuario.getId());
			service.registrarChegadaDoCliente(atendimento);
			FacesUtils.mostrarMensagemSucesso("Adicionado " + atendimento.getCliente().getNome() + " á fila de espera",
					null);
			inicialiazar();
		} catch (Exception e) {
			FacesUtils.mostrarMensagemSucesso(
					"Erro adicionar " + atendimento.getCliente().getNome() + " á fila de espera", null);
		}
	}

	public Atendimento getAtendimento() {
		return atendimento;
	}

	public void setAtendimento(Atendimento atendimento) {
		this.atendimento = atendimento;
	}
}
