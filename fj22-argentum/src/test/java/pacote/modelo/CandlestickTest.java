package pacote.modelo;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Calendar;

import org.junit.Test;

public class CandlestickTest {

	@Test(expected = IllegalArgumentException.class)
	public void precoMaximoNaoPodeSerMaiorQueMinimo() {
		BigDecimal abertura = new BigDecimal("10");
		BigDecimal fechamento = new BigDecimal("20");
		BigDecimal minimo = new BigDecimal("20");
		BigDecimal maximo = new BigDecimal("10");
		BigDecimal volume = new BigDecimal("10000");

		new Candlestick(abertura, fechamento, minimo, maximo, volume, Calendar.getInstance());
	}

	@Test(expected = IllegalArgumentException.class)
	public void dataNaoPodeSerNula() {
		BigDecimal abertura = new BigDecimal("10");
		BigDecimal fechamento = new BigDecimal("20");
		BigDecimal minimo = new BigDecimal("20");
		BigDecimal maximo = new BigDecimal("30");
		BigDecimal volume = new BigDecimal("10000");

		new Candlestick(abertura, fechamento, minimo, maximo, volume, null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void valoresNaoPodemSerNegativos() {
		BigDecimal abertura = new BigDecimal("-10");
		BigDecimal fechamento = new BigDecimal("-20");
		BigDecimal minimo = new BigDecimal("-50");
		BigDecimal maximo = new BigDecimal("-10");
		BigDecimal volume = new BigDecimal("-10000");

		new Candlestick(abertura, fechamento, minimo, maximo, volume, Calendar.getInstance());
	}

	@Test(expected = IllegalArgumentException.class)
	public void valoresNaoPodemSerNulos() {
		BigDecimal abertura = new BigDecimal("0");
		BigDecimal fechamento = new BigDecimal("0");
		BigDecimal minimo = new BigDecimal("2");
		BigDecimal maximo = new BigDecimal("10");
		BigDecimal volume = new BigDecimal("0");

		new Candlestick(abertura, null, minimo, maximo, volume, Calendar.getInstance());
	}

	@Test
	public void quandoAberturaEhIgualFechamentoEhAlta() {
		BigDecimal abertura = new BigDecimal("0");
		BigDecimal fechamento = new BigDecimal("0");
		BigDecimal minimo = new BigDecimal("2");
		BigDecimal maximo = new BigDecimal("10");
		BigDecimal volume = new BigDecimal("0");

		Candlestick candlestick = new Candlestick(abertura, fechamento, minimo, maximo, volume, Calendar.getInstance());

		assertEquals(true, candlestick.isAlta());
	}
}
