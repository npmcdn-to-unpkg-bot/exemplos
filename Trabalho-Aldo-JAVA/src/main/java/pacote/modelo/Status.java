package pacote.modelo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public enum Status {
	ATENDIDO("ATENDIDO"), CANCELADO("CANCELADO"), ESPERANDO("ESPERANDO"), NA_MESA("NA MESA");

	private String descricao;
	
	Status(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
