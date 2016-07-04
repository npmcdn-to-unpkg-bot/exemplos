package pacote.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.Base64;
import java.util.Calendar;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import pacote.modelo.ImagemBase64;
import pacote.modelo.ImagemBase64Repository;
import pacote.modelo.Post;
import pacote.modelo.Usuario;

@Named
@RequestScoped
public class Base64Bean implements Serializable {
	private static final long serialVersionUID = 1L;

	private Part imagem;

	@Inject
	private ImagemBase64Repository repository;
	
	private List<ImagemBase64> base64s;

	public void salvar() {
		String formato = imagem.getContentType();
		String nome = imagem.getName();
		byte[] imageAsByte = new byte[(int) imagem.getSize()];
		try {
			imagem.getInputStream().read(imageAsByte);
			ImagemBase64 ib4 = new ImagemBase64();
			
			Base64.Encoder encoder = Base64.getMimeEncoder();
			String base64AsString = new String(encoder.encode(imageAsByte));
			
			ib4.setBase64(base64AsString);
			ib4.setFormato(formato);
			ib4.setNome(nome);
			//repository.inserir(ib4);
			
			
			
			
			
			
			Usuario antonio = new Usuario();
			antonio.setUsuarioId(1);
			
			Post post = new Post();
			post.setUsuario(antonio);
			post.setTitulo("#teste Imagem");
			post.setTexto("Sera que foi!");
			post.setCidade("Campo Grande");
			post.setDataDaPostagem(Calendar.getInstance());
			post.setImagem(base64AsString);
			
			
			WebTarget url;
			Client client = ClientBuilder.newClient();
			url = client.target("http://localhost:8080/Fluxo/Post");
			Response response = url.request().post(Entity.json(post));
			
			
			

			System.out.println(ib4.getBase64());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<ImagemBase64> getBase64s() {
		base64s = repository.listar();
		return base64s;
	}

	public Part getImagem() {
		return imagem;
	}

	public void setImagem(Part imagem) {
		this.imagem = imagem;
	}

	public void setRepository(ImagemBase64Repository repository) {
		this.repository = repository;
	}

	public ImagemBase64Repository getRepository() {
		return repository;
	}
}
