package pacote.produces;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerProduces implements Serializable {
	private static final long serialVersionUID = 1L;

	private static EntityManagerFactory factory;

	static {
		factory = Persistence.createEntityManagerFactory("FinanceiroPU");
	}

	@Produces
	@RequestScoped
	public EntityManager getManager() {
		System.out.println("Criando EntityManager");
		return factory.createEntityManager();
	}

	public void closeEntityManager(@Disposes EntityManager manager) {
		if (manager.isOpen()) {
			manager.close();
			System.out.println("Fechando EntityManager");
		}
	}
}
