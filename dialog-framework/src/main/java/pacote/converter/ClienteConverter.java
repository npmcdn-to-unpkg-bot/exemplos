package pacote.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import pacote.model.Cliente;
import pacote.repository.Clientes;

@Named
public class ClienteConverter implements Converter {

	@Inject
	private Clientes clientes;

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {

		if (value != null && !value.equals("")) {
			return clientes.porId(new Long(value));
		}

		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {

		if (arg2 != null) {
			Cliente cliente = (Cliente) arg2;
			if (cliente.getId() != null) {
				return cliente.getId().toString();
			}
		}
		return null;
	}

}
