package pacote.modelo;

import java.util.Date;

public class TabelaEstatisticaMedia {

	private Date mediaTempoEspera;
	private Date mediaTempoAtendimento;
	private Date mediaTempoCancelamento;
	private double porcentagemAtendidos;
	private double porcentagemCancelados;
	private double total;

	public double getPorcentagemAtendidos() {
		return porcentagemAtendidos;
	}

	public void setPorcentagemAtendidos(double porcentagemAtendidos) {
		this.porcentagemAtendidos = porcentagemAtendidos;
	}

	public double getPorcentagemCancelados() {
		return porcentagemCancelados;
	}

	public void setPorcentagemCancelados(double porcentagemCancelados) {
		this.porcentagemCancelados = porcentagemCancelados;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Date getMediaTempoEspera() {
		return mediaTempoEspera;
	}

	public void setMediaTempoEspera(Date mediaTempoEspera) {
		this.mediaTempoEspera = mediaTempoEspera;
	}

	public Date getMediaTempoAtendimento() {
		return mediaTempoAtendimento;
	}

	public void setMediaTempoAtendimento(Date mediaTempoAtendimento) {
		this.mediaTempoAtendimento = mediaTempoAtendimento;
	}

	public Date getMediaTempoCancelamento() {
		return mediaTempoCancelamento;
	}

	public void setMediaTempoCancelamento(Date mediaTempoCancelamento) {
		this.mediaTempoCancelamento = mediaTempoCancelamento;
	}
}
