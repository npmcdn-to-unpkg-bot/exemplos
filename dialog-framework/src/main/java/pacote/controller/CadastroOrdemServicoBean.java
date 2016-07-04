package pacote.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import pacote.model.Cliente;
import pacote.model.OrdemServico;
import pacote.service.CadastroOrdemServicoService;
import pacote.util.FacesMessages;

@Named
@ViewScoped
public class CadastroOrdemServicoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CadastroOrdemServicoService cadastroOrdemServico;

	@Inject
	private FacesMessages messages;

	private OrdemServico ordemServico;

	public void inicializar() {
		ordemServico = new OrdemServico();
	}

	public void clienteSelecionado(SelectEvent event) {
		Cliente cliente = (Cliente) event.getObject();
		ordemServico.setCliente(cliente);
	}

	public void salvar() {
		cadastroOrdemServico.salvar(ordemServico);

		messages.info("Ordem de Serviço salva com sucesso para " + ordemServico.getCliente().getNome() + "!");

		ordemServico = new OrdemServico();
	}

	public OrdemServico getOrdemServico() {
		return ordemServico;
	}

	public String getNomeCliente() {
		return ordemServico.getCliente() == null ? null : ordemServico.getCliente().getNome();
	}

	public void setNomeCliente(String nome) {
	}

}