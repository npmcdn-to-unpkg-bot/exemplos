package pacote.data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

public class FilmeRepositorio {
	private static Filme[] filmes = {
			new Filme("Jogos Vorazes: Em Chamas", LocalDate.of(2014, Month.MARCH, 19), new BigDecimal("6")),
			new Filme("Jobs", LocalDate.of(2014, Month.APRIL, 25), new BigDecimal("6")),
			new Filme("CDZ", LocalDate.of(2015, Month.DECEMBER, 25), new BigDecimal("6")),
			new Filme("Homem de Ferro 2", LocalDate.of(2013, Month.JANUARY, 1), new BigDecimal("6")),
			new Filme("Captão America", LocalDate.of(2016, Month.OCTOBER, 19), new BigDecimal("6")), };

	public static List<Filme> todos() {
		return Arrays.asList(filmes);
	}

	public static Filme get(int nextInt) {
		return filmes[nextInt];
	}
}
