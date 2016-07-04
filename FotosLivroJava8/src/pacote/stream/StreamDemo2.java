package pacote.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamDemo2 {
	public static void main(String[] args) {
		List<Double> lista = new ArrayList<>();
		for (int i = 0; i <= 180; i++) {
			lista.add(Math.sin(Math.toRadians(i)));
		}
		System.out.println(lista);
		// Define um valor limite
		double LIMITE = 0.95;
		// obtem stream
		Stream<Double> stream1 = lista.stream();
		// APlica filtro com expressao lambda
		Stream<Double> streamFiltrado = stream1.filter(e -> e > LIMITE);
		// forEach
		System.out.println("Maiores que 0.95");
		streamFiltrado.forEach(e -> System.out.println("\t" + e));
		// obtem novamente stream
		Stream<Double> stream2 = lista.stream();
		// Aplica filtro e add em outra coleção
		List<Double> menores = stream2.filter(e -> e <= 0.2).collect(Collectors.toList());
		// Verifica tipo de objeto retornado por COllectors
		System.out.println(menores.getClass().getName());
		// forEach
		System.out.println("Menores que 0.2");
		menores.forEach(e -> System.out.println("\t" + e));
	}
}
