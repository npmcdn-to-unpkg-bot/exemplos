package pacote.repository;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import pacote.modelo.Funcionario;

public class FuncionarioRepository implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Funcionario buscar(String login, String senha) throws NoResultException{
		TypedQuery<Funcionario> query = manager
				.createQuery("from Funcionario where login = :login and senha = :senha", Funcionario.class);
		query.setParameter("login", login);
		query.setParameter("senha", senha);
		
		try {			
			return query.getSingleResult();
		} catch (NoResultException e) {
			throw e;
		}
	}
}
