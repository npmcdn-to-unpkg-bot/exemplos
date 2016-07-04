package pacote.java8.MODELO;

import java.util.Arrays;
import java.util.List;

public class PerguntaRepositorio {

	public static List<Pergunta> todos() {

		Pergunta p1 = new Pergunta("Java ou C#?", new Autor("Antonio", 19),
				Arrays.asList(new Resposta("Java"), new Resposta("C#"), new Resposta("Java"), new Resposta("Java")));
		Pergunta p2 = new Pergunta("Java ou C#?", new Autor("Antonio", 19),
				Arrays.asList(new Resposta("C#"), new Resposta("C#")));
		Pergunta p3 = new Pergunta("Java ou C#?", new Autor("Felipe", 19), Arrays.asList(new Resposta("Java")));
		Pergunta p4 = new Pergunta("Java ou C#?", new Autor("Andrey", 19), Arrays.asList(new Resposta("Java")));
		Pergunta p5 = new Pergunta("Java ou C#?", new Autor("Andrey", 19), Arrays.asList(new Resposta("Java")));
		Pergunta p6 = new Pergunta("Java ou C#?", new Autor("Andrey", 19), Arrays.asList(new Resposta("Java")));
		Pergunta p7 = new Pergunta("Java ou C#?", new Autor("Andrey", 19), Arrays.asList(new Resposta("Java")));
		Pergunta p8 = new Pergunta("Java ou C#?", new Autor("Felipe", 19), Arrays.asList(new Resposta("Java")));
		Pergunta p9 = new Pergunta("Java ou C#?", new Autor("Felipe", 19), Arrays.asList(new Resposta("Java")));

		return Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9);
	}
}
