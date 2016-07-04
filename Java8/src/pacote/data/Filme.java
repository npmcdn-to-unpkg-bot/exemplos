package pacote.data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Filme {
	private String nome;
	private LocalDate dataLancamento;
	private BigDecimal valor;

	public Filme(String nome, LocalDate dataLancamento, BigDecimal valor) {
		this.nome = nome;
		this.dataLancamento = dataLancamento;
		this.valor = valor;
	}

	public Filme() {

	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDate getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(LocalDate dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "Filme [nome=" + nome + ", dataLancamento="
				+ dataLancamento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ", valor=" + valor + "]";
	}
}
