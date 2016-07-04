package pacote.test;

import java.util.Calendar;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import pacote.modelo.Atendimento;
import pacote.modelo.Cliente;
import pacote.modelo.Funcionario;
import pacote.modelo.Status;

public class TestaChegada {
	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("Trabalho-Aldo");
		EntityManager manager = factory.createEntityManager();

		Cliente cliente = new Cliente();
		cliente.setNome("Lucas Daniel Tatu");
		cliente.setTelefone("9209-9988");

		Funcionario funcionario = manager.find(Funcionario.class, 1);

		Atendimento atendimento = new Atendimento();
		atendimento.setCliente(cliente);
		atendimento.setFuncionario(funcionario);
		atendimento.setHoraChegada(Calendar.getInstance());
		atendimento.setQuantidadePessoas(10);
		atendimento.setStatus(Status.ESPERANDO);

		manager.getTransaction().begin();
		//manager.persist(cliente);
		manager.persist(atendimento);
		manager.getTransaction().commit();

		manager.close();
		factory.close();
	}
}
