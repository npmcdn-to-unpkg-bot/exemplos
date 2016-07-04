package pacote.jpql;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import pacote.util.JpaUtil;

public class TypedSomaValor {
    public static void main(String[] args) {
	EntityManager manager = JpaUtil.getEntityManager();
	
	TypedQuery<BigDecimal> query = manager.createQuery("select sum(valor) from Veiculo", BigDecimal.class);
	BigDecimal resul = query.getSingleResult();
	
	System.out.println("Soma de tudo " + resul);
	
	manager.close();
	JpaUtil.close();
    }
}
