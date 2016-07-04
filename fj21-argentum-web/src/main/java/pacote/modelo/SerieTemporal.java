package pacote.modelo;

import java.util.List;

public class SerieTemporal {
	private final List<Candle> candles;

	public SerieTemporal(List<Candle> candles) {
		this.candles = candles;
	}

	public Candle getCandle(int i) {
		return candles.get(i);
	}

	public int getUltimaPosicao() {
		return candles.size() - 1;
	}
}
