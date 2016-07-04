package pacote.stream;

import java.math.BigDecimal;

public class Conta {
	private int numeroDaConta;
	private BigDecimal valor;
	private String titularDaConta;

	public Conta(int numeroDaConta, BigDecimal valor, String titularDaConta) {
		this.numeroDaConta = numeroDaConta;
		this.valor = valor;
		this.titularDaConta = titularDaConta;
	}

	public Conta() {
		// TODO Auto-generated constructor stub
	}

	public String getTitularDaConta() {
		return titularDaConta;
	}

	public void setTitularDaConta(String titularDaConta) {
		this.titularDaConta = titularDaConta;
	}

	public int getNumeroDaConta() {
		return numeroDaConta;
	}

	public void setNumeroDaConta(int numeroDaConta) {
		this.numeroDaConta = numeroDaConta;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

}
