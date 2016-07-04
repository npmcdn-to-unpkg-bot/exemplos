package pacote.repository;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import pacote.cdi.EntityManagerProduces;
import pacote.modelo.Usuario;

public class UsuarioRepository implements Serializable {
	private static final long serialVersionUID = 1L;

	public void atualizar(Usuario usuario) {
		EntityManager manager = EntityManagerProduces.getEntityManager();
		manager.getTransaction().begin();

		manager.merge(usuario);

		manager.getTransaction().commit();
		EntityManagerProduces.closeEntityManager(manager);
	}

	public List<Usuario> listar() {
		EntityManager manager = EntityManagerProduces.getEntityManager();
		manager.getTransaction().begin();

		TypedQuery<Usuario> query = manager.createQuery("from Usuario", Usuario.class);
		List<Usuario> usuarios = query.getResultList();

		manager.getTransaction().commit();
		EntityManagerProduces.closeEntityManager(manager);

		return usuarios;
	}

	public Usuario logar(String cpf, String senha) {
		EntityManager manager = EntityManagerProduces.getEntityManager();
		manager.getTransaction().begin();

		TypedQuery<Usuario> query = manager.createQuery("from Usuario where cpf = :cpf and senha = :senha",
				Usuario.class);
		query.setParameter("cpf", cpf);
		query.setParameter("senha", senha);
		
		Usuario usuario;
		try {
			usuario = query.getSingleResult();			
		} catch (NoResultException e) {
			usuario = new Usuario();
		}

		manager.getTransaction().commit();
		EntityManagerProduces.closeEntityManager(manager);
		return usuario;
	}
}
