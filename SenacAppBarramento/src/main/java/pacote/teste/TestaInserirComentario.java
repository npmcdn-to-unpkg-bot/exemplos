package pacote.teste;

import javax.persistence.EntityManager;

import pacote.cdi.EntityManagerProduces;
import pacote.modelo.Comentario;
import pacote.modelo.Post;
import pacote.modelo.Usuario;

public class TestaInserirComentario {
	public static void main(String[] args) {
		EntityManager manager = EntityManagerProduces.getEntityManager();
		manager.getTransaction().begin();
		
		Post postVoltaNathalia = manager.find(Post.class, 1);
		
		Usuario antonio = manager.find(Usuario.class, 1);
		Comentario comentario01 = new Comentario();
		comentario01.setUsuario(antonio);
		comentario01.setPost(postVoltaNathalia);
		comentario01.setTexto("#voltanathalia");
		
		Usuario elton = manager.find(Usuario.class, 2);
		Comentario comentario02 = new Comentario();
		comentario02.setUsuario(elton);
		comentario02.setPost(postVoltaNathalia);
		comentario02.setTexto("#voltanathalia");
		
		manager.persist(comentario01);
		manager.persist(comentario02);
		
		manager.getTransaction().commit();
		EntityManagerProduces.closeEntityManager(manager);
	}
}
