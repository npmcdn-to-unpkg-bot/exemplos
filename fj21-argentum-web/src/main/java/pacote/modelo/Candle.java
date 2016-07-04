package pacote.modelo;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public final class Candle {
	private final BigDecimal abertura;
	private final BigDecimal fechamento;
	private final BigDecimal minimo;
	private final BigDecimal maximo;
	private final BigDecimal volume;
	private final Calendar data;

	public Candle(BigDecimal abertura, BigDecimal fechamento, BigDecimal minimo, BigDecimal maximo,
			BigDecimal volume, Calendar data) {
		if (minimo.compareTo(maximo) == 1) {
			throw new IllegalArgumentException("Valor minimo não pode ser maior que o maximo");
		}

		this.abertura = this.validaValores(abertura);
		this.fechamento = this.validaValores(fechamento);
		this.minimo = this.validaValores(minimo);
		this.maximo = this.validaValores(maximo);
		this.volume = this.validaValores(volume);
		this.data = verificaDataNula(data);
	}

	private Calendar verificaDataNula(Calendar data) {
		if (data == null) {
			throw new IllegalArgumentException("Data não pode ser nula");
		}
		return data;
	}

	private BigDecimal validaValores(BigDecimal valor) {
		if (valor == null) {
			throw new IllegalArgumentException("Valores não podem ser nulos");
		}
		if (valor.signum() < 0) {
			throw new IllegalArgumentException("Valores não podem ser negativos");
		}
		return valor;
	}

	public BigDecimal getAbertura() {
		return abertura;
	}

	public BigDecimal getFechamento() {
		return fechamento;
	}

	public BigDecimal getMinimo() {
		return minimo;
	}

	public BigDecimal getMaximo() {
		return maximo;
	}

	public BigDecimal getVolume() {
		return volume;
	}

	public Calendar getData() {
		return data;
	}

	public boolean isAlta() {
		return abertura.compareTo(fechamento) <= 0 ? true : false;
	}

	public boolean isBaixa() {
		return abertura.compareTo(fechamento) == 1 ? true : false;
	}

	public static void main(String[] args) {
		BigDecimal abertura = new BigDecimal("111.00");
		BigDecimal fechamento = new BigDecimal("200000");

		int resul = abertura.compareTo(fechamento);

		System.out.println(abertura.compareTo(fechamento) == 1 ? "de baixa" : "de alta");
	}

	@Override
	public String toString() {
		String dataFormatada = new SimpleDateFormat("dd/MM/yyyy").format(data.getTime());
		return "Candlestick [abertura=" + abertura + ", fechamento=" + fechamento + ", minimo=" + minimo + ", maximo="
				+ maximo + ", volume=" + volume + ", data=" + dataFormatada + "]";
	}
}
