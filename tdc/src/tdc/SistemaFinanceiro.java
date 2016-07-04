package tdc;

import javax.enterprise.event.Observes;

public class SistemaFinanceiro {

	public void emite(@Observes Item itemComprado) {
		System.out.println("Emitindo Nota");
	}
}
