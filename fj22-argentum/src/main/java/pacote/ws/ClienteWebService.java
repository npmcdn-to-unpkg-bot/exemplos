package pacote.ws;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import pacote.modelo.Negociacao;
import pacote.reader.LeitorXML;

public class ClienteWebService {

	private static final String URL_WEBSERVICE = "http://argentumws.caelum.com.br/negociacoes";

	public List<Negociacao> getNegociacoes() {
		HttpURLConnection connection = null;
		try {
			URL url = new URL(URL_WEBSERVICE);
			connection = (HttpURLConnection) url.openConnection();
			InputStream inputStream = connection.getInputStream();
			return new LeitorXML().carrega(inputStream);
		} catch (Exception e) {
			throw new RuntimeException();
		} finally {
			connection.disconnect();
		}
	}
}
