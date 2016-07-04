package pacote.teste.cap03;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import pacote.util.JpaUtil;

public class MudarEmailEmLote {

    public static void main(String[] args) {
	EntityManager manager = JpaUtil.getEntityManager();
	manager.getTransaction().begin();

	Query query = manager.createQuery("delete Usuario where email like :email");
	query.setParameter("email", "%@gmail.com");

	int linhaAfetadas = query.executeUpdate();

	System.out.println(linhaAfetadas + " Registros atualizados");

	manager.getTransaction().commit();
	manager.close();
	JpaUtil.close();
    }
}
