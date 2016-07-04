package pacote.teste;

import pacote.modelo.PostComentario;
import pacote.repository.PostRepository;

public class TestaListarPosts {
	public static void main(String[] args) {
		
		//List<Post> posts = new PostRepository().listar();
		PostComentario post = new PostRepository().porId(1);
		
		System.out.println(post);
	}
}
