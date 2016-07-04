package pacote.indicadores;

import java.math.BigDecimal;
import java.math.MathContext;

import pacote.modelo.SerieTemporal;

public class MediaMovelPonderada implements Indicador {

	private Indicador outroIndicador;

	public MediaMovelPonderada(Indicador indicador) {
		this.outroIndicador = indicador;
	}

	public BigDecimal calcula(int posicao, SerieTemporal serie) {
		BigDecimal soma = new BigDecimal("0.0");
		int peso = 3;

		for (int i = posicao; i > posicao - 3; i--) {
			soma = soma.add(outroIndicador.calcula(i, serie).multiply(new BigDecimal(peso), new MathContext(2)));
			peso--;
		}
		return soma.divide(new BigDecimal("6"), new MathContext(2));
	}

	@Override
	public String toString() {
		return "";
	}
}
