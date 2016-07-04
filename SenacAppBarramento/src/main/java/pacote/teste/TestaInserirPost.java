package pacote.teste;

import java.util.Calendar;

import javax.persistence.EntityManager;

import pacote.cdi.EntityManagerProduces;
import pacote.modelo.Post;
import pacote.modelo.Usuario;

public class TestaInserirPost {
	public static void main(String[] args) {
		EntityManager manager = EntityManagerProduces.getEntityManager();
		manager.getTransaction().begin();
		
		Usuario antonio = manager.find(Usuario.class, 1);
		
		Post post = new Post();
		post.setUsuario(antonio);
		post.setTitulo("#teste Imagem");
		post.setTexto("Sera que foi!");
		post.setCidade("Campo Grande");
		post.setDataDaPostagem(Calendar.getInstance());
		
		manager.persist(post);
		
		manager.getTransaction().commit();
		EntityManagerProduces.closeEntityManager(manager);
	}
}
