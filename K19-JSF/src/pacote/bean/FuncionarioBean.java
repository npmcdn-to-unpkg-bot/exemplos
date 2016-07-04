package pacote.bean;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import pacote.modelo.Funcionario;

@Named
@ViewScoped
public class FuncionarioBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private Funcionario funcionario = new Funcionario();

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

}
