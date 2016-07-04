package pacote.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;
import java.util.Scanner;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

public class CalculadoraMediaGols {

	public static void main(String[] args) {
		System.out.println("#### Média de gols ####");
		System.out.println();

		String nome;
		List<Integer> golsPorPartida;

		try (Scanner entrada = new Scanner(System.in)) {
			System.out.print("Informe o nome do jogador: ");
			nome = entrada.nextLine();

			String continuar;
			System.out.print("Você gostaria de adicionar gols a este jogador? (s/n): ");
			continuar = entrada.nextLine();

			int partida = 1;
			golsPorPartida = new ArrayList<>();

			while ("s".equalsIgnoreCase(continuar)) {
				System.out.printf("Quantos gols %s fez na partida %d: ", nome, partida);
				int golsNaPartida = entrada.nextInt();
				golsPorPartida.add(golsNaPartida);

				partida++;

				System.out.print("Deseja continuar (s/n): ");
				continuar = entrada.next();
			}
		}

		imprimirMediaDeGols(nome, golsPorPartida);
	}

	private static void imprimirMediaDeGols(String nome, List<Integer> golsPorPartida) {
		/*
		 * double totalGols = 0; for (Integer golsNaPartida : golsPorPartida) {
		 * totalGols += golsNaPartida; }
		 * 
		 * double media = 0; if (!golsPorPartida.isEmpty()) { media = totalGols
		 * / golsPorPartida.size(); }
		 */

		Integer x = 3;
		double y = x.doubleValue();

		// stream() devolve um Stream que é um Iterator, sabe percorrer os
		// elementos de uma lista
		Stream<Integer> stream = golsPorPartida.stream();
		// Converto de um Stream generico para um mais especifico
		// Preciso pegar o Double dos meus integer, vamos usar referncia a
		// metodo, para cada elemento do meu Stream
		// Eu quero chamar o metodo doubleValue
		DoubleStream doubleStream = stream.mapToDouble(Integer::doubleValue);
		// OptionalDouble vai nos ajudar a evitar o IF()
		OptionalDouble optionalDouble = doubleStream.average();
		// Isso que dizer me retorna o valor da media ou o valor 0.0, ele vai
		// calcular e se não tiver nada ele vai retornar 0
		double media = optionalDouble.orElse(0.0);

		// Metodo em cascata
		double media2 = golsPorPartida.stream().mapToDouble(Integer::doubleValue).average().orElse(0.0);

		System.out.printf("\nO %s fez uma média de %.2f gols por jogo", nome, media2);
	}
}