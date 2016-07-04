package pacote.teste;

import javax.persistence.EntityManager;

import pacote.cdi.EntityManagerProduces;
import pacote.modelo.Usuario;

public class TestaUsuario {
	public static void main(String[] args) {
		EntityManager manager = EntityManagerProduces.getEntityManager();
		manager.getTransaction().begin();
		
		Usuario antonio = new Usuario();
		antonio.setCpf("11");
		antonio.setNome("Antonio Cesar");
		antonio.setSenha("123");
		
		Usuario elton = new Usuario();
		elton.setCpf("22");
		elton.setNome("Elton Naoki");
		elton.setSenha("123");
		
		Usuario andrey = new Usuario();
		andrey.setCpf("33");
		andrey.setNome("Antonio Cesar");
		andrey.setSenha("123");
		
		Usuario felipe = new Usuario();
		felipe.setCpf("44");
		felipe.setNome("Elton Naoki");
		felipe.setSenha("123");
		
		manager.persist(antonio);
		manager.persist(elton);
		manager.persist(andrey);
		manager.persist(felipe);
		
		manager.getTransaction().commit();
		EntityManagerProduces.closeEntityManager(manager);
	}
}
