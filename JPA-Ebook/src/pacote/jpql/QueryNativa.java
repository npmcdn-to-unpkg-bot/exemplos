package pacote.jpql;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import pacote.modelo.Veiculo;
import pacote.util.JpaUtil;

public class QueryNativa {
    public static void main(String[] args) {
	EntityManager manager = JpaUtil.getEntityManager();
	
	Query query = manager.createNativeQuery("select * from veiculo", Veiculo.class);
	
	@SuppressWarnings("unchecked")
	List<Veiculo> veiculos = query.getResultList();
	
	for (Veiculo veiculo : veiculos) {
	    System.out.println(veiculo);
	}
	
	manager.close();
	JpaUtil.close();
    }
}
