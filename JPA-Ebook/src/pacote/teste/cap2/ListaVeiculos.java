package pacote.teste.cap2;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import pacote.modelo.Veiculo;
import pacote.util.JpaUtil;

public class ListaVeiculos {

	public static void main(String[] args) {
		
		EntityManager manager = JpaUtil.getEntityManager();
		
		Query query = manager.createQuery("from Veiculo");
		List<Veiculo> veiculos = query.getResultList();

		for (Veiculo veiculo : veiculos) {
			System.out.println(veiculo);
		}
		
		manager.close();
		JpaUtil.close();
	}
}
