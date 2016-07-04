package pacote.lambda;

public class Calculavel {
	private static int i = 1;
	interface ICalculavel {
		double calculo(double a, double b);
	}

	public static void main(String[] args) {
		ICalculavel c = (a, b) -> a * b;
		ICalculavel c2 = Math::max;

		double resultado = efetuarCalculo((a, b) -> {
			i++;
			return a * b;
		}, 1, 2);

		System.out.println(resultado);
	}

	public static double efetuarCalculo(ICalculavel calculavel, double a, double b) {
		return calculavel.calculo(a, b) * a * b;
	}
}
