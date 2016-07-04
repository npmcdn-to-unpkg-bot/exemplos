package pacote.jpql;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import pacote.util.JpaUtil;

public class InnerJoinJPQL {
    public static void main(String[] args) {
	EntityManager manager = JpaUtil.getEntityManager();

	TypedQuery<Object[]> query = manager.createQuery(
		"select p.nome, count(v) from Propietario p left join p.veiculos v group by p.nome", Object[].class);
	List<Object[]> resul = query.getResultList();
	
	for (Object[] objects : resul) {
	    System.out.println(objects[0] + " - " + objects[1]);
	}

	manager.close();
	JpaUtil.close();
    }
}
