package pacote.stream;

import java.math.BigDecimal;

public class Funcionario {
	private String nome;
	private String departamento;
	private BigDecimal salario;

	public Funcionario() {
	}

	public Funcionario(String nome, String departamento, BigDecimal salario) {
		this.nome = nome;
		this.departamento = departamento;
		this.salario = salario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}

	@Override
	public String toString() {
		return "Funcionario [nome=" + nome + ", departamento=" + departamento + ", salario=" + salario + "]";
	}
}
