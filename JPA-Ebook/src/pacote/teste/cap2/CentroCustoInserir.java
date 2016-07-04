package pacote.teste.cap2;

import javax.persistence.EntityManager;

import pacote.modelo.CentroCusto;
import pacote.util.JpaUtil;

public class CentroCustoInserir {

    public static void main(String[] args) {
	EntityManager manager = JpaUtil.getEntityManager();
	manager.getTransaction().begin();

	CentroCusto cc1 = new CentroCusto();
	cc1.setNome("Tecnologia");

	CentroCusto cc2 = new CentroCusto();
	cc2.setNome("Comercial");

	manager.persist(cc1);
	manager.persist(cc2);

	manager.getTransaction().commit();
	manager.close();
	JpaUtil.close();
    }
}
