package pacote.criteria;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import pacote.modelo.Veiculo;
import pacote.util.JpaUtil;

public class PrimeiraCriteria {
    public static void main(String[] args) {
	EntityManager manager = JpaUtil.getEntityManager();
	
	CriteriaBuilder builder = manager.getCriteriaBuilder();
	CriteriaQuery<Veiculo> criteriaQuery = builder.createQuery(Veiculo.class);
	
	Root<Veiculo> veiculo = criteriaQuery.from(Veiculo.class);
	criteriaQuery.select(veiculo);
	
	TypedQuery<Veiculo> query = manager.createQuery(criteriaQuery);
	List<Veiculo> veiculos = query.getResultList();
	
	for (Veiculo veiculo2 : veiculos) {
	    System.out.println(veiculo2);
	}
	
	manager.close();
	JpaUtil.close();
    }
}
