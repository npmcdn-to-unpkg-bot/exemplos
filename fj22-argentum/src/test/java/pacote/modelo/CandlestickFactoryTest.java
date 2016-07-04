package pacote.modelo;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class CandlestickFactoryTest {

	@Test
	public void sequenciaSimplesDeNegociacoes() {
		Calendar hoje = Calendar.getInstance();

		Negociacao n1 = new Negociacao(new BigDecimal("40.5"), 100, hoje);
		Negociacao n2 = new Negociacao(new BigDecimal("45.0"), 100, hoje);
		Negociacao n3 = new Negociacao(new BigDecimal("39.8"), 100, hoje);
		Negociacao n4 = new Negociacao(new BigDecimal("42.3"), 100, hoje);

		List<Negociacao> negociacaos = Arrays.asList(n1, n2, n3, n4);

		CandlestickFactory fabrica = new CandlestickFactory();
		Candlestick candle = fabrica.constroiCandleStickPorData(hoje, negociacaos);

		assertEquals(new BigDecimal("40.5"), candle.getAbertura());
		assertEquals(new BigDecimal("42.3"), candle.getFechamento());
		assertEquals(new BigDecimal("39.8"), candle.getMinimo());
		assertEquals(new BigDecimal("45.0"), candle.getMaximo());
		assertEquals(new BigDecimal("167.60"), candle.getVolume());
	}

	@Test
	public void semNenhumaNegociacaoGeraCandleComZeros() {
		Calendar hoje = Calendar.getInstance();

		List<Negociacao> negociacaos = Arrays.asList();

		CandlestickFactory fabrica = new CandlestickFactory();
		Candlestick candle = fabrica.constroiCandleStickPorData(hoje, negociacaos);

		assertEquals(new BigDecimal("0"), candle.getAbertura());
		assertEquals(new BigDecimal("0"), candle.getFechamento());
		assertEquals(new BigDecimal("0"), candle.getMinimo());
		assertEquals(new BigDecimal("0"), candle.getMaximo());
		assertEquals(new BigDecimal("0"), candle.getVolume());
	}

	@Test
	public void apenasUmaNegociacaoGeraCandleComValoresIguais() {
		Calendar hoje = Calendar.getInstance();

		Negociacao n1 = new Negociacao(new BigDecimal("40.5"), 100, hoje);
		List<Negociacao> negociacaos = Arrays.asList(n1);

		CandlestickFactory fabrica = new CandlestickFactory();
		Candlestick candle = fabrica.constroiCandleStickPorData(hoje, negociacaos);

		assertEquals(new BigDecimal("40.5"), candle.getAbertura());
		assertEquals(new BigDecimal("40.5"), candle.getFechamento());
		assertEquals(new BigDecimal("40.5"), candle.getMinimo());
		assertEquals(new BigDecimal("40.5"), candle.getMaximo());
		assertEquals(new BigDecimal("40.50"), candle.getVolume());
	}

	@Test
	public void paraNegociacoesDeTresDiasDistintosGeraTresCandles() {
		Calendar hoje = Calendar.getInstance();

		Negociacao n1 = new Negociacao(new BigDecimal("40.5"), 100, hoje);
		Negociacao n2 = new Negociacao(new BigDecimal("45.0"), 100, hoje);
		Negociacao n3 = new Negociacao(new BigDecimal("39.8"), 100, hoje);
		Negociacao n4 = new Negociacao(new BigDecimal("42.3"), 100, hoje);

		Calendar amanha = (Calendar) hoje.clone();
		amanha.add(Calendar.DAY_OF_MONTH, 1);

		Negociacao n5 = new Negociacao(new BigDecimal("40.5"), 100, amanha);
		Calendar amanha2 = (Calendar) amanha.clone();
		amanha2.add(Calendar.HOUR, 5);
		Negociacao n6 = new Negociacao(new BigDecimal("45.0"), 100, amanha2);

		Calendar depois = (Calendar) amanha.clone();
		depois.add(Calendar.DAY_OF_MONTH, 1);

		Negociacao n7 = new Negociacao(new BigDecimal("40.5"), 100, depois);
		Negociacao n8 = new Negociacao(new BigDecimal("45.0"), 100, depois);

		List<Negociacao> negociacaos = Arrays.asList(n1, n2, n3, n4, n5, n6, n7, n8);

		CandlestickFactory fabrica = new CandlestickFactory();
		List<Candlestick> candles = fabrica.constroiCandles(negociacaos);

		assertEquals(3, candles.size());
	}

	@Test
	public void mapaComTresDias() {
		Calendar hoje = Calendar.getInstance();

		Negociacao n1 = new Negociacao(new BigDecimal("40.5"), 100, hoje);
		Negociacao n2 = new Negociacao(new BigDecimal("45.0"), 100, hoje);
		Negociacao n3 = new Negociacao(new BigDecimal("39.8"), 100, hoje);
		Negociacao n4 = new Negociacao(new BigDecimal("42.3"), 100, hoje);

		Calendar amanha = (Calendar) hoje.clone();
		amanha.add(Calendar.DAY_OF_MONTH, 1);

		Negociacao n5 = new Negociacao(new BigDecimal("40.5"), 100, amanha);
		
		Calendar amanha2 = (Calendar) amanha.clone();
		amanha2.add(Calendar.HOUR, 1);
		Negociacao n6 = new Negociacao(new BigDecimal("45.0"), 100, amanha2);

		Calendar depois = (Calendar) amanha.clone();
		depois.add(Calendar.DAY_OF_MONTH, 1);

		Negociacao n7 = new Negociacao(new BigDecimal("40.5"), 100, depois);
		Negociacao n8 = new Negociacao(new BigDecimal("45.0"), 100, depois);

		List<Negociacao> negociacaos = Arrays.asList(n1, n2, n3, n4, n5, n6, n7, n8);

		CandlestickFactory fabrica = new CandlestickFactory();
		Map<Calendar, List<Negociacao>> seila = fabrica.mapa(negociacaos);

		assertEquals(3, seila.size());
	}
	
	@Test(expected = IllegalStateException.class)
	public void naoPermiteCriarCandlesComNegociacoesForaDaOrdem() {
		Calendar hoje = Calendar.getInstance();

		Negociacao n1 = new Negociacao(new BigDecimal("40.5"), 100, hoje);
		Negociacao n2 = new Negociacao(new BigDecimal("45.0"), 100, hoje);
		Negociacao n3 = new Negociacao(new BigDecimal("39.8"), 100, hoje);
		Negociacao n4 = new Negociacao(new BigDecimal("42.3"), 100, hoje);

		Calendar amanha = (Calendar) hoje.clone();
		amanha.add(Calendar.DAY_OF_MONTH, 1);

		Negociacao n5 = new Negociacao(new BigDecimal("40.5"), 100, amanha);
		Negociacao n6 = new Negociacao(new BigDecimal("45.0"), 100, amanha);

		Calendar depois = (Calendar) amanha.clone();
		depois.add(Calendar.DAY_OF_MONTH, 1);

		Negociacao n7 = new Negociacao(new BigDecimal("40.5"), 100, depois);
		Negociacao n8 = new Negociacao(new BigDecimal("45.0"), 100, depois);

		List<Negociacao> negociacaos = Arrays.asList(n8, n1, n3, n4, n7, n6, n5, n1);

		CandlestickFactory fabrica = new CandlestickFactory();
		List<Candlestick> candles = fabrica.constroiCandles(negociacaos);

	}
}
