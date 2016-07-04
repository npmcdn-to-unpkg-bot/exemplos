package pacote.jpql;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import pacote.modelo.Veiculo;
import pacote.util.JpaUtil;

public class NamedQuery {
    public static void main(String[] args) {
	vai();
    }

    private static void vai() {
	EntityManager manager = JpaUtil.getEntityManager();
	
	TypedQuery<Veiculo> query = manager.createNamedQuery("Veiculo.comPropietarioPorValor", Veiculo.class);
	query.setParameter("valor", new BigDecimal(2000));
	List<Veiculo> veiculos = query.getResultList();
	
	for (Veiculo veiculo : veiculos) {
	    System.out.println(veiculo);
	}
	
	manager.close();
	JpaUtil.close();
    }
}
