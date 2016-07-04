package pacote.test;

import java.util.Calendar;

import pacote.cdi.ConnectionFactoryProduces;
import pacote.modelo.Atendimento;
import pacote.modelo.Status;
import pacote.repository.AtendimentoJDBC;

public class TestaSaidaAtendidoJDBC {
	public static void main(String[] args) {
		AtendimentoJDBC jdbc = new AtendimentoJDBC();
		
		Atendimento atendimento = jdbc.buscarPorId(7);
		System.out.println(atendimento);

		atendimento.setStatus(Status.ATENDIDO);
		atendimento.setHoraSaida(Calendar.getInstance());
		
		AtendimentoJDBC jdbc2 = new AtendimentoJDBC();
		jdbc2.atualizar(atendimento);
	}
}
