package pacote.service;

import java.io.Serializable;
import java.util.Date;

import javax.inject.Inject;

import pacote.bean.FacesUtils;
import pacote.exception.NegocioException;
import pacote.interceptor.Transaction;
import pacote.modelo.Lancamento;
import pacote.repository.LancamentoRepository;

public class CadastroLancamentos implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private LancamentoRepository repository;

	@Transaction
	public void salvar(Lancamento lancamento) throws NegocioException {
		if (lancamento.getDataPagamento() != null && lancamento.getDataPagamento().after(new Date())) {
			FacesUtils.mostrarMensagemErro("Data de pagamento não pode ser futura.", null);
			throw new NegocioException("Data de pagamento não pode ser futura.");
		}

		this.repository.guardar(lancamento);
	}

	@Transaction
	public void excluir(Lancamento lancamento) throws NegocioException {
		lancamento = repository.porId(lancamento.getId());

		if (lancamento.getDataPagamento() == null) {
			FacesUtils.mostrarMensagemErro("Não é possivel exluir um lançamento não pago.", null);
			throw new NegocioException("Não é possivel exluir um lançamento não pago.");
		}
		this.repository.remover(lancamento);
		FacesUtils.mostrarMensagemSucesso("Lançamento excluido com sucesso!.", null);
	}
}
