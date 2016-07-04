package pacote.teste.cap03;

import javax.persistence.EntityManager;

import pacote.modelo2.Categoria;
import pacote.modelo2.Produto;
import pacote.util.JpaUtil;

public class BuscandoCategoria {

    public static void main(String[] args) {

	EntityManager manager = JpaUtil.getEntityManager();

	Categoria categoria = manager.find(Categoria.class, 1L);

	System.out.println(categoria.getNome());

	manager.close();
	JpaUtil.close();
	try {
	    for (Produto p : categoria.getProdutos()) {
		System.out.println(p.getNome());
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
}
