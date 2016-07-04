package pacote.lambda;

import java.util.List;

public class Exemplo {
	public static void main(String[] args) {
		EnviadorEmail enviadorEmail = new EnviadorEmail();
		List<Fatura> faturasVencidas = FaturaDAO.buscarFaturasVencidas();

		// Consumer é o tipo de uma classe, ele ja sabe que a lista é de fatura,
		// posso declarar direto no nome da variavel
		faturasVencidas.forEach(f -> {
			enviadorEmail.enviar(f.getEmailDevedor(), f.resumoFatura());
			f.setNotificacaoEnviada(true);
		});

		// Se for executar apenas uma linha não precisa coloca chaves
		faturasVencidas.forEach(f -> enviadorEmail.enviar(f.getEmailDevedor(), f.resumoFatura()));

		/*
		 * for (Fatura fatura : faturasVencidas) {
		 * enviadorEmail.enviar(fatura.getEmailDevedor(),
		 * fatura.resumoFatura()); }
		 */
	}
}
