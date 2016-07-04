package pacote.bean;

import java.io.Serializable;
import java.util.Date;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.NoResultException;

import pacote.modelo.Funcionario;
import pacote.modelo.Usuario;
import pacote.repository.FuncionarioRepository;
import pacote.util.FacesUtils;

@Named
@RequestScoped
public class LoginBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private Usuario usuario;
	@Inject
	private FuncionarioRepository repository;

	private String nomeUsuario;
	private String senha;

	public String login() {
		try {
			Funcionario funcionario = repository.buscar(nomeUsuario, senha);
			if (funcionario != null) {
				usuario.setId(funcionario.getId());
				usuario.setNome(funcionario.getNome());
				usuario.setFuncao(funcionario.getFuncao());
				usuario.setDataLogin(new Date());

				if (usuario.isGacon()) {
					return "/Index.xhtml?faces-redirect=true";
				}
				if (usuario.isRecepicionista()) {
					return "/Atendimento.xhtml?faces-redirect=true";
				}
			}
		} catch (NoResultException e) {
			FacesUtils.mostrarMensagemErro("Usuário/Senha inválidos!", null);
		}
		return null;
	}

	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/Login.xhtml?faces-redirect=true";
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
