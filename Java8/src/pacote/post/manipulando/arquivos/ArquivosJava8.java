package pacote.post.manipulando.arquivos;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ArquivosJava8 {
	private static final Pattern HORAS = Pattern.compile(".*([0-9]{2}(,[0-9])? HORAS SEMANAIS|DEDICACAO EXCLUSIVA).*");

	public static void main(String[] args) throws Exception {
		Path caminho = Paths.get(System.getProperty("user.home"), "Documentos/201402_Servidores/20140228_Cadastro.csv");
		Stream<String> linhas = Files.lines(caminho, StandardCharsets.ISO_8859_1);
		// Stream::map aplica a cada item do stream um lambda passado como
		// parametro retornando um novo stream com os novos itens, neste caso
		// extrai o que intereça da String
		Stream<String> horasSemanais = linhas.map(linha -> {
			Matcher matcher = HORAS.matcher(linha);
			return matcher.matches() ? matcher.group(1) : "";
		});
		// Stream::filter permanece apenas os itens que o lambda retornou TRUE
		// em um novo stream, neste caso sem as linhas vazias
		Stream<String> horasSemanaisNaoVazias = horasSemanais.filter(horaSemana -> !horaSemana.isEmpty());
		// Ordena as linhas
		Stream<String> horasSemanaisOrdenadas = horasSemanaisNaoVazias.sorted();
		// Agrupa contatando quantas ocorrencias de cada tipo
		// Collectors::groupingBy faz agrupamentos, temos que passar dois
		// lambdas, um para montar as chaves, e outro para montar os valores do
		// MAP, neste caso o lambda que monta as chaves deve retornar a propia
		// linha que esta sendo agrupada poderiamos representar assim s -> s
		// mas..., ja o segunda deve contar as ocorrencias de linhas iguais,
		// mas ja existe um lambda para fazer contagens
		Map<String, Long> horasSemanaisAgrupadas = horasSemanaisOrdenadas
				.collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()));

		System.out.println(horasSemanaisAgrupadas);
		System.out.println("Quantidade \t\t Horas");
		horasSemanaisAgrupadas.forEach((s, l) -> {
			System.out.println(l + " \t\t " + s);
		});
	}
}
