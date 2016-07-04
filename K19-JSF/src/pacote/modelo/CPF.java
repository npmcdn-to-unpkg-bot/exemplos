package pacote.modelo;

import java.io.Serializable;

public class CPF implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long numeroDeIdentificacao;
	private Long primeiroDigitoVerificador;
	private Long segundoDigitoVerificador;

	public Long getNumeroDeIdentificacao() {
		return numeroDeIdentificacao;
	}

	public void setNumeroDeIdentificacao(Long numeroDeIdentificacao) {
		this.numeroDeIdentificacao = numeroDeIdentificacao;
	}

	public Long getPrimeiroDigitoVerificador() {
		return primeiroDigitoVerificador;
	}

	public void setPrimeiroDigitoVerificador(Long primeiroDigitoVerificador) {
		this.primeiroDigitoVerificador = primeiroDigitoVerificador;
	}

	public Long getSegundoDigitoVerificador() {
		return segundoDigitoVerificador;
	}

	public void setSegundoDigitoVerificador(Long segundoDigitoVerificador) {
		this.segundoDigitoVerificador = segundoDigitoVerificador;
	}

}
