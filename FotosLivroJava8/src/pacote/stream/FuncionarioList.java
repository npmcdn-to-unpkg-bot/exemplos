package pacote.stream;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioList {

	public static List<Funcionario> criarListaFuncionario() {
		List<Funcionario> list = new ArrayList<>();

		list.add(new Funcionario("Antonio", "A", new BigDecimal("1000.00")));
		list.add(new Funcionario("Augusto", "B", new BigDecimal("1024.00")));
		list.add(new Funcionario("Lucas", "B", new BigDecimal("678.56")));
		list.add(new Funcionario("Zuleide", "C", new BigDecimal("333.00")));
		list.add(new Funcionario("Cesar", "A", new BigDecimal("7890.50")));
		list.add(new Funcionario("Andrey", "D", new BigDecimal("4267.90")));

		return list;
	}
}
