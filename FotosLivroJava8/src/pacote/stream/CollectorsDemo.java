package pacote.stream;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CollectorsDemo {
	public static void main(String[] args) {
		List<Funcionario> func = FuncionarioList.criarListaFuncionario();

		// Obtem a lista de nomes [map + reduce]
		List<String> nomes = func.stream().map(Funcionario::getNome).collect(Collectors.toList());
		nomes.forEach(System.out::println);

		// Concatena String com nomes esspecificos [filter+map+reduce]
		String juncao = func.stream().filter(f -> f.getNome().startsWith("A")).map(Funcionario::getNome)
				.collect(Collectors.joining("##"));
		System.out.println("Nomes: " + juncao);

		// Soma salario dos funcionarios
		double salario = func.stream().collect(Collectors.summingDouble(f -> f.getSalario().doubleValue()));
		System.out.println("Total: " + salario);

		// Agrupa funcionarios por departamento [map+reduce]
		Map<String, List<Funcionario>> porDepartamento = func.stream()
				.collect(Collectors.groupingBy(Funcionario::getDepartamento));
		porDepartamento.forEach((String t, List<Funcionario> u) -> {
			System.out.println(t);
			u.forEach(System.out::println);
		});

		int is = 0;
		// Subtotal de salarios por departamento [map+reduce]
		Map<String, Double> totalPorDepartamento = func.stream().collect(Collectors.groupingBy(f -> f.getDepartamento(),
				Collectors.summingDouble(f -> f.getSalario().doubleValue())));
		totalPorDepartamento.forEach((String t, Double u) -> {
			System.out.println(t + " = " + u);
			int ghi = is;
		});

		func.stream()
				.collect(Collectors.groupingBy(Funcionario::getNome,
						Collectors.summingDouble(f -> f.getSalario().doubleValue())))
				.forEach((f, s) -> System.out.println("Func: " + f + "\nSalario: " + s));
	}
}
