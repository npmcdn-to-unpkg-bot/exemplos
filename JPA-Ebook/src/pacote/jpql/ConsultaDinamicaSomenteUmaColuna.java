package pacote.jpql;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import pacote.util.JpaUtil;

public class ConsultaDinamicaSomenteUmaColuna {
    public static void main(String[] args) {
	EntityManager manager = JpaUtil.getEntityManager();

	TypedQuery<String> query = manager.createQuery("select modelo from Veiculo", String.class);

	List<String> modelos = query.getResultList();

	for (String string : modelos) {
	    System.out.println(string);
	}
	
	manager.close();
	JpaUtil.close();
    }
}
