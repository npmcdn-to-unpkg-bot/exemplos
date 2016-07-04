package pacote.stream;

import java.util.List;

public class TestEscopo {
	public static void main(String[] args) {
		int i = 0;
		List<Funcionario> list = FuncionarioList.criarListaFuncionario();
		
		list.forEach(f -> {
			int x = i;
			x++;
			System.out.println(x);
		});
		
	}
}
