package pacote.bean;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.ChartModel;

import pacote.cdi.ICliente;
import pacote.cdi.WebService;
import pacote.grafico.GeradorModeloGrafico;
import pacote.indicadores.Indicador;
import pacote.indicadores.IndicadorFechamento;
import pacote.indicadores.MediaMovelSimples;
import pacote.modelo.Candle;
import pacote.modelo.CandleFactory;
import pacote.modelo.Negociacao;
import pacote.modelo.SerieTemporal;

@Named
@ViewScoped
public class ArgentunBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private List<Negociacao> negociacoes;
	private ChartModel modeloGrafico;

	private String nomeIndicador;
	private String nomeMedia;

	@Inject
	@WebService
	private ICliente cliente;

	@Inject
	private CandleFactory factory;

	private Indicador indicadorMedia;

	private Indicador indicadorBase;

	@PostConstruct
	public void init() {
		System.out.println("@PostConstruct");

		this.negociacoes = this.cliente.getNegociacoes();
		geraGrafico();
	}

	public List<Negociacao> getNegociacoes() {
		return negociacoes;
	}

	public ChartModel getModeloGrafico() {
		return modeloGrafico;
	}

	public String getNomeMedia() {
		return nomeMedia;
	}

	public void setNomeMedia(String nomeMedia) {
		this.nomeMedia = nomeMedia;
	}

	public String getNomeIndicador() {
		return nomeIndicador;
	}

	public void setNomeIndicador(String nomeIndicador) {
		this.nomeIndicador = nomeIndicador;
	}

	public void geraGrafico() {

		List<Candle> candles = factory.constroiCandles(negociacoes);
		SerieTemporal serie = new SerieTemporal(candles);

		GeradorModeloGrafico gerador = new GeradorModeloGrafico(serie, 2, 10);
		gerador.plotaIndicador(defineIndicador());
		this.modeloGrafico = gerador.getModeloGrafico();
	}

	private Indicador defineIndicador() {
		if (nomeIndicador == null || nomeMedia == null) {
			return new MediaMovelSimples(new IndicadorFechamento());
		}

		try {
			String pacote = "pacote.indicadores.";
			indicadorBase = (Indicador) Class.forName(pacote + nomeIndicador).newInstance();

			Class<?> classeMedia = Class.forName(pacote + nomeMedia);
			Constructor<?> constructorMedia = classeMedia.getConstructor(Indicador.class);
			indicadorMedia = (Indicador) constructorMedia.newInstance(indicadorBase);
			return indicadorMedia;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
