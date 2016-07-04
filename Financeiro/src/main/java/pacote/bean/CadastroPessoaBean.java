package pacote.bean;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import pacote.modelo.Pessoa;
import pacote.repository.PessoaRepository;
import pacote.service.PessoaService;

@Named
@ViewScoped
public class CadastroPessoaBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private PessoaService service;
	@Inject
	private PessoaRepository repository;
	private List<String> nomesParaSugestao;
	private Pessoa pessoa;
	private String id;

	public void init() {
		this.nomesParaSugestao = repository.nomesPessoas();

		if (id != null && !id.equals("")) {
			pessoa = repository.porId(new Long(id));
		} else {
			this.pessoa = new Pessoa();
		}
	}

	public void salvar() {
		try {
			service.salvar(pessoa);
			FacesUtils.mostrarMensagemSucesso("Pessoa " + pessoa.getNome() + " salvo com sucesso!", null);
			this.pessoa = new Pessoa();
			init();
		} catch (Exception e) {
			FacesUtils.mostrarMensagemErro("Ocorreu um erro ao tentar salvar " + pessoa.getNome(), null);
			e.printStackTrace();
		}
	}

	public List<String> nomesPessoas(String nome) {
		List<String> sugestoes = this.nomesParaSugestao.stream()
				.filter(s -> s.toUpperCase().contains(nome.toUpperCase())).collect(Collectors.toList());
		return sugestoes;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}
}
