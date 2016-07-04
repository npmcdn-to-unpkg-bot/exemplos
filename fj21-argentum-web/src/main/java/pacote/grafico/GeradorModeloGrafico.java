package pacote.grafico;

import java.math.BigDecimal;

import org.primefaces.model.chart.ChartModel;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

import pacote.indicadores.Indicador;
import pacote.indicadores.MediaMovelSimples;
import pacote.modelo.SerieTemporal;

public class GeradorModeloGrafico {

	private SerieTemporal serie;
	private int comeco;
	private int fim;
	private LineChartModel modeloGrafico;

	public GeradorModeloGrafico(SerieTemporal serie, int comeco, int fim) {
		this.serie = serie;
		this.comeco = comeco;
		this.fim = fim;
		this.modeloGrafico = new LineChartModel();
	}

	public void plotaIndicador(Indicador indicador) {
		LineChartSeries chartSeries = new LineChartSeries(indicador.toString());

		for (int i = comeco; i <= fim; i++) {
			BigDecimal valor = indicador.calcula(i, serie);
			chartSeries.set(i, valor);
		}

		this.modeloGrafico.addSeries(chartSeries);
		this.modeloGrafico.setLegendPosition("W");
		this.modeloGrafico.setTitle("Indicadores");
	}
	
	public ChartModel getModeloGrafico() {
		return modeloGrafico;
	}

}
