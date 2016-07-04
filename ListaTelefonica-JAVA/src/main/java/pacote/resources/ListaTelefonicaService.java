package pacote.resources;

import java.util.List;

import javax.swing.JOptionPane;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import pacote.modelo.Contato;
import pacote.modelo.Operadora;
import pacote.repository.ContatoRepository;
import pacote.repository.OperadoraRepository;

@Path("/")
public class ListaTelefonicaService {

	@GET
	@Path("/Contato")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Contato> listarContatos() {
		return new ContatoRepository().listar();
	}

	@GET
	@Path("/Operadora")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Operadora> listarOperadoras() {
		return new OperadoraRepository().listar();
	}
	
	@GET
	@Path("/Contato/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Contato buscarPorId(@PathParam("id") int id) {
		return new ContatoRepository().buscarporId(id);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void inserirContato(Contato contato) {
		new ContatoRepository().inserir(contato);
	}
	
	@POST
	@Path("/Deletar")
	@Consumes(MediaType.APPLICATION_JSON)
	public void deletarContatosList(List<Contato> contatos) {
		new ContatoRepository().deletar(contatos);
	}
	
	@DELETE
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void deletarContatos(@PathParam("id") int id) {
		new ContatoRepository().deletar(id);
	}
}
