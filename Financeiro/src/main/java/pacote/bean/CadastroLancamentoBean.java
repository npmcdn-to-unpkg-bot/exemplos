package pacote.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import pacote.exception.NegocioException;
import pacote.modelo.Lancamento;
import pacote.modelo.Pessoa;
import pacote.modelo.TipoLancamento;
import pacote.repository.LancamentoRepository;
import pacote.repository.PessoaRepository;
import pacote.service.CadastroLancamentos;

@Named
@ViewScoped
public class CadastroLancamentoBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private CadastroLancamentos cadastroLancamentos;
	@Inject
	private PessoaRepository pessoaRepository;
	@Inject
	private LancamentoRepository lancamentoRepository;

	private Lancamento lancamento;
	private List<Pessoa> pessoas;
	private List<String> descricaoLancamentos;

	public void prepararCadastro() {
		this.pessoas = pessoaRepository.todos();
		this.descricaoLancamentos = lancamentoRepository.descricoes();

		if (lancamento == null) {
			lancamento = new Lancamento();
		}
	}

	public void salvar() {
		try {
			cadastroLancamentos.salvar(lancamento);
			String sera = lancamento.getId() == null ? "salvo" : "editado";
			FacesUtils.mostrarMensagemSucesso("Lançamento " + sera + " com sucesso!",
					"Lançamento " + lancamento.getDescricao() + " registrado no sistema!");
			this.lancamento = new Lancamento();
			this.descricaoLancamentos = lancamentoRepository.descricoes();
		} catch (NegocioException e) {
			FacesUtils.mostrarMensagemErro("Erro ao tentar salvar", e.getMessage());
		}
		// return "formularioCadastro.xhtml?faces-redirect=true";
	}

	public void dataVencimentoAlterada(AjaxBehaviorEvent event) {
		if (lancamento.getDataPagamento() == null) {
			lancamento.setDataPagamento(lancamento.getDataVencimento());
		}
	}

	public void dataPagamentoHoje() {
		lancamento.setDataPagamento(new Date());
	}

	/**
	 * Fornece sugestões para o usuario enquanto ele digita, busca as sugestões
	 * direto no banco
	 * 
	 * @param descricao
	 * @return
	 */
	public List<String> pesquisarDescricoesNoBanco(String descricao) {
		return lancamentoRepository.descricoesQueContem(descricao);
	}

	/**
	 * Fornece sugestões para o usuario enquanto ele digita, busca as sugestões
	 * de uma lista de descrições que é carregada quando a aplicação é invocado
	 * 
	 * @param descricao
	 * @return
	 */
	public List<String> pesquisarDescricoesNaLista(String descricao) {
		descricaoLancamentos.forEach(System.out::println);
		List<String> encontrados = descricaoLancamentos.stream()
				.filter(s -> s.toUpperCase().contains(descricao.toUpperCase())).collect(Collectors.toList());
		System.out.println("pesquisarDescricoesNaLista");
		return encontrados;
	}

	public Lancamento getLancamento() {
		return lancamento;
	}

	public void setLancamento(Lancamento lancamento) {
		this.lancamento = lancamento;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public TipoLancamento[] getTiposLancamentos() {
		return TipoLancamento.values();
	}
}
