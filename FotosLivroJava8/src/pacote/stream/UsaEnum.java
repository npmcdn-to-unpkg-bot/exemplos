package pacote.stream;

import java.util.Arrays;

public class UsaEnum {
	public static void main(String[] args) {
		Arrays.asList(Enum.values()).forEach(constante -> {

			//System.out.printf("%-25s #%02d: %-20s\n", constante, constante.getNumero(), constante.getNome());
			System.out.println(constante);
			switch (constante) {
			case COMPACTAR:
				System.out.println("Opção de compactação");
				break;
			case DESCOMPACTAR:
				System.out.println("Opção de descompactação");
				break;
			}
		});
	}
}
