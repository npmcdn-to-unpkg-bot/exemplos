package tdc;

import javax.annotation.Priority;
import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Specializes;
import javax.interceptor.Interceptor;

import cdi.Frete;
import cdi.TipoFrete;

//@Alternative // Todos tem Priority.APPLICATION, por isso dar mais 1
//@Priority(Interceptor.Priority.APPLICATION + 1)
@Specializes
public class CalculadoraDeFreteAutomaticoAssincrono extends CalculadoraDeFreteAutomatico {
	// extends não pega as anotações do pai, @Specializes faz ele pegar
	@Override
	public double calcula(double valorTotal) {
		System.out.println("Calculo Automático Assincrono");
		return valorTotal * 0.10;
	}

}
