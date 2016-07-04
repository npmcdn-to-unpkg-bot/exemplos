package tdc;

import java.io.IOException;

import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.enterprise.util.AnnotationLiteral;
import javax.enterprise.util.TypeLiteral;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cdi.Frete;
import cdi.FreteAutomatico;
import cdi.FreteManual;
import cdi.TipoFrete;

@WebServlet("/fluxo")
public class FluxoDeCompraServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Inject // PONTO DE INJEÇÃO
	private MeuLogger meuLogger;

	@Inject
	@Any
	private Instance<CalculadoraDeFrete> calculadoraLazy;

	@Inject
	private CarrinhoDeCompras carrinho;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		meuLogger.info("Iniciando fluxo de conta!");

		double valorTotal = 1000.0;
		// double frete = calculadora.calcula(valorTotal);
		// resp.getOutputStream().print(frete);
		String tipoEscolhido = "B";
		if (tipoEscolhido.equals("A")) {
			Instance<CalculadoraDeFrete> var = calculadoraLazy.select(new AnnotationLiteral<FreteAutomatico>() {
			});
			var.get().calcula(valorTotal);
		} else if (tipoEscolhido.equals("B")) {
			Instance<CalculadoraDeFrete> var = calculadoraLazy.select(new AnnotationLiteral<FreteManual>() {
			});
			var.get().calcula(valorTotal);
		}

		carrinho.finalizaCompra();
	}
}
