package pacote.teste.cap2;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import pacote.modelo.Veiculo;
import pacote.util.JpaUtil;

public class UpdateVeiculo {

	public static void main(String[] args) {
		EntityManager manager = JpaUtil.getEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		
		Veiculo veiculo = manager.find(Veiculo.class, 2L);
		
		veiculo.setFabricante("Chevrolet");
		veiculo.setModelo("Camaro");
		veiculo.setAnoFabricacao(2014);
		veiculo.setAnoModelo(2015);
		veiculo.setValor(new BigDecimal(271300));
		
		transaction.commit();
		manager.close();
		JpaUtil.close();
	}
}
