package pacote.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class FacesUtils {

	public static void mostrarMensagemSucesso(String titulo, String mensagem) {
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, titulo, mensagem);
		context.addMessage(null, message);
	}

	public static void mostrarMensagemErro(String titulo, String mensagem) {
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, titulo, mensagem);
		context.addMessage(null, message);
	}

}
