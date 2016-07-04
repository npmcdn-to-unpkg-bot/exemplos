package pacote.modelo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "post")
@XmlRootElement
public class Post implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "inc")
	@GenericGenerator(name = "inc", strategy = "increment")
	@Column(name = "post_id")
	public int postId;

	@ManyToOne
	@JoinColumn(name = "usuario_id")
	public Usuario usuario;

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataDaPostagem;

	@Lob
	public String imagem;
	public String titulo;
	public String texto;
	public String cidade;
	public String unidade;
	public String area;

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public String getCidade() {
		return cidade;
	}

	public Calendar getDataDaPostagem() {
		return dataDaPostagem;
	}

	public void setDataDaPostagem(Calendar dataDaPostagem) {
		this.dataDaPostagem = dataDaPostagem;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUnidade() {
		return unidade;
	}

	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	@Override
	public String toString() {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		return "Post usuario=" + usuario.getNome() + ", dataDaPostagem=" + format.format(dataDaPostagem.getTime())
				+ ", titulo=" + titulo + ", texto=" + texto + "]";
	}

}
