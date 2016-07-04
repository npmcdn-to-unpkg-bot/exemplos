package pacote.repository;

import java.io.Serializable;
import java.sql.Connection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import pacote.cdi.ConnectionFactoryProduces;
import pacote.cdi.EntityManagerProduces;
import pacote.modelo.Comentario;
import pacote.modelo.Post;
import pacote.modelo.PostComentario;
import pacote.modelo.Usuario;

public class PostRepository implements Serializable {
	private static final long serialVersionUID = 1L;

	public void inserir(Post post) {
		EntityManager manager = EntityManagerProduces.getEntityManager();
		manager.getTransaction().begin();

		Usuario usuario = manager.find(Usuario.class, post.getUsuario().getUsuarioId());
		post.setUsuario(usuario);
		manager.persist(post);

		manager.getTransaction().commit();
		EntityManagerProduces.closeEntityManager(manager);
	}

	public PostComentario porId(int id) {
		EntityManager manager = EntityManagerProduces.getEntityManager();

		TypedQuery<Post> query = manager.createQuery("SELECT distinct p FROM Post p WHERE p.postId = :postId",
				Post.class);
		query.setParameter("postId", id);
		Post post = query.getSingleResult();

		// TypedQuery<Comentario> queryComentatio = manager.createQuery("from
		// Comentario where post = :post", Comentario.class);
		// queryComentatio.setParameter("post", post);
		
		Connection connection = ConnectionFactoryProduces.getConnection();
		PostJDBC postJDBC = new PostJDBC(connection);
		List<Comentario> comentarios = postJDBC.buscarPorIdDoPost(id);
		ConnectionFactoryProduces.closeConnection(connection);

		EntityManagerProduces.closeEntityManager(manager);

		PostComentario postComentario = new PostComentario();
		postComentario.adicionarPost(post, comentarios);
		return postComentario;
	}

	public List<Post> listar() {
		EntityManager manager = EntityManagerProduces.getEntityManager();
		manager.getTransaction().begin();

		TypedQuery<Post> query = manager.createQuery("from Post", Post.class);
		List<Post> posts = query.getResultList();

		manager.getTransaction().commit();
		EntityManagerProduces.closeEntityManager(manager);
		return posts;
	}

	public void deletar(int id) {
		EntityManager manager = EntityManagerProduces.getEntityManager();
		manager.getTransaction().begin();
		
		Post post = manager.find(Post.class, id);
		manager.remove(post);

		manager.getTransaction().commit();
		EntityManagerProduces.closeEntityManager(manager);
	}
}
