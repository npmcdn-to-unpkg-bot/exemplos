package livroandroid.lib.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import pacote.modelo.PostComentario;

public class HttpClientGET {
	public static void main(String[] args) throws IOException {
		String url = "http://localhost:8080/Fluxo/Post/2";
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);
		CloseableHttpResponse response1 = httpclient.execute(httpGet);

		try {
			HttpEntity entity1 = response1.getEntity();
			String json = EntityUtils.toString(entity1);

			GsonBuilder gsonBuilder = new GsonBuilder().registerTypeAdapter(Calendar.class, new CalendarDeserializer());
			Gson gson = gsonBuilder.create();
			PostComentario post = gson.fromJson(json, PostComentario.class);
			System.out.println(post);
		} finally {
			response1.close();
		}
	}
}
