package pacote.modelo;

import java.math.BigDecimal;
import java.util.Calendar;

public class CandleBuilder {
	private BigDecimal abertura;
	private BigDecimal fechamento;
	private BigDecimal minimo;
	private BigDecimal maximo;
	private BigDecimal volume;
	private Calendar data;
	private int count = 0;

	public CandleBuilder comAbertura(BigDecimal abertura) {
		this.abertura = abertura;
		count++;
		return this;
	}

	public CandleBuilder comFechamento(BigDecimal fechamento) {
		this.fechamento = fechamento;
		count++;
		return this;
	}

	public CandleBuilder comMinimo(BigDecimal minimo) {
		this.minimo = minimo;
		count++;
		return this;
	}

	public CandleBuilder comMaximo(BigDecimal maximo) {
		this.maximo = maximo;
		count++;
		return this;
	}

	public CandleBuilder comVolume(BigDecimal volume) {
		this.volume = volume;
		count++;
		return this;
	}

	public CandleBuilder comData(Calendar data) {
		this.data = data;
		count++;
		return this;
	}

	public Candlestick geraCandle() {
		if (count != 6) {
			throw new IllegalStateException("CandleBuilder precisa de todos os valores de uma Candle");
		}
		return new Candlestick(abertura, fechamento, minimo, maximo, volume, data);
	}
}
