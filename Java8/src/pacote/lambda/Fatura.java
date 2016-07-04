package pacote.lambda;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Fatura {
	private String emailDevedor;
	private BigDecimal valor;
	private LocalDate dataVencimento;
	private boolean notificacaoEnviada;

	public Fatura(String emailDevedor, BigDecimal valor, LocalDate dataVencimento) {
		this.emailDevedor = emailDevedor;
		this.valor = valor;
		this.dataVencimento = dataVencimento;
	}

	public Fatura() {
		// TODO Auto-generated constructor stub
	}

	public String resumoFatura() {
		String dataFormatada = this.dataVencimento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		return String.format("Valor R$ %s, Vencimento: %s", this.valor, dataFormatada);
	}

	public String getEmailDevedor() {
		return emailDevedor;
	}

	public void setEmailDevedor(String emailDevedor) {
		this.emailDevedor = emailDevedor;
	}

	public boolean isNotificacaoEnviada() {
		return notificacaoEnviada;
	}

	public void setNotificacaoEnviada(boolean notificacaoEnviada) {
		this.notificacaoEnviada = notificacaoEnviada;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public LocalDate getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(LocalDate dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	@Override
	public String toString() {
		return "Fatura [emailDevedor=" + emailDevedor + ", valor=" + valor + ", dataVencimento="
				+ dataVencimento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "]";
	}

}
