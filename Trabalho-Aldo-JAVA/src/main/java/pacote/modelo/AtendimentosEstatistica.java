package pacote.modelo;

public class AtendimentosEstatistica {
	private double porcentagemAtendidos;
	private double porcentagemCancelados;
	private double total;
	private String mediaTempoEspera;
	private String mediaTempoAtendimento;
	private String mediaTempoCancelamento;

	public AtendimentosEstatistica() {
	}

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

	public String getMediaTempoEspera() {
		return mediaTempoEspera;
	}

	public void setMediaTempoEspera(String mediaTempoEspera) {
		this.mediaTempoEspera = mediaTempoEspera;
	}

	public String getMediaTempoAtendimento() {
		return mediaTempoAtendimento;
	}

	public void setMediaTempoAtendimento(String mediaTempoAtendimento) {
		this.mediaTempoAtendimento = mediaTempoAtendimento;
	}

	public String getMediaTempoCancelamento() {
		return mediaTempoCancelamento;
	}

	public void setMediaTempoCancelamento(String mediaTempoCancelamento) {
		this.mediaTempoCancelamento = mediaTempoCancelamento;
	}
}