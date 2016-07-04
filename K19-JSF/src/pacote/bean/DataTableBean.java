package pacote.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import pacote.modelo.Curso;

@Named
@ViewScoped
public class DataTableBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private List<Curso> cursos;
	private List<Curso> cursosSelecionados;
	private List<Curso> cursosFiltrados;

	public DataTableBean() {
		cursos = new ArrayList<>();
		cursos.add(new Curso(1L, "K01", "Lógica de programação", "http://www.k19.com.br/css/img/k01-logo-small.png"));
		cursos.add(new Curso(2L, "K03", "Java é top", "http://www.k19.com.br/css/img/k02-logo-small.png"));
		cursos.add(new Curso(3L, "K04", "C# é bosta", "http://www.k19.com.br/css/img/k03-logo-small.png"));
		cursos.add(new Curso(4L, "K05", "PHP an?", "http://www.k19.com.br/css/img/k31-logo-small.png"));
		cursos.add(new Curso(5L, "K04", "Python Kaue kkk", "http://www.k19.com.br/css/img/k12-logo-small.png"));
		cursos.add(new Curso(5L, "K54", "Python Andrey", "http://www.k19.com.br/css/img/k52-logo-small.png"));
		cursos.add(new Curso(5L, "K65", "Android", "http://www.k19.com.br/css/img/k51-logo-small.png"));
		cursos.add(new Curso(5L, "K76", "Elton john", "http://www.k19.com.br/css/img/k41-logo-small.png"));
		cursos.add(new Curso(5L, "K87", "Gabriel DotNET", "http://www.k19.com.br/css/img/k32-logo-small.png"));
	}

	public List<Curso> getCursos() {
		return cursos;
	}

	public List<Curso> getCursosSelecionados() {
		return cursosSelecionados;
	}

	public void setCursosSelecionados(List<Curso> cursosSelecionados) {
		this.cursosSelecionados = cursosSelecionados;
	}

	public List<Curso> getCursosFiltrados() {
		return cursosFiltrados;
	}

	public void setCursosFiltrados(List<Curso> cursosFiltrados) {
		this.cursosFiltrados = cursosFiltrados;
	}

	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}

}
