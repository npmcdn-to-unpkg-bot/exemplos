package pacote.jpql;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import pacote.modelo.Veiculo;
import pacote.util.JpaUtil;

public class ProblemaN1 {
    public static void main(String[] args) {
	EntityManager manager = JpaUtil.getEntityManager();
	
	TypedQuery<Veiculo> query = manager.createQuery("from Veiculo v inner join fetch v.propietario", Veiculo.class);
	List<Veiculo> veiculos = query.getResultList();
	
	for (Veiculo veiculo : veiculos) {
	    System.out.println(veiculo.getModelo() + " - " + veiculo.getPropietario().getNome());
	}
	
	manager.close();
	JpaUtil.close();
    }
}
