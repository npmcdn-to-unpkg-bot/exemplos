package pacote.data;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NovaLocacao {
	public static void main(String[] args) {
		Scanner entrada = new Scanner(System.in);
		List<Filme> filmesAlugados = new ArrayList<>();

		System.out.println("Escolha:");
		FilmeRepositorio.todos().forEach(f -> {
			String dataFormatada = f.getDataLancamento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
			System.out.printf("%-30s %s\n", f.getNome(), dataFormatada);
		});

		while (entrada.hasNext()) {
			filmesAlugados.add(FilmeRepositorio.get(entrada.nextInt()));
		}
		
		Locacao locacao = new Locacao(filmesAlugados, new Cliente("Antonio Cesar"));
		locacao.imprimirRecibo();
	}
}
