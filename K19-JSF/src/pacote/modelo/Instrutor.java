package pacote.modelo;

import java.io.Serializable;

public class Instrutor implements Serializable {
	private static final long serialVersionUID = 1L;

	private String nome;
	private String dataNascimento;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

}