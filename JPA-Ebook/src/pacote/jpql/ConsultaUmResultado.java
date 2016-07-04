package pacote.jpql;

import java.util.Calendar;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import pacote.modelo.Veiculo;
import pacote.util.JpaUtil;

public class ConsultaUmResultado {
    public static void main(String[] args) {
	EntityManager manager = JpaUtil.getEntityManager();

	TypedQuery<Veiculo> query = manager
		.createQuery("from Veiculo where modelo = :modelo and fabricante = :fa", Veiculo.class);
	query.setParameter("modelo", "CHA");
	query.setParameter("fa", "vau");

	Veiculo veiculo = query.getSingleResult();

	Calendar c = Calendar.getInstance();
	c.set(2011, Calendar.DECEMBER, 25, 10, 30);

	System.out.println("Existem " + veiculo + " em nosso sistema.");

	manager.close();
	JpaUtil.close();
    }
}
