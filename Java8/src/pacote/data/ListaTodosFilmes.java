package pacote.data;

import java.time.format.DateTimeFormatter;

public class ListaTodosFilmes {
	public static void main(String[] args) {
		System.out.printf("%-30s %s\n", "Nome:", "Data Lançamento:");

		FilmeRepositorio.todos().forEach(f -> {
			// Para formatar uma data basta chamar o metodo .formate() e passar um DateTimeFormatter indicando o formato
			String dataFormatada = f.getDataLancamento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
			System.out.printf("%-30s %s\n", f.getNome(), dataFormatada);
		});
	}
}
