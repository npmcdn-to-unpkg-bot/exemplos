package pacote.bean;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import pacote.modelo.CPF;

@Named
@ViewScoped
public class CPFBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private CPF cpf = new CPF();

	public CPF getCpf() {
		return cpf;
	}

	public void setCpf(CPF cpf) {
		this.cpf = cpf;
	}
}
