package pacote.jpql;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import pacote.modelo.TotalPorAno;
import pacote.util.JpaUtil;

public class FuncaoAgregacao {
    public static void main(String[] args) {
	EntityManager manager = JpaUtil.getEntityManager();

	TypedQuery<TotalPorAno> query = manager
		.createQuery("select new pacote.modelo.TotalPorAno(v.anoFabricacao, avg(v.valor), count(v)) "
			+ "from Veiculo v group by v.anoFabricacao", TotalPorAno.class);
	List<TotalPorAno> resultado = query.getResultList();
	
	for (TotalPorAno totalPorAno : resultado) {
	    System.out.println(totalPorAno);
	}
	
	manager.close();
	JpaUtil.close();
    }
}
