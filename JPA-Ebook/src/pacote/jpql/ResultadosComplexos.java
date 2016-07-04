package pacote.jpql;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import pacote.util.JpaUtil;

public class ResultadosComplexos {
    public static void main(String[] args) {
	EntityManager manager = JpaUtil.getEntityManager();
	
	TypedQuery<Object[]> query = manager.createQuery("select modelo, valor, anoFabricacao from Veiculo", Object[].class);
	List<Object[]> resultado = query.getResultList();
	
	for (Object[] valores : resultado) {
	    String modelo = (String) valores[0];
	    BigDecimal valor = (BigDecimal) valores[1];
	    Integer anoFabricacao = (Integer) valores[2];
	    System.out.println(modelo + " - " + valor + " = " + anoFabricacao);
	}
	
	manager.close();
	JpaUtil.close();
    }
}
