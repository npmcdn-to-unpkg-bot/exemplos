package pacote.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import pacote.modelo.Pessoa;
import pacote.repository.PessoaRepository;

//@FacesConverter(forClass = Pessoa.class)
@Named
public class PessoaConverter implements Converter {

	// ERRO NAO CONVERTER NAO SUPORTA CDI
	@Inject
	private PessoaRepository repository;

	@Override
	public Object getAsObject(FacesContext ctx, UIComponent component, String value) {
		Pessoa retorno = null;

		if (value != null && !"".equals(value) && !value.equals("Selecione")) {
			retorno = repository.porId(new Long(value));
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext ctx, UIComponent component, Object value) {
		if (value != null) {
			Pessoa p = (Pessoa) value;
			if (p.getId() != null) {
				return p.getId().toString();
			}
		}
		return null;
	}

}
