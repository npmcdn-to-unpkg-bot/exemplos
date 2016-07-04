package pacote.teste.cap03;

import javax.persistence.EntityManager;

import pacote.modelo2.Categoria;
import pacote.modelo2.Produto;
import pacote.util.JpaUtil;

public class RemoverObjOrfans {

    public static void main(String[] args) {
	EntityManager manager = JpaUtil.getEntityManager();
	manager.getTransaction().begin();
	
	Categoria categoria = manager.find(Categoria.class, 5L);
	Produto produto = manager.find(Produto.class, 8L);
	
	categoria.getProdutos().remove(produto);
	
	manager.getTransaction().commit();
	manager.close();
	JpaUtil.close();
    }
}
