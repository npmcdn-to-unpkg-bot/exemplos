package pacote.modelo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class GeradorDeSerie {

	/**
	 * Serve para ajudar a fazer os testes. Recebe uma sequ�ncia de valores e
	 * cria candles com abertura, fechamento, minimo e maximo iguais, mil de
	 * volume e data de hoje. Finalmente, devolve tais candles encapsuladas em
	 * uma Serie Temporal.
	 * 
	 * @param valores
	 * @return
	 */
	public static SerieTemporal criaSerie(double... valores) {
		List<Candle> candles = new ArrayList<>();

		for (double d : valores) {
			Candle c = new Candle(new BigDecimal(d), new BigDecimal(d), new BigDecimal(d), new BigDecimal(d),
					new BigDecimal(1000), Calendar.getInstance());
			
			candles.add(c);
			//System.out.println(c.getFechamento());
		}

		return new SerieTemporal(candles);
	}

}
