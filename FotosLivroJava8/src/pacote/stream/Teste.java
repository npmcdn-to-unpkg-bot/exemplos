package pacote.stream;

import java.util.List;
import java.util.stream.Collectors;

public class Teste {
	public static void main(String[] args) {
		List<Funcionario> func = FuncionarioList.criarListaFuncionario();
		double salario = func.stream().collect(Collectors.summingDouble(f -> f.getSalario().doubleValue()));
		double salario2 = func.stream().mapToDouble(f -> f.getSalario().doubleValue()).sum();
		
	}
}
