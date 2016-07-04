package tdc;

import javax.enterprise.event.Observes;

public class EnviadorDeEmail {

	public void envia(@Observes Item item) {
		System.out.println("Enviando email");
	}

}
