package pacote.indicadores;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.math.MathContext;

import org.junit.Test;

import pacote.modelo.GeradorDeSerie;
import pacote.modelo.SerieTemporal;

public class MediaMovelPonderadaTest {

	@Test
	public void test() {
		SerieTemporal serie = GeradorDeSerie.criaSerie(1, 2, 3, 4, 5, 6);
		MediaMovelPonderada mms = new MediaMovelPonderada(new IndicadorAbertura());

		BigDecimal b = new BigDecimal("14.0").divide(new BigDecimal("6"), new MathContext(2));
		assertEquals(b, mms.calcula(2, serie));
		
		BigDecimal b2 = new BigDecimal("20.0").divide(new BigDecimal("6"), new MathContext(2));
		assertEquals(b2, mms.calcula(3, serie));
		
		BigDecimal b3 = new BigDecimal("26.0").divide(new BigDecimal("6"), new MathContext(2));
		assertEquals(b3, mms.calcula(4, serie));
		
		BigDecimal b4 = new BigDecimal("32.0").divide(new BigDecimal("6"), new MathContext(2));
		assertEquals(b4, mms.calcula(5, serie));
	}

}
