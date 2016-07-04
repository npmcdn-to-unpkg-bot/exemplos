package pacote.java8;

import java.util.List;
import java.util.function.Consumer;

public class ForEach {
	public static void main(String[] args) {
		List<Pergunta> perguntas = Teste.todos();

		// lista forEach
		perguntas.forEach(new Consumer<Pergunta>() {
			@Override
			public void accept(Pergunta t) {
				System.out.println(t.getTitulo());
			}
		});

		perguntas.forEach(p -> System.out.println(p.getTitulo()));
	}
}
