package pacote.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;

import pacote.modelo.Contato;

public class ContatoRepository {

	public List<Contato> listar() {
		EntityManager manager = EntityManagerProduces.getEntityManager();

		TypedQuery<Contato> query = manager.createQuery("from Contato", Contato.class);
		List<Contato> contatos = query.getResultList();

		EntityManagerProduces.closeEntityManager(manager);
		return contatos;
	}

	public void inserir(Contato contato) {
		EntityManager manager = EntityManagerProduces.getEntityManager();

		manager.getTransaction().begin();
		manager.persist(contato);
		manager.getTransaction().commit();

		EntityManagerProduces.closeEntityManager(manager);
	}

	public void deletar(List<Contato> contatos) {
		EntityManager manager = EntityManagerProduces.getEntityManager();

		manager.getTransaction().begin();
		for (Contato contato : contatos) {
			contato = manager.find(Contato.class, contato.getId());
			manager.remove(contato);
		}
		manager.getTransaction().commit();

		EntityManagerProduces.closeEntityManager(manager);
	}

	public void deletar(int id) {
		EntityManager manager = EntityManagerProduces.getEntityManager();

		Contato contato = manager.find(Contato.class, id);
		JOptionPane.showMessageDialog(null, contato.getNome() + " deletado com sucesso!");
		manager.getTransaction().begin();
		manager.remove(contato);
		manager.getTransaction().commit();

		EntityManagerProduces.closeEntityManager(manager);
	}

	public Contato buscarporId(int id) {
		EntityManager manager = EntityManagerProduces.getEntityManager();

		Contato contato = manager.find(Contato.class, id);

		EntityManagerProduces.closeEntityManager(manager);
		return contato;
	}
}
