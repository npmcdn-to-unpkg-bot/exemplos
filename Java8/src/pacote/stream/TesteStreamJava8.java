package pacote.stream;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class TesteStreamJava8 {
	public static List<Conta> listaDeContas;

	public static void main(String[] args) {
		Conta conta1 = new Conta(65453, new BigDecimal("1"), "Jorge");
		Conta conta2 = new Conta(42156, new BigDecimal("2"), "Luiz");
		Conta conta3 = new Conta(88951, new BigDecimal("3"), "Lomes");
		Conta conta4 = new Conta(21583, new BigDecimal("4"), "Silva");
		Conta conta5 = new Conta(63954, new BigDecimal("5"), "João");

		listaDeContas = Arrays.asList(conta1, conta2, conta3, conta4, conta5);

		soma();
	}

	private static void impressao() {
		listaDeContas.forEach(conta -> {
			System.out.println(conta.getNumeroDaConta());
		});
	}

	private static void ordenacao() {
		listaDeContas.stream().sorted((c1, c2) -> Integer.compare(c2.getNumeroDaConta(), c1.getNumeroDaConta()))
				.forEach(c -> System.out.println(c.getNumeroDaConta()));
	}

	private static void busca() {
		listaDeContas.stream().filter(c -> c.getTitularDaConta().startsWith("S"))
				.forEach(c -> System.out.println(c.getTitularDaConta()));
	}

	private static void soma() {
		Double soma = listaDeContas.stream().filter(c -> c.getTitularDaConta().startsWith("L"))
				.mapToDouble(c -> c.getValor().doubleValue()).sum();
		System.out.println(soma);
	}

}
