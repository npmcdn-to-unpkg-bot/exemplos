package livroandroid.lib.utils;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;

import pacote.modelo.Post;
import pacote.modelo.Usuario;

public class HttpClientPOST {
	public static void main(String[] args) throws Exception {
		Usuario usuario = new Usuario();
		usuario.setUsuarioId(1);
		
		Post post = new Post();
		post.setUsuario(usuario);
		post.setTexto("Testando a biblioteca GSON do google");
		post.setTitulo("GSON sera que vai funcionar?");
		
		String json = new Gson().toJson(post);
		String url = "http://localhost:8080/Fluxo/Post";

		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);

		StringEntity entity = new StringEntity(json, ContentType.create("application/json", Consts.UTF_8));
		entity.setChunked(true);

		httpPost.setEntity(entity);
		CloseableHttpResponse response2 = httpclient.execute(httpPost);

		try {
			System.out.println(response2.getStatusLine());
			HttpEntity entity2 = response2.getEntity();
			EntityUtils.consume(entity2);
		} finally {
			response2.close();
		}
	}
}
