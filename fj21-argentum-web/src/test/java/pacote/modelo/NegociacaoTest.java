package pacote.modelo;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Test;

public class NegociacaoTest {

	@Test
	public void datNegociacaoEhImutavel() {
		Calendar data = Calendar.getInstance();
		data.set(Calendar.DAY_OF_MONTH, 15);
		Negociacao negociacao = new Negociacao(new BigDecimal("10"), 5, data);

		negociacao.getData().set(Calendar.DAY_OF_MONTH, 20);

		assertEquals(15, negociacao.getData().get(Calendar.DAY_OF_MONTH));
	}

	@Test(expected = IllegalArgumentException.class)
	public void naoCriaNegociacaoComDataNula() {
		new Negociacao(new BigDecimal("10"), 5, null);
	}

	@Test
	public void mesmoMilisegundoEhMesmoDia() {
		Calendar agora = Calendar.getInstance();
		Calendar mesmoMomento = (Calendar) agora.clone();

		Negociacao negociacao = new Negociacao(new BigDecimal("40.0"), 100, agora);

		assertTrue(negociacao.isMesmoDia(mesmoMomento));
	}

	@Test
	public void comHorariosDiferentesEhMesmoDia() {
		Calendar manha = new GregorianCalendar(2011, 10, 20, 8, 30);
		Calendar tarde = new GregorianCalendar(2011, 10, 20, 15, 30);

		Negociacao negociacao = new Negociacao(new BigDecimal("40.0"), 100, manha);

		assertTrue(negociacao.isMesmoDia(tarde));
	}

	@Test
	public void comDiaIgualMasMesDiferentesNaoEhMesmoDia() {
		Calendar hoje = new GregorianCalendar(2011, 10, 20, 8, 30);
		Calendar outroMes = new GregorianCalendar(2011, 11, 20, 15, 30);

		Negociacao negociacao = new Negociacao(new BigDecimal("40.0"), 100, hoje);

		assertFalse(negociacao.isMesmoDia(outroMes));
	}

	@Test
	public void comDiaIgualMesIgualEhAnoDiferenteNaoEhMesmoDia() {
		Calendar hoje = new GregorianCalendar(2012, 10, 20, 8, 30);
		Calendar outroMes = new GregorianCalendar(2011, 10, 20, 15, 30);

		Negociacao negociacao = new Negociacao(new BigDecimal("40.0"), 100, hoje);

		assertFalse(negociacao.isMesmoDia(outroMes));
	}

	@Test
	public void model() {

	}
}
