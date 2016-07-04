package livroandroid.lib.utils;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import pacote.modelo.Post;

public class PostService {

	// Faz a requisição HTTP, cria a lista de carros e salva o JSON em arquivo
	public static List<Post> getPostFromWebService(String url) throws IOException {

		HttpHelper http = new HttpHelper();
		String json = http.doGet(url);
		Type listType = new TypeToken<ArrayList<Post>>(){}.getType();
		Gson gson = new GsonBuilder().create();
		List<Post> listPosts = gson.fromJson(json, listType);
		return listPosts;
	}

	
	public static void main(String[] args) throws IOException {
		String url = "http://localhost:8080/Fluxo/Post";
		List<Post> posts = getPostFromWebService(url);
		
		for (Post post : posts) {
			System.out.println(post);
		}
		
//		Usuario usuario = new Usuario();
//		usuario.setUsuarioId(1);
//		usuario.setNome("Antonio Cesar");
//		usuario.setCpf("11");
//		usuario.setSenha("123");
//		
//		Post post = new Post();
//		post.setUsuario(usuario);
//		post.setTexto("Testando a biblioteca GSON do google");
//		post.setTitulo("GSON sera que vai funcionar?");
//		post.setDataDaPostagem(Calendar.getInstance());
//		
//		Usuario usuario2 = new Usuario();
//		usuario2.setUsuarioId(1);
//		usuario2.setNome("Elton naoki");
//		usuario2.setCpf("11");
//		usuario2.setSenha("123");
//		
//		Post post2 = new Post();
//		post2.setUsuario(usuario2);
//		post2.setTexto("Outro post qualquer");
//		post2.setTitulo("Nao sei mano");
//		post2.setDataDaPostagem(Calendar.getInstance());
//		
//		List<Post> posts = new ArrayList<>();
//		posts.add(post);
//		posts.add(post2);
//		
//		String json = new Gson().toJson(posts);
//		
//		Type listType = new TypeToken<ArrayList<Post>>(){}.getType();
//		List<Post> listPosts = new Gson().fromJson(json, listType);
//		
//		System.out.println(listPosts);
		
	}
}