package pacote.teste.cap03;

import javax.persistence.EntityManager;

import pacote.modelo2.Produto;
import pacote.util.JpaUtil;

public class BuscandoProduto {

    public static void main(String[] args) {
	EntityManager manager = JpaUtil.getEntityManager();

	Produto produto = manager.find(Produto.class, 1L);

	System.out.println(produto.getNome());
	//System.out.println(produto.getCategoria().getNome());
	manager.close();
	JpaUtil.close();
	
	System.out.println(produto.getCategoria().getNome());
    }
}
