package pacote.teste.cap03;

import javax.persistence.EntityManager;

import pacote.modelo2.Categoria;
import pacote.util.JpaUtil;

public class TesteExclusaoEmCascata {
    public static void main(String[] args) {
	
	EntityManager manager = JpaUtil.getEntityManager();
	manager.getTransaction().begin();
	
	Categoria c = manager.find(Categoria.class, 3L);
	
	manager.remove(c);
	
	manager.getTransaction().commit();
	manager.close();
	JpaUtil.close();
    }
}
