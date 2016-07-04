package pacote.modelo;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;

public final class Negociacao {
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

	private BigDecimal getVolume() {
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

	public Calendar mesmoDia(Calendar outraData) {
		if (this.data.get(Calendar.DAY_OF_MONTH) == outraData.get(Calendar.DAY_OF_MONTH)
				&& data.get(Calendar.MONTH) == outraData.get(Calendar.MONTH)
				&& data.get(Calendar.YEAR) == outraData.get(Calendar.YEAR)) {
			return outraData;
		}
		return data;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Negociacao other = (Negociacao) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		return true;
	}

	public static void main(String[] args) {
		System.out.println("Subtrai");
		System.out.println(new BigDecimal("2.00").subtract(new BigDecimal("1.1")));
		System.out.println("");

		System.out.println("Soma");
		System.out.println(new BigDecimal("2.00").add(new BigDecimal("1.2")));
		System.out.println("");

		System.out.println("Compara");
		System.out.println(new BigDecimal("2.00").compareTo(new BigDecimal("1.3")));
		System.out.println("");

		System.out.println("Divide");
		System.out.println(new BigDecimal("2.00").divide(new BigDecimal("2.00")));
		System.out.println("");

		System.out.println("Máximo");
		System.out.println(new BigDecimal("2.00").max(new BigDecimal("1.5")));
		System.out.println("");

		System.out.println("Mínimo");
		System.out.println(new BigDecimal("2.00").min(new BigDecimal("1.6")));
		System.out.println("");

		System.out.println("Potência");
		System.out.println(new BigDecimal("2.00").pow(2));
		System.out.println("");

		System.out.println("Multiplica");
		System.out.println(new BigDecimal("2.00").multiply(new BigDecimal("1.8")));
		System.out.println("");

		// por padrão ele não fará nenhum tipo de arredondamento
		System.out.println("Divide com VIRGULA");
		System.out.println(new BigDecimal("1.00").divide(new BigDecimal("1.3"), 1, RoundingMode.UP));

		int quantidade = 2;
		BigDecimal qtd = new BigDecimal(quantidade);
		BigDecimal qtd2 = new BigDecimal("10");
		System.out.println(qtd.divide(qtd2));
	}
}
