package pacote.java8.MODELO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class TesteJava7 {
	public static void main(String[] args) {
		List<Pergunta> perguntas = PerguntaRepositorio.todos();

		Map<Autor, List<Pergunta>> mapaPerguntasDoAutor = new HashMap<>();
		for (Pergunta pergunta : perguntas) {
			if (mapaPerguntasDoAutor.containsKey(pergunta.getAutor())) {
				mapaPerguntasDoAutor.get(pergunta.getAutor()).add(pergunta);
			} else {
				mapaPerguntasDoAutor.put(pergunta.getAutor(), new ArrayList<>());
				mapaPerguntasDoAutor.get(pergunta.getAutor()).add(pergunta);
			}
		}

		Set<Autor> keyAutor = new HashSet<>(mapaPerguntasDoAutor.keySet());
		for (Autor autor : keyAutor) {
			List<Pergunta> perguntas2 = mapaPerguntasDoAutor.get(autor);
			for (Pergunta pergunta : perguntas2) {
				System.out.println(pergunta);
			}
		}

		// O usuario pesquisa as perguntas de determinado autor
		Scanner entrada = new Scanner(System.in);
		while (entrada.hasNextLine()) {
			String nomeString = entrada.nextLine();
			Autor nomeAutor = new Autor();
			nomeAutor.setNome(nomeString);
			if (mapaPerguntasDoAutor.containsKey(nomeAutor)) {
				List<Pergunta> perguntasDoAutor = mapaPerguntasDoAutor.get(nomeAutor);
				System.out.println("Esse autor fez " + perguntasDoAutor.size() + " perguntas");
				for (Pergunta pergunta : perguntasDoAutor) {
					System.out.println(pergunta);
				}
			}
		}
		entrada.close();
	}
}