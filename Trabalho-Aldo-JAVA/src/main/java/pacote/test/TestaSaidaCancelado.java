package pacote.test;

import java.util.Calendar;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import pacote.modelo.Atendimento;
import pacote.modelo.Status;

public class TestaSaidaCancelado {
	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("Trabalho-Aldo");
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();

		Atendimento atendimento = manager.find(Atendimento.class, 6);
		System.out.println(atendimento);

		atendimento.setStatus(Status.CANCELADO);
		atendimento.setHoraSaida(Calendar.getInstance());

		manager.getTransaction().commit();
		manager.close();
		factory.close();
		System.out.println(atendimento);
	}
}
