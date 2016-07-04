package tdc;

public class MeuLogger {

	private Class<?> classe;

	public MeuLogger(Class<?> classe) {
		this.classe = classe;
	}

	public void info(String mensagem) {
		System.out.println("::CLASS::" + classe.getName());
		System.out.println(mensagem);
	}

	public void close() {
		System.out.println("Close");
	}
}
