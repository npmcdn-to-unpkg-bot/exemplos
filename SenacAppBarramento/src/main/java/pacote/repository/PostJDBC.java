package pacote.repository;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import pacote.modelo.Comentario;
import pacote.modelo.Usuario;

public class PostJDBC implements Serializable {
	private static final long serialVersionUID = 1L;

	private Connection connection;

	public PostJDBC(Connection connection) {
		this.connection = connection;
	}

	public List<Comentario> buscarPorIdDoPost(int id) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT c.comentario_id, c.data_do_comentario, c.texto, ");
		sql.append("u.nome, u.cpf, u.senha, u.foto_perfil,");
		sql.append("c.usuario_id FROM comentario c ");
		sql.append("INNER JOIN usuario u ON c.usuario_id = u.usuario_id ");
		sql.append("WHERE c.post_id = ?");

		List<Comentario> comentarios = new ArrayList<>();
		try {
			PreparedStatement comando = connection.prepareStatement(sql.toString());
			comando.setInt(1, id);
			ResultSet result = comando.executeQuery();
			
			while (result.next()) {
				// Pega Atendimento
				Comentario comentario = new Comentario();
				comentario.setComentarioId(result.getInt("comentario_id"));

				if (result.getDate("data_do_comentario") != null) {
					Calendar dataComentario = Calendar.getInstance();
					dataComentario.setTime(result.getTimestamp("data_do_comentario"));
					comentario.setDataDoComentario(dataComentario);
				}

				comentario.setTexto(result.getString("texto"));

				// Pega Usuario
				Usuario usuario = new Usuario();
				usuario.setUsuarioId(result.getInt("usuario_id"));
				usuario.setNome(result.getString("nome"));
				usuario.setCpf(result.getString("cpf"));
				usuario.setSenha(result.getString("senha"));
				
				if (result.getString("foto_perfil") != null) {
					usuario.setFotoPerfil(result.getString("foto_perfil"));					
				}
				
				comentario.setUsuario(usuario);
				comentario.setPost(null);
				
				comentarios.add(comentario);
			}
			result.close();
			comando.close();

			return comentarios;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
