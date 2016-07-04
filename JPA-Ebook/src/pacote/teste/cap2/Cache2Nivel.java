package pacote.teste.cap2;

import javax.persistence.EntityManager;

import pacote.modelo.CentroCusto;
import pacote.util.JpaUtil;

public class Cache2Nivel {
    public static void main(String[] args) {
	EntityManager manager1 = JpaUtil.getEntityManager();
	CentroCusto c1 = manager1.find(CentroCusto.class, 1L);
	manager1.close();
	System.out.println(c1.getNome());

	System.out.println("------");

	EntityManager manager2 = JpaUtil.getEntityManager();
	CentroCusto c2 = manager2.find(CentroCusto.class, 1L);
	manager2.close();
	System.out.println(c2.getNome());
	JpaUtil.close();
    }
}
