package pacote.modelo;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Calendar;

import org.junit.Test;

public class CandleBuilderTest {

	@Test(expected = IllegalStateException.class)
	public void geracaoDeCandleDeveTerTodosOsDadosNecessarios() {
		CandleBuilder builder = new CandleBuilder();
		builder.comAbertura(new BigDecimal("40"));
		builder.comFechamento(new BigDecimal("40"));
		builder.comMinimo(new BigDecimal("40"));
		//builder.comMaximo(new BigDecimal("40"));
		builder.comVolume(new BigDecimal("57"));
		builder.comData(Calendar.getInstance());

		builder.geraCandle();
	}

	@Test
	public void test() {

	}
}
