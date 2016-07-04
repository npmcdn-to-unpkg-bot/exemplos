package pacote.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;

import pacote.modelo.CPF;

@Named
public class ConversorDeCPF implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {

		if (value == null) {
			throw new RuntimeException("Dados incorretos");
		}

		String partesDoCPF[] = value.split("-");
		Long numeroDeIdentificacao = Long.parseLong(partesDoCPF[0]);
		Long primeiroDigitoVerificador = Long.parseLong(partesDoCPF[1].substring(0, 1));
		Long segundoDigitoVerificador = Long.parseLong(partesDoCPF[1].substring(1, 2));

		CPF cpf = new CPF();
		cpf.setNumeroDeIdentificacao(numeroDeIdentificacao);
		cpf.setPrimeiroDigitoVerificador(primeiroDigitoVerificador);
		cpf.setSegundoDigitoVerificador(segundoDigitoVerificador);
		return cpf;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		CPF cpf = (CPF) value;
		return cpf.getNumeroDeIdentificacao() + "-TESTE" + cpf.getPrimeiroDigitoVerificador()
				+ cpf.getSegundoDigitoVerificador();
	}

}
