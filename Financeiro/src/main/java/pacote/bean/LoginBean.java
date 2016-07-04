package pacote.bean;

import java.io.Serializable;
import java.util.Calendar;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import pacote.modelo.Usuario;

@Named
@RequestScoped
public class LoginBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private Usuario usuario;

	private String nomeUsuario;
	private String senha;

	public String login() {
		if ("admin".equals(this.nomeUsuario) && "123".equals(this.senha)) {
			this.usuario.setNome(this.nomeUsuario);
			this.usuario.setDataLogin(Calendar.getInstance());
			return "/ConsultaLancamentos?faces-redirect=true";
		} else {
			FacesUtils.mostrarMensagemErro("Usuário/senha inválidos!", null);
			return null;
		}
	}

	public String logout() {
		//FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		this.usuario.setNome(null);
		return "Login.xhtml?faces-redirect=true";
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}
