package pacote.test;

import java.util.Calendar;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import pacote.modelo.Atendimento;
import pacote.modelo.Status;

public class TestaSaidaAtendido {
	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("Trabalho-Aldo");
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();

		Atendimento atendimento = manager.find(Atendimento.class, 5);
		System.out.println(atendimento);

		atendimento.setStatus(Status.ATENDIDO);
		atendimento.setHoraSaida(Calendar.getInstance());

		manager.getTransaction().commit();
		manager.close();
		factory.close();
		System.out.println(atendimento);
	}
}
