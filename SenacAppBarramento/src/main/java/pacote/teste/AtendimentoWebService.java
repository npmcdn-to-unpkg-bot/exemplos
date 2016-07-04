package pacote.teste;

import java.io.Serializable;
import java.util.Calendar;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import pacote.modelo.Comentario;
import pacote.modelo.Post;
import pacote.modelo.Usuario;

public class AtendimentoWebService implements Serializable {
	private static final long serialVersionUID = 1L;

	private WebTarget url;

	public AtendimentoWebService() {
		Client client = ClientBuilder.newClient();
		url = client.target("http://localhost:8080/Fluxo/Comentario");
	}

	public void registrarChegadaDoCliente(Post post) {
		Response response = url.request().post(Entity.json(post));
	}
	
	public void comentario(Comentario comentario) {
		Response response = url.request().post(Entity.json(comentario));
	}

	public static void main(String[] args) {
		
		Usuario usuario = new Usuario();
		usuario.setUsuarioId(4);
		
		Post post = new Post();
		post.setPostId(2);
		
		Comentario comentario01 = new Comentario();
		comentario01.setUsuario(usuario);
		comentario01.setPost(post);
		comentario01.setTexto("AAAAAAAAAAAA  MENINO GUERRIDO");
		comentario01.setDataDoComentario(Calendar.getInstance());
		
		
		new AtendimentoWebService().comentario(comentario01);
	}
}
