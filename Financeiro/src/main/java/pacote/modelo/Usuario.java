package pacote.modelo;

import java.io.Serializable;
import java.util.Calendar;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	private String nome;
	private Calendar dataLogin;

	public String getNome() {
		return nome;
	}

	public boolean isLogado() {
		return nome != null;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Calendar getDataLogin() {
		return dataLogin;
	}

	public void setDataLogin(Calendar dataLogin) {
		this.dataLogin = dataLogin;
	}
}
