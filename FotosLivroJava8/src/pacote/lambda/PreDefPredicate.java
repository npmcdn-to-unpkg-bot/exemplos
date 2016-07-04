package pacote.lambda;

import java.util.function.IntPredicate;

public class PreDefPredicate {

	public static void main(String[] args) {
		// Predicate : n é potencia de 2
		IntPredicate potDois = value -> (Math.log(value) / Math.log(2)) % 2 == 0;
		// Predicate: n > 10
		IntPredicate cemOuMenos = (n) -> n <= 100;
		//Aplicação separada dos Predicados
		System.out.println(potDois.test(10));
		System.out.println(potDois.test(1024));
		System.out.println(cemOuMenos.test(10));
		System.out.println(cemOuMenos.test(1024));
	}
}
