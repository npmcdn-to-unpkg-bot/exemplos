package pacote.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import pacote.modelo.Cliente;
import pacote.repository.ClienteRepository;

@Named
@ViewScoped
public class SelecaoClienteBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private ClienteRepository repository;

	private List<Cliente> clientes;
	private List<Cliente> clientesFiltrados;
	private String nome;

	public void init() {
		this.clientes = repository.listarClientes();
	}

	public void pesquisar() {
		this.clientesFiltrados = this.clientes.stream()
				.filter(c -> c.getNome().toLowerCase().contains(nome.toLowerCase())).collect(Collectors.toList());
	}

	public void selecionarCliente(Cliente cliente) {
		// Quando fechar o dialogo eu passo o objeto selecionado
		RequestContext.getCurrentInstance().closeDialog(cliente);
	}

	public void abrirDialog() {
		Map<String, Object> opcoes = new HashMap<>();
		// Abre o dialog e bloqueia o fundo
		opcoes.put("modal", true);
		// Não deixa redimencionar
		opcoes.put("resizable", false);
		// Altura da aerea conteudo
		opcoes.put("contentHeight", 475);
		RequestContext.getCurrentInstance().openDialog("SelecaoCliente.xhtml", opcoes, null);
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
