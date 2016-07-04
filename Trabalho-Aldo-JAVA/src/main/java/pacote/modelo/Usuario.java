package pacote.modelo;

import java.io.Serializable;
import java.util.Date;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private String nome;
	private String funcao;
	private Date dataLogin;

	public boolean isLogado() {
		return nome != null;
	}

	public boolean isRecepicionista() {
		return "Recepcionista".equals(funcao);
	}

	public boolean isGacon() {
		return "Garçon".equals(funcao);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getFuncao() {
		return funcao;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}

	public Date getDataLogin() {
		return dataLogin;
	}

	public void setDataLogin(Date dataLogin) {
		this.dataLogin = dataLogin;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
