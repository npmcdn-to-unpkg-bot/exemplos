package pacote.post.manipulando.arquivos;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CargaHorariaServidores {
	private static final Pattern HORAS = Pattern.compile(".*([0-9]{2}(,[0-9])? HORAS SEMANAIS|DEDICACAO EXCLUSIVA).*");

	public static void main(String[] args) throws Exception {
		Path caminho = Paths.get(System.getProperty("user.home"), "Documentos/201402_Servidores/20140228_Cadastro.csv");
		Stream<String> linhas = Files.lines(caminho, StandardCharsets.ISO_8859_1);

		linhas.map(linha -> {
			Matcher matcher = HORAS.matcher(linha);
			return matcher.matches() ? matcher.group(1) : "";
		}).filter(horaSemana -> !horaSemana.isEmpty()).sorted()
				.collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
				.forEach((s, l) -> {
					System.out.println(l + " \t\t " + s);
				});
	}
}
