package pacote.repository;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerProduces implements Serializable {
	private static final long serialVersionUID = 1L;

	private static EntityManagerFactory factory;

	static {
		factory = Persistence.createEntityManagerFactory("Lista");
	}

	public static EntityManager getEntityManager() {
		System.out.println("Criando EntityManager");
		return factory.createEntityManager();
	}

	public static void closeEntityManager(EntityManager manager) {
		if (manager.isOpen()) {
			manager.close();
			System.out.println("Fechando EntityManager");
		}
	}
}
