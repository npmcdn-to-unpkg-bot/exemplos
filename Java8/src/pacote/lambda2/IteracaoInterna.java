package pacote.lambda2;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class IteracaoInterna {

	public static void filtro(List<String> list, Predicate<String> condicao) {
		list.stream().filter(condicao::test).forEach(System.out::println);
	}

	public static void main(String[] args) {
		List<String> brics = Arrays.asList("Brasil", "Rússia", "Índia", "China", "Africa do Sul");
		Integer i = 1;

		System.out.println("Países que iniciam com a letra B");
		filtro(brics, s -> s.startsWith("B"));

		System.out.println("");
		System.out.println("Países que terminam com a letra A");
		filtro(brics, s -> s.endsWith("a"));

		System.out.println("");
		System.out.println("Imprimi toda a lista");
		filtro(brics, s -> true);

		System.out.println("");
		System.out.println("Imprimi nenhum da lista");
		filtro(brics, s -> false);
	}
}
