package pacote.test;

import java.text.ParseException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import pacote.modelo.Atendimento;

public class TestaLista {
	public static void main(String[] args) throws ParseException {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("Trabalho-Aldo");
		EntityManager manager = factory.createEntityManager();

		TypedQuery<Atendimento> query = manager.createQuery("from Atendimento", Atendimento.class);
		List<Atendimento> atendimentos = query.getResultList();

		for (Atendimento atendimento : atendimentos) {
			System.out.println(atendimento);
		}
		
		manager.close();
		factory.close();
	}
}
