package pacote.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import pacote.modelo.Lancamento;
import pacote.repository.LancamentoRepository;

//@FacesConverter(forClass = Lancamento.class)
@Named
public class LancamentosConverter implements Converter {

	@Inject
	private LancamentoRepository repository;

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		Lancamento lancamento = null;

		if (arg2 != null && !arg2.equals("")) {
			lancamento = repository.porId(new Long(arg2));
		}

		return lancamento;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		if (arg2 != null) {
			Lancamento lancamento = (Lancamento) arg2;
			return lancamento.getId() != null ? lancamento.getId().toString() : null;
		}
		return null;
	}
}
