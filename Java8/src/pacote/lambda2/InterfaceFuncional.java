package pacote.lambda2;

public class InterfaceFuncional {
	public static void main(String[] args) {
		String nome = "afasfa";
		Runnable r1 = new Runnable() {
			@Override
			public void run() {
				System.out.println("Expressões Lambdas 1" + nome);
			}
		};

		// () = Lista de argumentos, -> indica que estamos usando expressao
		// lambada, 3º corpo do metodo {}
		Runnable r10 = () -> System.out.println("Expressões Lambdas 2");

		Runnable r2 = () -> {
			System.out.println("Expressões Lambdas 2");
		};

		r1.run();
		r2.run();

		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("Expressões Lambdas na Thread 1");
			}
		}).start();

		new Thread(() -> System.out.println("Expressões Lambdas na Thread 2")).start();
	}

}
