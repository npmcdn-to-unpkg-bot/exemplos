package pacote.java8;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Sort {
	public static void main(String[] args) {
		List<Pergunta> perguntas = Teste.todos();

		// Sem ordenar
		// System.out.println(perguntas);

		// ordenar perguntas por numero de respostas
		perguntas.sort(new Comparator<Pergunta>() {
			@Override
			public int compare(Pergunta p1, Pergunta p2) {
				return Integer.compare(p1.getRespostas().size(), p2.getRespostas().size());
			}
		});
		// System.out.println(perguntas);

		// Ordem por quantidade de respostas
		perguntas.sort((p1, p2) -> Integer.compare(p1.getRespostas().size(), p2.getRespostas().size()));
		perguntas.sort(Comparator.comparing(pp -> pp.getRespostas().size()));
		System.out.println(perguntas);

		// Ordem alfabetica
		perguntas.sort(Comparator.comparing(pp -> pp.getAutor()));
		// Dada a pergunta chama o getAutor
		perguntas.sort(Comparator.comparing(Pergunta::getAutor));
		System.out.println(perguntas);

		// Ordem inversa
		Collections.reverse(perguntas);
		System.out.println(perguntas);
	}
}
