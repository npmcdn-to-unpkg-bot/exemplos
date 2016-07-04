package pacote.resources;

import java.io.Serializable;
import java.util.Calendar;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import pacote.modelo.Comentario;
import pacote.repository.ComentarioRepository;

@Path("/Comentario")
public class ComentarioResource implements Serializable {
	private static final long serialVersionUID = 1L;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void Inserir(Comentario comentario) {
		comentario.setDataDoComentario(Calendar.getInstance());
		new ComentarioRepository().inserir(comentario);
	}
}
