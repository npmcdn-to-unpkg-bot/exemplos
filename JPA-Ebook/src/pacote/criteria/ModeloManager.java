package pacote.criteria;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import pacote.modelo.Propietario;
import pacote.modelo.Veiculo;
import pacote.util.JpaUtil;

public class ModeloManager {
    public static void main(String[] args) {
	EntityManager manager = JpaUtil.getEntityManager();
	
	TypedQuery<Propietario> query = manager.createQuery("select distinct p from Propietario p inner join p.veiculos v", Propietario.class);
	TypedQuery<Long> query2 = manager.createQuery("select count(p) from Propietario p ", Long.class);
	
	List<Propietario> veiculos = query.getResultList();
	Long qtd = query2.getSingleResult();
	
	for (Propietario propietario : veiculos) {
	    System.out.println(propietario);
	}
	System.out.println("QTD: " + qtd);
	
	manager.close();
	JpaUtil.close();
    }
}
