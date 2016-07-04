package tdc;

import javax.enterprise.event.Observes;

public class SistemaEstoque {

	public void emite(@Observes Item item) {
		System.out.println("Retirando o item");
	}
}
