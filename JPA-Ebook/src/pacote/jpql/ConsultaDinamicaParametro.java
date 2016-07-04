package pacote.jpql;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import pacote.modelo.Veiculo;
import pacote.util.JpaUtil;

public class ConsultaDinamicaParametro {
    public static void main(String[] args) {
	EntityManager manager = JpaUtil.getEntityManager();

	Scanner scanner = new Scanner(System.in);

	System.out.println("Registros por página");
	int registrosPorPagina = scanner.nextInt();
	int numeroDaPagina = 0;

	TypedQuery<Veiculo> query = manager.createQuery("from Veiculo", Veiculo.class);

	do {
	    System.out.println("Numero da página");
	    numeroDaPagina = scanner.nextInt();

	    if (numeroDaPagina != 0) {
		int primeiroRegistro = (numeroDaPagina - 1) * registrosPorPagina;

		query.setFirstResult(primeiroRegistro);
		query.setMaxResults(registrosPorPagina);
		List<Veiculo> veiculos = query.getResultList();

		for (Veiculo veiculo : veiculos) {
		    System.out.println(
			    veiculo.getModelo() + " " + veiculo.getFabricante() + ": " + veiculo.getAnoFabricacao());
		}
	    }
	} while (numeroDaPagina != 0);
	
	scanner.close();
	manager.close();
	JpaUtil.close();
    }
}
