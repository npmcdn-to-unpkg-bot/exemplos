package pacote.teste.cap2;

import javax.persistence.EntityManager;

import pacote.modelo.Veiculo;
import pacote.util.JpaUtil;

public class RemoveVeiculo {

	public static void main(String[] args) {
		
		EntityManager manager = JpaUtil.getEntityManager();
		
		Veiculo veiculo = manager.find(Veiculo.class, 3L);
		
		manager.getTransaction().begin();
		manager.remove(veiculo);
		manager.getTransaction().commit();
		
		manager.close();
		JpaUtil.close();
		
	}
}
