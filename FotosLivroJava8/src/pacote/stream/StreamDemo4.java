package pacote.stream;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

public class StreamDemo4 {
	public static void main(String[] args) {
		List<Double> listaX = new ArrayList<>();
		for (double x = 0; x < 10; x += 0.5) {
			listaX.add(x);
		}
		System.out.println("X: " + listaX);
		// (1) Totalização via navegação com iterator
		Iterator<Double> iterator = listaX.iterator();
		double totalX = 0;
		while (iterator.hasNext()) {
			totalX += iterator.next();
		}
		System.out.println("Total(X): " + totalX);

		// (2) Totalização via navegação com forEach
		Somador somador = new Somador();
		BigDecimal soma = BigDecimal.ZERO;
		listaX.forEach(x -> soma.add(new BigDecimal(x)));
		System.out.println("Total(X): " + soma);

		// (3) Totalização via redução
		totalX = listaX.stream().mapToDouble(e -> e).sum(); // Redução
		System.out.println("Total(X): " + totalX);

		// Mapeamento restrito de X em Y
		List<Double> listaY = listaX.stream().filter(x -> x > 3 && x < 7) // Filtra
				.map(x -> x * x - 10 * x + 24) // Mapeamento
				.collect(Collectors.toList()); // coleta
		System.out.println("Yrestrito: " + listaY);

		// Contagem e maximo via redução predefinida
		System.out.printf("Num(Yrestrito): %d valores\n", listaY.stream().count());
		Optional<Double> maxY = listaY.stream().max(Double::compare); // redução
		System.out.println("Max(Yrestrito): " + maxY.get());

		// (4) Totalização via redução definida
		double totalY = listaY.stream().reduce(0.0, (acc, e) -> acc + e);
		System.out.println("Total(Yrestrito): " + totalY);
	}
}
