package pacote.java8.MODELO;

import java.util.ArrayList;
import java.util.List;

public class Pergunta {
	private String titulo;
	private String conteudo;
	private Autor autor;
	private List<Resposta> respostas = new ArrayList<>();

	public Pergunta() {
	}

	public Pergunta(String titulo, Autor autor, List<Resposta> respostas) {
		this.titulo = titulo;
		this.autor = autor;
		this.respostas = respostas;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

	public List<Resposta> getRespostas() {
		return respostas;
	}

	public void setRespostas(List<Resposta> respostas) {
		this.respostas = respostas;
	}

	@Override
	public String toString() {
		return "Pergunta [titulo=" + titulo + ", Autor=" + autor.getNome() + "]";
	}
}
