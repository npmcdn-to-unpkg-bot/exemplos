package pacote.teste.cap03;

import javax.persistence.EntityManager;

import pacote.modelo2.Categoria;
import pacote.modelo2.Produto;
import pacote.util.JpaUtil;

public class InserirCategariaEProdutos {

    public static void main(String[] args) {
	EntityManager manager = JpaUtil.getEntityManager();
	manager.getTransaction().begin();

	Categoria categoria = new Categoria();
	categoria.setNome("Frutas");

	Produto p1 = new Produto();
	p1.setNome("Maça");
	p1.setCategoria(categoria);

	Produto p2 = new Produto();
	p2.setNome("Pera");
	p2.setCategoria(categoria);
	
	categoria.getProdutos().add(p1);
	categoria.getProdutos().add(p2);

	try {
	    manager.persist(categoria);
	} catch (Exception e) {
	    e.printStackTrace();
	}

	manager.getTransaction().commit();
	manager.close();
	JpaUtil.close();
    }
}
