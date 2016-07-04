package pacote.interceptor;

import java.io.Serializable;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;

import pacote.bean.FacesUtils;

@Interceptor
@Transaction
public class TransacaoInterceptador implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	@AroundInvoke
	public Object transacao(InvocationContext ctx) {
		manager.getTransaction().begin();
		System.out.println("manager.getTransaction().begin()");

		Object object = null;
		try {
			object = ctx.proceed();
			
			manager.getTransaction().commit();
			System.out.println("manager.getTransaction().commit()");
			FacesUtils.mostrarMensagemSucesso("Deu bom", null);
		} catch (Exception e) {
			manager.getTransaction().rollback();
			FacesUtils.mostrarMensagemErro("Deu pau", null);
			e.printStackTrace();
		}
		return object;
	}
}
