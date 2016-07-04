package pacote.teste.cap03;

import javax.persistence.EntityManager;

import pacote.modelo2.Categoria;
import pacote.modelo2.Produto;
import pacote.util.JpaUtil;

public class InserindoLazyEager {

    public static void main(String[] args) {
	EntityManager manager = JpaUtil.getEntityManager();
	manager.getTransaction().begin();

	Categoria categoria = new Categoria();
	categoria.setNome("Bebidas Pesadas");

	Produto produto1 = new Produto();
	produto1.setNome("Cachaça");
	produto1.setCategoria(categoria);

	try {
	    manager.persist(produto1);
	} catch (Exception e) {
	    e.printStackTrace();
	}
	manager.getTransaction().commit();
	manager.close();
	JpaUtil.close();
    }
}
