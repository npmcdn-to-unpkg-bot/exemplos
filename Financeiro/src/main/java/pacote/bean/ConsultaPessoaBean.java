package pacote.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import pacote.modelo.Pessoa;
import pacote.repository.PessoaRepository;
import pacote.service.PessoaService;

@Named
@ViewScoped
public class ConsultaPessoaBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private PessoaRepository repository;
	@Inject
	private PessoaService service;
	private List<Pessoa> pessoas;
	private Pessoa pessoaSelecionada;

	@PostConstruct
	public void init() {
		this.pessoas = repository.todos();
	}

	public void remover() {
		try {
			service.remover(pessoaSelecionada);
			init();
			FacesUtils.mostrarMensagemSucesso("Pessoa " + pessoaSelecionada.getNome() + " removida com sucesso!", null);
		} catch (Exception e) {
			FacesUtils.mostrarMensagemErro("Ocorreu um erro ao tentar remover " + pessoaSelecionada.getNome(), null);
		}
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public Pessoa getPessoaSelecionada() {
		return pessoaSelecionada;
	}

	public void setPessoaSelecionada(Pessoa pessoaSelecionada) {
		this.pessoaSelecionada = pessoaSelecionada;
	}
}
