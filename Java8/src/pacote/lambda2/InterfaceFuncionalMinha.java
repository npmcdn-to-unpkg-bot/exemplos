package pacote.lambda2;

public class InterfaceFuncionalMinha {

	interface Num {
		double getValor();
	}

	interface ValorNumerico {
		boolean teste(int n);
	}

	public static void main(String[] args) {
		System.out.println("#################################################################");
		Num n;
		// dissemos que vai ser retornado esse valor
		n = () -> 333.11;
		System.out.println(n.getValor());
		Num n2 = () -> Math.random() * 100;
		System.out.println(n2.getValor());

		System.out.println("#################################################################");

		ValorNumerico isPar2 = new ValorNumerico() {
			@Override
			public boolean teste(int n) {
				return (n % 2) == 0;
			}
		};

		ValorNumerico isPar = (int i) -> (i % 2) == 0;

		System.out.println(isPar.teste(89));
		System.out.println(isPar.teste(90));
		System.out.println("#################################################################");
	}
}
