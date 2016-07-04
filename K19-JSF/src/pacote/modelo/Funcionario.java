package pacote.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Funcionario implements Serializable {
	private static final long serialVersionUID = 1L;

	private BigDecimal salario;
	private Integer codigo;
	private Date aniversario;

	public BigDecimal getSalario() {
		return salario;
	}

	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Date getAniversario() {
		return aniversario;
	}

	public void setAniversario(Date aniversario) {
		this.aniversario = aniversario;
	}

}
