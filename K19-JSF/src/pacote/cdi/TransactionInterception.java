package pacote.cdi;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

@Trasaction
@Interceptor
public class TransactionInterception {

	@Inject
	private EntityManager manager;

	@AroundInvoke
	public Object abrirTransacao(InvocationContext ctx) throws Exception {
		EntityTransaction transacao = manager.getTransaction();

		// Object object = null;
		try {
			transacao.begin();
			System.out.println("Iniciando Transação");

			return ctx.proceed();

		} catch (Exception e) {
			if (transacao != null) {
				transacao.rollback();
				System.out.println("Rollback Transação");
			}
			throw new RuntimeException(e);
		} finally {
			if (transacao != null && transacao.isActive()) {
				transacao.commit();
				System.out.println("Comitando Transação");
			}
		}
		// return object;
	}
}
