package pacote.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Streams {
	public static void main(String[] args) {
		List<Pergunta> perguntas = Teste.todos();

		Stream<Pergunta> perguntaStream = perguntas.stream().filter(new Predicate<Pergunta>() {
			@Override
			public boolean test(Pergunta t) {
				return t.getAutor().startsWith("R");
			}
		});
		perguntaStream.forEach(System.out::println);

		// Dada uma Pergunta "p" eu só quero ficar com as perguntas que os nomes
		// dos autores começam com a letra R
		perguntas.stream().filter(p -> p.getAutor().startsWith("R")).filter(p -> p.getAutor().isEmpty())
				.forEach(System.out::println);

		// Eu quero filtrar como acima, e ainda pegar quantas respostas são
		perguntas.stream().filter(p -> p.getAutor().startsWith("R")).map(p -> p.getRespostas().size())
				.forEach(System.out::println);

		List<Pergunta> ps = perguntas.stream().filter(p -> p.getAutor().startsWith("R")).collect(Collectors.toList());
		System.out.println(perguntas);
		System.out.println(ps);
	}
}
