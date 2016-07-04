package pacote.service;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import pacote.modelo.Atendimento;
import pacote.modelo.Status;

public class AtendimentoWebService implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private WebTarget url;

	public AtendimentoWebService() {
		Client client = ClientBuilder.newClient();
		url = client.target("http://localhost:8080/JAX-RS/Atendimento");
	}

	public List<Atendimento> listar() {
		List<Atendimento> resultado = url.request().get(new GenericType<List<Atendimento>>() {
		});
		return resultado;
	}

	public Atendimento porId(int id) {
		Response response = url.path("/" + id).request().get();
		Atendimento atendimento = response.readEntity(Atendimento.class);
		return atendimento;
	}

	public Boolean registrarChegadaDoCliente(Atendimento atendimento) {
		Response response = url.request().post(Entity.json(atendimento));
		Boolean resposta = response.readEntity(Boolean.class);
		return resposta;
	}

	public Boolean atualizarDadosDoAtendimento(Atendimento atendimento) {
		Response response = url.request().put(Entity.json(atendimento));
		Boolean resposta = response.readEntity(Boolean.class);
		return resposta;
	}

	public static void main(String[] args) {
		Atendimento atendimento = new AtendimentoWebService().porId(12);
		System.out.println("ANTES " + atendimento);

		atendimento.setHoraSaida(Calendar.getInstance());
		atendimento.setStatus(Status.ATENDIDO);

		boolean b = new AtendimentoWebService().atualizarDadosDoAtendimento(atendimento);
		System.out.println(b);
		atendimento = new AtendimentoWebService().porId(12);
		System.out.println("DEPOIS " + atendimento);
	}
}
