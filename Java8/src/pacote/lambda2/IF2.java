package pacote.lambda2;

public class IF2 {

	interface Numero {
		double getValor();
	}

	interface ValorNumerico {
		boolean teste(int n1);
	}

	interface ValorNumerico2 {
		boolean teste(int n1, int n2);
	}

	public static void main(String[] args) {
		Numero n = () -> {
			return 100.0;
		};
		System.out.println(n.getValor());

		ValorNumerico isPar = (int n1) -> (n1 % 2) == 0;
		System.out.println(isPar.teste(20));

		ValorNumerico2 isIgual = (x, y) -> (x % y) == 0;
		System.out.println(isIgual.teste(10, 2));

		// Quando for o metodo tiver um parametro não precisamos especificar o
		// tipo nem colocar parenteses
		ValorNumerico expressao1 = n1 -> (n1 % 2) == 0;
		System.out.println(expressao1.teste(20));

		// Quando for o metodo tiver DOIS parametro precisamos colocar
		// parenteses sem precisar colocar o tipo
		ValorNumerico2 expressao2 = (x, y) -> {
			// Tudo que estiver em duas chaves é chamado de bloco Lambda
			int w = x + y;
			return w > 1000;
		};
		System.out.println(expressao2.teste(10, 2));
	}
}
