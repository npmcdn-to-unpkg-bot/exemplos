package livroandroid.lib.utils;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import pacote.modelo.Post;

public class HttpClientGETList {
	public static void main(String[] args) throws IOException {
		String url = "http://localhost:8080/Fluxo/Post";
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);
		CloseableHttpResponse response = httpclient.execute(httpGet);

		try {
			HttpEntity entity = response.getEntity();
			String json = EntityUtils.toString(entity);
			
			Type listType = new TypeToken<ArrayList<Post>>(){}.getType();
			GsonBuilder gsonBuilder = new GsonBuilder().registerTypeAdapter(Calendar.class, new CalendarDeserializer());
			Gson gson = gsonBuilder.create();
			List<Post> listPosts = gson.fromJson(json, listType);

			for (Post post : listPosts) {
				System.out.println(post);
			}
		} finally {
			response.close();
		}
	}
}
