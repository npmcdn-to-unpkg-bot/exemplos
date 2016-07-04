package pacote.ws;

import static org.junit.Assert.*;

import org.junit.Test;

public class ClienteWebServiceTest {

	@Test
	public void seConectaAhUrl() {
		ClienteWebService service = new ClienteWebService();
		service.getNegociacoes();
	}

}
