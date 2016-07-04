package pacote.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Estado implements Serializable {
	private static final long serialVersionUID = 1L;

	private String nome;
	private String sigla;
	private List<String> cidades = new ArrayList<>();

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public List<String> getCidades() {
		return cidades;
	}

	public void setCidades(List<String> cidades) {
		this.cidades = cidades;
	}

}
