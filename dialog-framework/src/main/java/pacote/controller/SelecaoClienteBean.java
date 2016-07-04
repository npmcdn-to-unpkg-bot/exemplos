package pacote.controller;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import pacote.model.Cliente;
import pacote.repository.Clientes;

@Named
@ViewScoped
public class SelecaoClienteBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Clientes clientes;

	private String nome;

	private List<Cliente> clientesFiltrados;

	public void pesquisar() {
		clientesFiltrados = clientes.porNomeSemelhante(nome);
	}

	// Todas as telas podem usar o dialogo
	public void abrirDialogo() {
		Map<String, Object> opcoes = new HashMap<>();
		// Abre o dialog e bloqueia o fundo
		opcoes.put("modal", true);
		// Não deixa redimencionar
		opcoes.put("resizable", false);
		// Altura da aerea conteudo
		opcoes.put("contentHeight", 470);

		RequestContext.getCurrentInstance().openDialog("SelecaoCliente", opcoes, null);
	}

	public void selecionar(Cliente cliente) {
		// Quando fechar o dialogo eu passo o objeto selecionado
		RequestContext.getCurrentInstance().closeDialog(cliente);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Cliente> getClientesFiltrados() {
		return clientesFiltrados;
	}
}