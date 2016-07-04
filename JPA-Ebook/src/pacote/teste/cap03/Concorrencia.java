package pacote.teste.cap03;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;

import pacote.modelo.Usuario;
import pacote.util.JpaUtil;

public class Concorrencia {
    public static void main(String[] args) {
	try {
	    EntityManager manager = JpaUtil.getEntityManager();
	    manager.getTransaction().begin();

	    EntityManager manager2 = JpaUtil.getEntityManager();
	    manager2.getTransaction().begin();

	    Usuario u1 = manager.find(Usuario.class, 2);
	    u1.setEmail("maria@gmail.com");

	    Usuario u2 = manager2.find(Usuario.class, 2);
	    u2.setEmail("jose@gmail.com");

	    manager.getTransaction().commit();
	    manager.close();

	    manager2.getTransaction().commit();
	    manager2.close();

	    JpaUtil.close();

	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
}
