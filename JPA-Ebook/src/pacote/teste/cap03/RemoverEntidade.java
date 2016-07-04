package pacote.teste.cap03;

import javax.persistence.EntityManager;

import pacote.modelo2.Categoria;
import pacote.util.JpaUtil;

public class RemoverEntidade {

    public static void main(String[] args) {
	EntityManager manager = JpaUtil.getEntityManager();
	manager.getTransaction().begin();

	Categoria categoria = manager.find(Categoria.class, 1L);

	try {
	    manager.remove(categoria);
	} catch (Exception e) {
	    e.printStackTrace();
	}

	manager.getTransaction().commit();
	manager.close();
	JpaUtil.close();
    }
}
