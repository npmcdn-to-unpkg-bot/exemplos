package pacote.indicadores;

import java.math.BigDecimal;

import pacote.modelo.SerieTemporal;

public class IndicadorAbertura implements Indicador {

	@Override
	public BigDecimal calcula(int posicao, SerieTemporal serie) {
		return serie.getCandle(posicao).getAbertura();
	}

}
