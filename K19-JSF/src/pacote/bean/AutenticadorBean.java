package pacote.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

@Named
@SessionScoped
public class AutenticadorBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private static Map<String, String> mapa = new HashMap<>();

	private String usuario;
	private String senha;

	static {
		AutenticadorBean.mapa.put("k19", "k19");
		AutenticadorBean.mapa.put("jonas.hirata", "jonas.hirata");
		AutenticadorBean.mapa.put("marcelo.martins", "marcelo.martins");
		AutenticadorBean.mapa.put("rafael.cosentino", "rafael.cosentino");
	}

	public String atentica() {
		FacesContext fc = FacesContext.getCurrentInstance();

		if (AutenticadorBean.mapa.containsKey(this.usuario)
				&& AutenticadorBean.mapa.get(this.usuario).equals(this.senha)) {

			ExternalContext ec = fc.getExternalContext();
			HttpSession session = (HttpSession) ec.getSession(false);
			session.setAttribute("usuario", this.usuario);

			return "/carros?faces-redirect=true";
		} else {
			FacesMessage fm = new FacesMessage("usuário e / ou senha inválidos");
			fm.setSeverity(FacesMessage.SEVERITY_ERROR);
			fc.addMessage(null, fm);
			return "/login?faces-redirect=true";
		}
	}

	public String registraSaida() {
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		HttpSession session = (HttpSession) ec.getSession(false);
		session.removeAttribute("usuario");
		return "/login?faces-redirect=true";
	}

	public static Map<String, String> getMapa() {
		return mapa;
	}

	public static void setMapa(Map<String, String> mapa) {
		AutenticadorBean.mapa = mapa;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}
