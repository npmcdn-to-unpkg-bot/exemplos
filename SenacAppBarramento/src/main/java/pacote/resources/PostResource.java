package pacote.resources;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import pacote.modelo.Post;
import pacote.modelo.PostComentario;
import pacote.repository.PostRepository;

@Path("/Post")
public class PostResource implements Serializable {
	private static final long serialVersionUID = 1L;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Post> listarPosts() {
		List<Post> posts = new PostRepository().listar();
		return posts;
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public PostComentario porId(@PathParam("id") int id) {
		PostComentario post = new PostRepository().porId(id);
		return post;
	}
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public void deletar(@PathParam("id") int id) {
		new PostRepository().deletar(id);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void Inserir(Post post) {
		post.setDataDaPostagem(Calendar.getInstance());
		new PostRepository().inserir(post);
	}
}
