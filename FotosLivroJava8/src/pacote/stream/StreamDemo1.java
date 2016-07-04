package pacote.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class StreamDemo1 {
	public static void main(String[] args) {
		List<Integer> lista = new ArrayList<>();
		for (int i = 0; i <= 15; i++) {
			lista.add((int) Math.pow(i, 2));
		}
		// Exibe
		System.out.println(lista);
		// Obtem o Stream sequencial de tipo adequado
		Stream<Integer> stream1 = lista.stream();
		// Uso de forEach em stream com expressao lambda
		stream1.forEach(e -> System.out.print(e + ", "));
		System.out.println();
		// Alternativa compacta
		lista.stream().forEach(e -> System.out.printf("%d; ", e));
		lista.stream().forEach(new Consumer<Integer>() {
			@Override
			public void accept(Integer t) {
				
			}
		});
		System.out.println();
	}
}
