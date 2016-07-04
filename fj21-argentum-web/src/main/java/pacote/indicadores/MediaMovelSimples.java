package pacote.indicadores;

import java.math.BigDecimal;
import java.math.MathContext;

import pacote.modelo.SerieTemporal;

public class MediaMovelSimples implements Indicador {

	private Indicador outroIndicador;

	public MediaMovelSimples(Indicador indicador) {
		this.outroIndicador = indicador;
	}

	public BigDecimal calcula(int posicao, SerieTemporal serie) {
		BigDecimal soma = new BigDecimal("0.0");
		for (int i = posicao; i > posicao - 3; i--) {
			soma = soma.add(outroIndicador.calcula(i, serie));
		}
		return soma.divide(new BigDecimal("3"), new MathContext(2));
	}

	@Override
	public String toString() {
		return "";
	}
}
