package ms.senac.br.appsenac.model;

import java.io.Serializable;
import java.util.Calendar;

public class Comentario implements Serializable {
	private static final long serialVersionUID = 1L;

	private int comentarioId;
	private Usuario usuario;
	private Post post;
	private String texto;
	private Calendar dataDoComentario;

	public Calendar getDataDoComentario() {
		return dataDoComentario;
	}

	public void setDataDoComentario(Calendar dataDoComentario) {
		this.dataDoComentario = dataDoComentario;
	}

	public int getComentarioId() {
		return comentarioId;
	}

	public void setComentarioId(int comentarioId) {
		this.comentarioId = comentarioId;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Comentario that = (Comentario) o;

		return comentarioId == that.comentarioId;

	}

	@Override
	public int hashCode() {
		return comentarioId;
	}

	@Override
	public String toString() {
		return "Comentario [usuario=" + usuario.getNome() + ", post=" + post.getTitulo() + ", texto=" + texto + "]";
	}

}
