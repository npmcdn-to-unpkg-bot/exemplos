package pacote.service;

import java.io.Serializable;

import javax.inject.Inject;

import org.omnifaces.util.Faces;

import pacote.interceptor.Transaction;
import pacote.modelo.Pessoa;
import pacote.repository.PessoaRepository;

public class PessoaService implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private PessoaRepository repository;

	@Transaction
	public void salvar(Pessoa pessoa) {
		repository.guardar(pessoa);
	}

	@Transaction
	public void remover(Pessoa pessoa) {
		pessoa = repository.porId(pessoa.getId());
		repository.remover(pessoa);
	}
}
