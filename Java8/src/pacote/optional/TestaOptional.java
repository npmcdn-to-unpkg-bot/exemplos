package pacote.optional;

import java.math.BigDecimal;
import java.util.Optional;

public class TestaOptional {
	public static void main(String[] args) {
		Seguro seguro = new Seguro("Total com franquia reduzida", new BigDecimal("600"));
		// Optional seria como uma caixa que vc coloca algo la dentro, no caso
		// um Seguro, pode ser que exista,
		// ou não, mas a caixa vai sempre existir
		Optional<Seguro> seguroOptional = Optional.of(seguro);
		// Dentro da minha caixa eu quero que vc execute esse metodo
		seguroOptional.map(Seguro::getValorFranquia).ifPresent(System.out::println);
	}
}
