package pacote.test;

import java.util.Calendar;

import pacote.cdi.ConnectionFactoryProduces;
import pacote.modelo.Atendimento;
import pacote.modelo.Status;
import pacote.repository.AtendimentoJDBC;

public class TestaConfirmaAtendimentoJDBC {
	public static void main(String[] args) {
		AtendimentoJDBC jdbc = new AtendimentoJDBC();

		Atendimento atendimento = jdbc.buscarPorId(7);
		System.out.println(atendimento);

		atendimento.setHoraAtendimento(Calendar.getInstance());
		atendimento.setStatus(Status.NA_MESA);
		atendimento.setMesa(7);
		
		AtendimentoJDBC jdbc2 = new AtendimentoJDBC();
		jdbc2.atualizar(atendimento);
	}
}
