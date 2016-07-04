package tdc;

import cdi.Frete;
import cdi.FreteAutomatico;
import cdi.TipoFrete;

@FreteAutomatico
public class CalculadoraDeFreteAutomatico implements CalculadoraDeFrete {

	@Override
	public double calcula(double valorTotal) {
		System.out.println("Calculo Automático Sincrono");
		return valorTotal * 0.10;
	}

}
