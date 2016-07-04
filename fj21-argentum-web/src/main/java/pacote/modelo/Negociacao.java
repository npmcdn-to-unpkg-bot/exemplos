package pacote.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;

public final class Negociacao implements Serializable {
	private static final long serialVersionUID = 1L;

	private final BigDecimal preco;
	private final int quantidade;
	private final Calendar data;

	public Negociacao(BigDecimal preco, int quantidade, Calendar data) {
		if (data == null) {
			throw new IllegalArgumentException("Data não pode ser nula");
		}
		this.preco = preco;
		this.quantidade = quantidade;
		this.data = data;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public Calendar getData() {
		return (Calendar) data.clone();
	}

	public BigDecimal getVolume() {
		BigDecimal qtd = new BigDecimal(quantidade);
		return preco.multiply(qtd);
	}

	public boolean isMesmoDia(Calendar outraData) {
		if (this.data.get(Calendar.DAY_OF_MONTH) == outraData.get(Calendar.DAY_OF_MONTH)
				&& data.get(Calendar.MONTH) == outraData.get(Calendar.MONTH)
				&& data.get(Calendar.YEAR) == outraData.get(Calendar.YEAR)) {
			return true;
		}
		return false;
	}
}
