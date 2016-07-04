package pacote.jpql;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import pacote.modelo.Veiculo;
import pacote.util.JpaUtil;

public class ConsultaDinamicaAnoFabricacao {
    public static void main(String[] args) {
	EntityManager manager = JpaUtil.getEntityManager();
	EntityTransaction transaction = manager.getTransaction();
	transaction.begin();

	Query query = manager.createQuery("select v from Veiculo v where anoFabricacao = 2010");
	@SuppressWarnings("unchecked")
	List<Veiculo> veiculos = query.getResultList();
	
	Calendar.getInstance();
	
	for (Veiculo veiculo : veiculos) {
	    System.out.println(veiculo.getModelo() + " " + veiculo.getFabricante() + ": " + veiculo.getAnoFabricacao());
	}

	transaction.commit();
	manager.close();
	JpaUtil.close();
    }
}
