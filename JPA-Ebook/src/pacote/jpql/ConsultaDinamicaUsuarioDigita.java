package pacote.jpql;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import pacote.modelo.Veiculo;
import pacote.util.JpaUtil;

public class ConsultaDinamicaUsuarioDigita {
    public static void main(String[] args) {
	EntityManager manager = JpaUtil.getEntityManager();
	EntityTransaction transaction = manager.getTransaction();
	transaction.begin();

	TypedQuery<Veiculo> query = manager.createQuery("from Veiculo where anoFabricacao >= :ano and valor <= :preco",
		Veiculo.class);
	query.setParameter("ano", 2013);
	query.setParameter("preco", new BigDecimal(10_000));
	query.setFirstResult(0);
	query.setMaxResults(3);
	List<Veiculo> veiculos = query.getResultList();

	for (Veiculo veiculo : veiculos) {
	    System.out.println(veiculo.getModelo() + " " + veiculo.getFabricante() + ": " + veiculo.getAnoFabricacao());
	}

	transaction.commit();
	manager.close();
	JpaUtil.close();
    }
}
