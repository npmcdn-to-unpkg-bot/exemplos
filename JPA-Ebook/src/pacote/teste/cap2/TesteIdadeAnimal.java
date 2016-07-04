package pacote.teste.cap2;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.EntityManager;

import pacote.modelo.Animal;
import pacote.util.JpaUtil;

public class TesteIdadeAnimal {
    public static void main(String[] args) {
	EntityManager manager = JpaUtil.getEntityManager();
	manager.getTransaction().begin();

	Calendar dataNascimento = Calendar.getInstance();
	dataNascimento.set(2011, 2, 1);

	Animal animal = new Animal();
	animal.setNome("Mimosa");
	animal.setDataNascimento(dataNascimento.getTime());
	// animal.setDataUltimaAtualização(new Date());

	System.out.println("Idade antes de persistir: " + animal.getIdade());
	manager.persist(animal);
	// manager.flush();
	System.out.println("Idade depois de persistir: " + animal.getIdade());
	System.out.println(new SimpleDateFormat().format(animal.getDataUltimaAtualização()));

	manager.getTransaction().commit();
	manager.close();
	JpaUtil.close();
    }
}