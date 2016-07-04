package pacote.java8.MODELO;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class TesteJava8 {
	public static void main(String[] args) {
		List<Pergunta> perguntas = PerguntaRepositorio.todos();

		Map<Autor, List<Pergunta>> mapaPerguntasDoAutor = perguntas.stream()
				.collect(Collectors.groupingBy(Pergunta::getAutor));

		mapaPerguntasDoAutor.forEach((Autor autor, List<Pergunta> perguntass) -> {
			perguntass.forEach(System.out::println);
		});

		// O usuario pesquisa as perguntas de determinado autor
		Scanner entrada = new Scanner(System.in);
		while (entrada.hasNextLine()) {
			String nomeString = entrada.nextLine();
			Autor nomeAutor = new Autor();
			nomeAutor.setNome(nomeString);
			if (mapaPerguntasDoAutor.containsKey(nomeAutor)) {
				List<Pergunta> perguntasDoAutor = mapaPerguntasDoAutor.get(nomeAutor);
				System.out.println("Esse autor fez " + perguntasDoAutor.size() + " perguntas");
				perguntasDoAutor.forEach(System.out::println);
			}
		}
		entrada.close();
	}
}