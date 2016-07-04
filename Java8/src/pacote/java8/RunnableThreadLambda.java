package pacote.java8;

public class RunnableThreadLambda {
	public static void main(String[] args) {
		
		Runnable r1 = new Runnable() {
			@Override
			public void run() {
				System.out.println("run() 1");
			}
		};
		
		Runnable r2 = () -> System.out.println("run() 2");
		
		new Thread(r1).start();
		new Thread(r2).start();
	}
}
