package pacote.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamMap {
	public static void main(String[] args) {
		List<Pergunta> perguntas = Teste.todos();

		// Dado o nome do autor ele me da a lista de todas as perguntas daqle
		// autor, neste caso ele lista todas as perguntas de um autor
		Map<String, List<Pergunta>> mapa = perguntas.stream().collect(Collectors.groupingBy(Pergunta::getAutor));

		Map<String, List<Pergunta>> mapa3 = new HashMap<String, List<Pergunta>>();
		for (Pergunta pergunta : perguntas) {
			if (mapa3.containsKey(pergunta.getAutor())) {
				mapa3.get(pergunta.getAutor()).add(pergunta);
			} else {
				mapa3.put(pergunta.getAutor(), new ArrayList<>());
				mapa3.get(pergunta.getAutor()).add(pergunta);
			}
		}

		// ######### LISTA
		mapa.forEach((String t, List<Pergunta> u) -> {
			System.out.println("Autor: " + t);
			System.out.println("Perguntas: ");
			u.forEach(p -> System.out.println(p.getTitulo()));
		});

		mapa3.forEach((String t, List<Pergunta> u) -> {
			System.out.println("Autor: " + t);
			System.out.println("Perguntas: ");
			u.forEach(p -> System.out.println(p.getTitulo()));
		});
	}
}
