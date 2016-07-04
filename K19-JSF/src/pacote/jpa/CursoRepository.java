package pacote.jpa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import pacote.modelo.Curso;

@Named
@ViewScoped
public class CursoRepository implements Serializable {
	private static final long serialVersionUID = 1L;

	private List<Curso> cursos = new ArrayList<>();

	public CursoRepository() {
		cursos.add(new Curso(1L, "K01", "Lógica de Programação", "http://www.k19.com.br/css/img/k01-logo-small.png"));
		cursos.add(new Curso(2L, "K02", "OO em java", "http://www.k19.com.br/css/img/k02-logo-small.png"));
		cursos.add(new Curso(3L, "K03", "C#", "http://www.k19.com.br/css/img/k03-logo-small.png"));
		cursos.add(new Curso(4L, "K04", "PHP", "http://www.k19.com.br/css/img/k21-logo-small.png"));
		cursos.add(new Curso(5L, "K05", "JAVA", "http://www.k19.com.br/css/img/k12-logo-small.png"));
		cursos.add(new Curso(6L, "K06", "SQL", "http://www.k19.com.br/css/img/k32-logo-small.png"));
	}

	public List<Curso> getCursos() {
		return cursos;
	}
}
