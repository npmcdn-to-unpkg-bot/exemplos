package pacote.modelo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PostComentario implements Serializable {
	private static final long serialVersionUID = 1L;

	private int postId;
	private Usuario usuario;
	private Calendar dataDaPostagem;
	private String imagem;
	private String titulo;
	private String texto;
	private String cidade;
	private String unidade;
	private String area;
	private List<Comentario> comentarios;

	public void adicionarPost(Post post, List<Comentario> comentarios) {
		this.postId = post.getPostId();
		this.usuario = post.getUsuario();
		this.dataDaPostagem = post.getDataDaPostagem();
		this.imagem = post.getImagem();
		this.titulo = post.getTitulo();
		this.texto = post.getTexto();
		this.cidade = post.getCidade();
		this.unidade = post.getUnidade();
		this.area = post.getArea();
		this.comentarios = comentarios;
	}


	public List<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

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
				+ ", titulo=" + titulo + ", texto=" + texto + "] " + comentarios;
	}

}
