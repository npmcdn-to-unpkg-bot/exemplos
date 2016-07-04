package pacote.cdi;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@ApplicationScoped
public class EntityManagerProduces {

	private static EntityManagerFactory factory;

	static {
		factory = Persistence.createEntityManagerFactory("K19-JSF");
	}

	@Produces
	@RequestScoped
	public EntityManager getEntityManager() {
		System.out.println("Criando EntityManager");
		return factory.createEntityManager();
	}

	public void close(@Disposes EntityManager manager) {
		if (manager.isOpen()) {
			manager.close();
			System.out.println("Fechando EntityManager");
		}
	}
}
