package pacote.modelo;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class VeiculoId implements Serializable {
	private static final long serialVersionUID = 1L;

	private String placa;
	private String cidade;

	public VeiculoId(String placa, String cidade) {
		super();
		this.placa = placa;
		this.cidade = cidade;
	}

	public VeiculoId() {
		// TODO Auto-generated constructor stub
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

}
