package pacote.jpql;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import pacote.modelo.Veiculo;
import pacote.util.JpaUtil;

public class NamedQueryPorModelo {
    public static void main(String[] args) {
	EntityManager manager = JpaUtil.getEntityManager();
	
	TypedQuery<Veiculo> query = manager.createNamedQuery("Veiculo.porModelo", Veiculo.class);
	query.setParameter("modelo", "Uno");
	List<Veiculo> veiculos = query.getResultList();
	
	for (Veiculo veiculo : veiculos) {
	    System.out.println(veiculo);
	}
	
	manager.close();
	JpaUtil.close();
    }
}
