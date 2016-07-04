package pacote.modelo;

import java.math.BigDecimal;

public class PrecoVeiculo {
    private String modelo;
    private BigDecimal valor;
    
    public PrecoVeiculo(String modelo, BigDecimal valor) {
	super();
	this.modelo = modelo;
	this.valor = valor;
    }
    public String getModelo() {
        return modelo;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    public BigDecimal getValor() {
        return valor;
    }
    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
    
    
}
