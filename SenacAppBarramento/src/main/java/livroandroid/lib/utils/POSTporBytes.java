package livroandroid.lib.utils;

import com.google.gson.Gson;

import pacote.modelo.Post;
import pacote.modelo.Usuario;

public class POSTporBytes {
	public static void main(String[] args) throws Exception {
		Usuario usuario = new Usuario();
		usuario.setUsuarioId(2);
		
		Post post = new Post();
		post.setUsuario(usuario);
		post.setTexto("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF");
		post.setTitulo("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF");
		
		String json = new Gson().toJson(post);
		String url = "http://localhost:8080/Fluxo/Post";
		System.out.println(json);
		new HttpHelper().doPost(url, json.getBytes(), "UTF-8");
	}
}
