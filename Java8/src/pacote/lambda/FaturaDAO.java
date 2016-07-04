package pacote.lambda;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class FaturaDAO {

	public static List<Fatura> buscarFaturasVencidas() {
		// Aqui eu acessaria o banco de dados
		// data de hoje menos (minusDays) 3 dias
		Fatura f1 = new Fatura("joao@gmal.com", new BigDecimal("350.00"), LocalDate.now().minusDays(3));
		Fatura f2 = new Fatura("maria@gmal.com", new BigDecimal("150.00"), LocalDate.now().minusMonths(2).minusDays(2));
		Fatura f3 = new Fatura("jose@gmal.com", new BigDecimal("200.00"), LocalDate.now().minusDays(5));

		return Arrays.asList(f1, f2, f3);
	}
}
