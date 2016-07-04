package pacote.cdi;

import java.io.Serializable;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

@Interceptor
@Transacao
public class InterceptadorTransacao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	@AroundInvoke
	public Object iniciaTransacao(InvocationContext ctx) throws Exception {
		EntityTransaction transacao = manager.getTransaction();

		try {
			transacao.begin();
			System.out.println("Inicia Transacao");
			return ctx.proceed();
		} catch (Exception e) {
			if (transacao != null) {
				transacao.rollback();
				System.out.println("Rollback Transacao");
			}
			throw new RuntimeException(e);
		} finally {
			if (transacao != null && transacao.isActive()) {
				transacao.commit();
				System.out.println("Confirma Transacao");
			}
		}
	}
}
