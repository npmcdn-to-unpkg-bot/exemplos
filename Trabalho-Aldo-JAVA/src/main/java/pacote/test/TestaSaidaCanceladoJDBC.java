package pacote.test;

import java.util.Calendar;

import pacote.cdi.ConnectionFactoryProduces;
import pacote.modelo.Atendimento;
import pacote.modelo.Status;
import pacote.repository.AtendimentoJDBC;

public class TestaSaidaCanceladoJDBC {
	public static void main(String[] args) {
		AtendimentoJDBC jdbc = new AtendimentoJDBC();
		
		Atendimento atendimento = jdbc.buscarPorId(5);
		System.out.println(atendimento);

		atendimento.setStatus(Status.CANCELADO);
		atendimento.setHoraSaida(Calendar.getInstance());
		
		AtendimentoJDBC jdbc2 = new AtendimentoJDBC();
		jdbc2.atualizar(atendimento);
	}
}
