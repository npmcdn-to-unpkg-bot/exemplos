package pacote.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import pacote.modelo.Instrutor;

@Named
@ViewScoped
public class InstrutorBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private List<Instrutor> instrutores = new ArrayList<>();

	public InstrutorBean() {
		Instrutor rafael = new Instrutor();
		rafael.setNome("Rafael Cosentino");
		rafael.setDataNascimento("30/10/1985");

		Instrutor marcelo = new Instrutor();
		marcelo.setNome("Marcelo Martins");
		marcelo.setDataNascimento("02/04/1985");

		this.instrutores.add(rafael);
		this.instrutores.add(marcelo);
	}

	public List<Instrutor> getInstrutores() {
		return instrutores;
	}

	public void setInstrutores(List<Instrutor> instrutores) {
		this.instrutores = instrutores;
	}
}
