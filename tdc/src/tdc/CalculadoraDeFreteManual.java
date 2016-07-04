package tdc;

import cdi.Frete;
import cdi.FreteManual;
import cdi.TipoFrete;

@FreteManual
public class CalculadoraDeFreteManual implements CalculadoraDeFrete {

	public CalculadoraDeFreteManual() {
		System.out.println("Calculadora Manual");
	}

	@Override
	public double calcula(double valorTotal) {
		System.out.println("Calculo Manual");
		return valorTotal * 0.20;
	}

}
