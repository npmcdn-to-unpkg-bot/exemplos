package pacote.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class PrimefacesBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private String value;
	private List<String> values;

	private int placarTimeA;
	private int placarTimeB;

	public List<String> completeMethod(String query) {
		List<String> lista = new ArrayList<>();

		for (int i = 0; i < 10; i++) {
			lista.add(query + Math.random());
		}
		return lista;
	}

	public void update() {
		if (Math.random() > 0.5) {
			this.placarTimeA++;
		} else {
			this.placarTimeB++;
		}
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public List<String> getValues() {
		return values;
	}

	public void setValues(List<String> values) {
		this.values = values;
	}

	public int getPlacarTimeA() {
		return placarTimeA;
	}

	public int getPlacarTimeB() {
		return placarTimeB;
	}
}
