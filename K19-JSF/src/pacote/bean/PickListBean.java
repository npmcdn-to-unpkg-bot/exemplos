package pacote.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.model.DualListModel;

import pacote.modelo.Curso;

@Named
@ViewScoped
public class PickListBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private List<Curso> source;
	private DualListModel<Curso> cursos;

	public PickListBean() {
		source = new ArrayList<>();
		source.add(new Curso(1L, "K01", "Lógica de programação", "http://www.k19.com.br/css/img/k01-logo-small.png"));
		source.add(new Curso(2L, "K03", "Java é top", "http://www.k19.com.br/css/img/k02-logo-small.png"));
		source.add(new Curso(3L, "K04", "C# é bosta", "http://www.k19.com.br/css/img/k03-logo-small.png"));
		source.add(new Curso(4L, "K05", "PHP an?", "http://www.k19.com.br/css/img/k31-logo-small.png"));
		source.add(new Curso(5L, "K04", "Python Kaue kkk", "http://www.k19.com.br/css/img/k12-logo-small.png"));
		source.add(new Curso(5L, "K54", "Python Andrey", "http://www.k19.com.br/css/img/k52-logo-small.png"));
		source.add(new Curso(5L, "K65", "Android", "http://www.k19.com.br/css/img/k51-logo-small.png"));
		source.add(new Curso(5L, "K76", "Elton john", "http://www.k19.com.br/css/img/k41-logo-small.png"));
		source.add(new Curso(5L, "K87", "Gabriel DotNET", "http://www.k19.com.br/css/img/k32-logo-small.png"));

		List<Curso> target = new ArrayList<>();

		cursos = new DualListModel<>(source, target);
	}

	public void onTranfer() {

	}

	public DualListModel<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(DualListModel<Curso> cursos) {
		this.cursos = cursos;
	}

}
