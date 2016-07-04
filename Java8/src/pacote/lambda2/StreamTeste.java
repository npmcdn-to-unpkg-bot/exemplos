package pacote.lambda2;

import java.util.Arrays;
import java.util.List;

public class StreamTeste {
	public static void main(String[] args) {
		// Antes java 8
		List<String> brics = Arrays.asList("Brasil", "Rússia", "Índia", "China", "Africa do Sul");
		for (String pais : brics) {
			System.out.println(pais);
		}

		brics.forEach(pais -> System.out.println(pais));
		brics.forEach((pais) -> System.out.println(pais));
		brics.forEach((String pais) -> System.out.println(pais));
		
		// Metodo reference
		brics.forEach(System.out::println);
	}
}
