package pacote.jpql;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import pacote.modelo.Propietario;
import pacote.util.JpaUtil;

public class LeftJoinJPQL {
    public static void main(String[] args) {
	EntityManager manager = JpaUtil.getEntityManager();

	TypedQuery<Propietario> query = manager.createQuery("select distinct p from Propietario p inner join p.veiculos v",
		Propietario.class);
	List<Propietario> propietarios = query.getResultList();
	
	manager.close();
	JpaUtil.close();

	for (Propietario propietario : propietarios) {
	    System.out.println(propietario);
	}
    }
}
