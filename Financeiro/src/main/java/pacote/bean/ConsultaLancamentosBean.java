package pacote.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import pacote.modelo.Lancamento;
import pacote.repository.LancamentoRepository;
import pacote.service.CadastroLancamentos;

@Named
@ViewScoped
public class ConsultaLancamentosBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private LancamentoRepository repository;
	@Inject
	private CadastroLancamentos cadastroLancamentos;
	private Lancamento lancamentoSelecionado;
	private List<Lancamento> lancamentos;

	public void consultar() {
		this.lancamentos = repository.listarLancamento();
	}

	public void excluir() {
		try {
			cadastroLancamentos.excluir(lancamentoSelecionado);
			this.consultar();
		} catch (Exception e) {
			FacesUtils.mostrarMensagemErro("Falha ao excluir.", null);
		}
	}

	public List<Lancamento> getLancamentos() {
		return lancamentos;
	}

	public void setLancamentoSelecionado(Lancamento lancamentoSelecionado) {
		this.lancamentoSelecionado = lancamentoSelecionado;
	}
}
