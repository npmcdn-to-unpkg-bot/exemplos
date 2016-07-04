package pacote.java8;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

public class Teste {
	public static void main(String[] args) {
		List<Pergunta> perguntas = todos();

		// lambda v1, v2, v3 para o sort

		// lambda para Thread

		// lambda para o sort
	}

	public static List<Pergunta> todos() {
		Pergunta p1 = new Pergunta("Qual o melhor framework web?", "Paulo",
				Arrays.asList(new Resposta("JSF"), new Resposta("V Raptor!"), new Resposta("Spring MVC")));

		Pergunta p2 = new Pergunta("Como ordenar com Java?", "Lucas", Arrays.asList(new Resposta("Use um Comparator")));

		Pergunta p3 = new Pergunta("Vai ter mais coffe break?", "Rodrigo",
				Arrays.asList(new Resposta("Sim"), new Resposta("Certamente!")));
		
		Pergunta p4 = new Pergunta("Vai ter mais coffe break?", "Rntonio",
				Arrays.asList(new Resposta("Sim"), new Resposta("Certamente!"), new Resposta("Sim"), new Resposta("Certamente!")));

		Pergunta p5 = new Pergunta("Vai", "Rntonio",
				Arrays.asList(new Resposta("Sim"), new Resposta("Certamente!"), new Resposta("Sim"), new Resposta("Certamente!")));
		
		List<Pergunta> perguntas = Arrays.asList(p1, p2, p3, p4, p5);
		return perguntas;
	}
}
