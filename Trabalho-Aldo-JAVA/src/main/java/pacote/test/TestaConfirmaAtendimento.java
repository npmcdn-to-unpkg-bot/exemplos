package pacote.test;

import java.util.Calendar;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import pacote.modelo.Atendimento;
import pacote.modelo.Status;

public class TestaConfirmaAtendimento {
	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("Trabalho-Aldo");
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();

		Atendimento atendimento = manager.find(Atendimento.class, 5);
		System.out.println(atendimento);

		atendimento.setHoraAtendimento(Calendar.getInstance());
		atendimento.setStatus(Status.NA_MESA);
		atendimento.setMesa(7);

		manager.getTransaction().commit();
		manager.close();
		factory.close();
	}
}
