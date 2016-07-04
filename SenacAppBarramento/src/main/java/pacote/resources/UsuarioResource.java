package pacote.resources;

import java.io.Serializable;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import pacote.modelo.Usuario;
import pacote.repository.UsuarioRepository;

@Path("/Usuario")
public class UsuarioResource implements Serializable {
	private static final long serialVersionUID = 1L;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Usuario logar(@QueryParam("cpf") String cpf, @QueryParam("senha") String senha) {
		Usuario usuario = new UsuarioRepository().logar(cpf, senha);
		return usuario;
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void atualizar(Usuario usuario) {
		new UsuarioRepository().atualizar(usuario);
	}
}
