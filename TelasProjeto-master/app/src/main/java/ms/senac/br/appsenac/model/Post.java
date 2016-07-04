package ms.senac.br.appsenac.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Post implements Serializable {
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
	
	public Calendar  getDataDaPostagem() {
		return dataDaPostagem;
	}
	
	public void setDataDaPostagem(Calendar  dataDaPostagem) {
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
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Post post = (Post) o;

		return postId == post.postId;

	}

	@Override
	public int hashCode() {
		return postId;
	}

	@Override
	public String toString() {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		return "Post usuario=" + usuario.getNome() + ", dataDaPostagem=" + format.format(dataDaPostagem.getTime())
				+ ", titulo=" + titulo + ", texto=" + texto + "]";
	}

}
