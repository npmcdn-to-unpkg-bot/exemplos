package pacote.test;

import java.util.Calendar;

import pacote.cdi.ConnectionFactoryProduces;
import pacote.modelo.Atendimento;
import pacote.modelo.Status;
import pacote.repository.AtendimentoJDBC;

public class TestaChegadaJDBC {
	public static void main(String[] args) {
		Atendimento atendimento = new Atendimento();
		atendimento.getCliente().setId(1);
		atendimento.getFuncionario().setId(1);
		atendimento.setHoraChegada(Calendar.getInstance());
		atendimento.setQuantidadePessoas(10);
		atendimento.setStatus(Status.ESPERANDO);
		
		AtendimentoJDBC jdbc = new AtendimentoJDBC();
		jdbc.persistir(atendimento);
	}
}
