package pacote.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamDemo3 {
	public static void main(String[] args) {
		List<Integer> lista = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			lista.add(i);
		}
		System.out.println(lista);
		// Obtem stream dos quadrados dos elementos
		IntStream quadrados = lista.stream().mapToInt(e -> e * e);
		quadrados.forEach(e -> System.out.println("\t" + e));
		// Cria Steam a partir de duplas de valores reais
		Stream<Dupla> streamDupplas = Stream.of(new Dupla(81.5, 1.69), new Dupla(52.5, 1.62), new Dupla(64.2, 1.66));
		// Obtem stream dos IMCs dos elementos com massa maior que 80
		DoubleStream imc = streamDupplas.filter(d -> d.valor1 > 80)
				.mapToDouble(d -> d.getValor1() / Math.pow(d.valor2, 2));
		imc.forEach(e -> System.out.println("[imc]" + e));
	}
}
