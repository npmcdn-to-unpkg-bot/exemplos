package pacote.jpql;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import pacote.modelo.PrecoVeiculo;
import pacote.util.JpaUtil;

public class ResultadosComplexosComNew {
    public static void main(String[] args) {
	EntityManager manager = JpaUtil.getEntityManager();
	
	TypedQuery<PrecoVeiculo> query = manager.createQuery("select new pacote.modelo.PrecoVeiculo(modelo, valor) from Veiculo", PrecoVeiculo.class);
	List<PrecoVeiculo> resultado = query.getResultList();
	
	for (PrecoVeiculo precoVeiculo : resultado) {
	    System.out.println(precoVeiculo.getModelo() + " - " + precoVeiculo.getValor());
	}
	
	manager.close();
	JpaUtil.close();
    }
}
