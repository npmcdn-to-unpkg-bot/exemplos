package pacote.repository;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import pacote.cdi.EntityManagerProduces;
import pacote.modelo.Comentario;
import pacote.modelo.Post;
import pacote.modelo.Usuario;

public class ComentarioRepository implements Serializable{
	private static final long serialVersionUID = 1L;

	public void inserir(Comentario comentario) {
		EntityManager manager = EntityManagerProduces.getEntityManager();
		manager.getTransaction().begin();

		Usuario usuario = manager.find(Usuario.class, comentario.getUsuario().getUsuarioId());
		Post post = manager.find(Post.class, comentario.getPost().getPostId());

		comentario.setPost(post);
		comentario.setUsuario(usuario);

		manager.persist(comentario);

		manager.getTransaction().commit();
		EntityManagerProduces.closeEntityManager(manager);
	}
	
	public List<Comentario> listar(){
		EntityManager manager = EntityManagerProduces.getEntityManager();
		manager.getTransaction().begin();
		
		TypedQuery<Comentario> query = manager.createQuery("from Comentario", Comentario.class);
		List<Comentario> comentarios = query.getResultList();
		
		manager.getTransaction().commit();
		EntityManagerProduces.closeEntityManager(manager);
		
		return comentarios;
	}
}
