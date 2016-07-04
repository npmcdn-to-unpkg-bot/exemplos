package pacote.teste.cap03;

import javax.persistence.EntityManager;

import pacote.modelo.Usuario;
import pacote.util.JpaUtil;

public class InserirUsuarios {
    public static void main(String[] args) {
	EntityManager manager = JpaUtil.getEntityManager();
	manager.getTransaction().begin();

	Usuario u1 = new Usuario("a@gmail.com", true);
	Usuario u2 = new Usuario("a@algaworks.com", true);
	Usuario u3 = new Usuario("a@gmail.com", true);

	manager.persist(u1);
	manager.persist(u2);
	manager.persist(u3);

	manager.getTransaction().commit();
	manager.close();
	JpaUtil.close();
    }
}
