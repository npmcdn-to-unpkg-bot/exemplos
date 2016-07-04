package pacote.modelo;

public class TotalPorAno {
    private Integer anoFabricao;
    private Double mediaPreco;
    private Long quantidadeCarros;
    
    public TotalPorAno(Integer anoFabricao, Double mediaPreco, Long quantidadeCarros) {
	super();
	this.anoFabricao = anoFabricao;
	this.mediaPreco = mediaPreco;
	this.quantidadeCarros = quantidadeCarros;
    }

    public Integer getAnoFabricao() {
        return anoFabricao;
    }

    public void setAnoFabricao(Integer anoFabricao) {
        this.anoFabricao = anoFabricao;
    }

    public Double getMediaPreco() {
        return mediaPreco;
    }

    public void setMediaPreco(Double mediaPreco) {
        this.mediaPreco = mediaPreco;
    }

    public Long getQuantidadeCarros() {
        return quantidadeCarros;
    }

    public void setQuantidadeCarros(Long quantidadeCarros) {
        this.quantidadeCarros = quantidadeCarros;
    }

    @Override
    public String toString() {
	return "TotalPorAno [anoFabricao=" + anoFabricao + ", mediaPreco=" + mediaPreco + ", quantidadeCarros="
		+ quantidadeCarros + "]";
    }
    
    
}
