package pacote.indicadores;

import java.math.BigDecimal;

import pacote.modelo.SerieTemporal;

public interface Indicador {

	public BigDecimal calcula(int posicao, SerieTemporal serie);
}
