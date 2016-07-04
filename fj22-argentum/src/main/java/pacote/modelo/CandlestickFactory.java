package pacote.modelo;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class CandlestickFactory {

	private Candlestick candleDoDia;

	public Candlestick constroiCandleStickPorData(Calendar data, List<Negociacao> negociacaos) {

		List<BigDecimal> ordenadaMaxMin = negociacaos.stream().map(e -> e.getPreco())
				.sorted((n1, n2) -> n1.compareTo(n2)).collect(Collectors.toList());
		double vol = ordenadaMaxMin.stream().mapToDouble(BigDecimal::doubleValue).sum();

		BigDecimal volume = ordenadaMaxMin.isEmpty() ? BigDecimal.ZERO
				: new BigDecimal(vol).setScale(2, RoundingMode.HALF_UP);

		BigDecimal maximo = ordenadaMaxMin.isEmpty() ? BigDecimal.ZERO : ordenadaMaxMin.get(ordenadaMaxMin.size() - 1);
		BigDecimal minimo = ordenadaMaxMin.isEmpty() ? BigDecimal.ZERO : ordenadaMaxMin.get(0);

		BigDecimal abertura = ordenadaMaxMin.isEmpty() ? BigDecimal.ZERO : negociacaos.get(0).getPreco();
		BigDecimal fechamento = ordenadaMaxMin.isEmpty() ? BigDecimal.ZERO
				: negociacaos.get(negociacaos.size() - 1).getPreco();

		CandleBuilder builder = new CandleBuilder();
		Candlestick candlestick = builder.comAbertura(abertura).comFechamento(fechamento).comMinimo(minimo)
				.comMaximo(maximo).comVolume(volume).comData(data).geraCandle();

		return candlestick;
	}

	public List<Candlestick> constroiCandles(List<Negociacao> todasNegociacaos) {
		List<Candlestick> candles = new ArrayList<>();

		List<Negociacao> negociacaosDoDia = new ArrayList<>();
		Calendar dataAtual = todasNegociacaos.get(0).getData();

		for (Negociacao negociacao : todasNegociacaos) {
			if (negociacao.getData().before(dataAtual)) {
				throw new IllegalStateException("Negociaçoes em ordem errada!");
			}
			if (!negociacao.isMesmoDia(dataAtual)) {
				Candlestick candleDoDia = constroiCandleStickPorData(dataAtual, negociacaosDoDia);

				candles.add(candleDoDia);
				negociacaosDoDia = new ArrayList<>();
				dataAtual = negociacao.getData();
			}
			negociacaosDoDia.add(negociacao);
		}

		Candlestick candleDoDia = constroiCandleStickPorData(dataAtual, negociacaosDoDia);
		candles.add(candleDoDia);

		return candles;
	}

	public List<Candlestick> constroiCandles2(List<Negociacao> todasNegociacaos) {
		Map<Calendar, List<Negociacao>> mapa = mapa(todasNegociacaos);
		List<Candlestick> candles = new ArrayList<>();

		Set<Calendar> chaves = mapa.keySet();
		for (Calendar chave : chaves) {
			List<Negociacao> negociacoesDoDia = mapa.get(chave);
			Candlestick candleDoDia = constroiCandleStickPorData(chave, negociacoesDoDia);
			candles.add(candleDoDia);
		}
		return candles;
	}

	public Map<Calendar, List<Negociacao>> mapa(List<Negociacao> todasNegociacaos) {

		Map<Calendar, List<Negociacao>> mapa = new LinkedHashMap<>();
		Calendar hoje = todasNegociacaos.get(0).getData();

		for (Negociacao negociacao : todasNegociacaos) {
			if (negociacao.isMesmoDia(hoje)) {
				if (mapa.containsKey(hoje)) {
					mapa.get(hoje).add(negociacao);
				} else {
					mapa.put(hoje, new ArrayList<>());
					mapa.get(hoje).add(negociacao);
				}
			} else {
				hoje = negociacao.getData();
				mapa.put(hoje, new ArrayList<>());
				mapa.get(hoje).add(negociacao);
			}
		}
		return mapa;
	}
}
