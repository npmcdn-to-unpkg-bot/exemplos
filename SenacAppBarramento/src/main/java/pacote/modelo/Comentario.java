package pacote.modelo;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "comentario")
@XmlRootElement
public class Comentario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "inc")
	@GenericGenerator(name = "inc", strategy = "increment")
	@Column(name = "comentario_id")
	public int comentarioId;

	@ManyToOne
	@JoinColumn(name = "usuario_id")
	public Usuario usuario;

	@ManyToOne
	@JoinColumn(name = "post_id")
	public Post post;

	public String texto;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_do_comentario")
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
	public String toString() {
		return "Comentario [usuario=" + usuario.getNome() + ", post=" + post.getTitulo() + ", texto=" + texto + "]";
	}

}
