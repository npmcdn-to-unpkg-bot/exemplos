package pacote.embutido;

import javax.persistence.EntityManager;

import pacote.modelo2.Usuario;
import pacote.util.JpaUtil;

public class InserirComentarios {
    public static void main(String[] args) {
	EntityManager manager = JpaUtil.getEntityManager();
	manager.getTransaction().begin();
	Usuario usuario = new Usuario();
	usuario.setNome("Nome");
	usuario.setSenha("AAA");
	usuario.setCpf("AAAAAAAA");
//	
//	Post post = new Post();
//	post.setTitulo("AAAAAAAAAAAA");
//	post.setTexto("BBBBBBBBBBBB");
//	post.setUsuario(usuario);
//	
	
	manager.persist(usuario);
	//manager.persist(post);
	manager.getTransaction().commit();
	manager.close();
	JpaUtil.close();
    }
}
