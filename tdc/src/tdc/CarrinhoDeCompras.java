package tdc;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.event.Event;
import javax.inject.Inject;

public class CarrinhoDeCompras {

	@Inject
	private Event<Item> evento;

	public void finalizaCompra() {
		Item itemComprado = new Item();

		// Dispara para todos que estao @Observers executarem algo
		evento.fire(itemComprado);

		// financeiro.executa(itemComprado);
		//
		// estoque.executa(itemComprado);
	}
}
