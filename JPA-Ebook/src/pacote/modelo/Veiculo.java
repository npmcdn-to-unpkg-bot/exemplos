package pacote.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "veiculo")
@NamedQueries({
	@NamedQuery(name = "Veiculo.todos", query = "from Veiculo v inner join fetch v.propietario")})
public class Veiculo implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "inc")
    @GenericGenerator(name = "inc", strategy = "increment")
    private Long codigo;

    @Column(length = 60, nullable = false)
    private String fabricante;

    @Column(length = 60, nullable = false)
    private String modelo;

    @Column(name = "ano_fabricacao", nullable = false)
    private Integer anoFabricacao;

    @Column(name = "ano_modelo", nullable = false)
    private Integer anoModelo;

    @Column(precision = 10, scale = 2, nullable = true)
    private BigDecimal valor;

    @Column(name = "tipo_combustivel", nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoCombustivel tipoCombustivel;

    @Column(name = "data_cadastro")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dataCadastro;

    @Transient
    private String nadaNoBanco;

    @ManyToOne
    @JoinColumn(name = "cod_propietario")
    private Propietario propietario;

    @ManyToMany
    @JoinTable(name = "veiculo_acessorio", joinColumns = @JoinColumn(name = "cod_veiculo"), inverseJoinColumns = @JoinColumn(name = "cod_acessorio"))
    private Set<Acessorio> acessorios = new HashSet<>();

    public Set<Acessorio> getAcessorios() {
	return acessorios;
    }

    public void setAcessorios(Set<Acessorio> acessorios) {
	this.acessorios = acessorios;
    }

    public Propietario getPropietario() {
	return propietario;
    }

    public void setPropietario(Propietario propietario) {
	this.propietario = propietario;
    }

    public void setDataCadastro(Calendar dataCadastro) {
	this.dataCadastro = dataCadastro;
    }

    public Calendar getDataCadastro() {
	return dataCadastro;
    }

    public void setTipoCombustivel(TipoCombustivel tipoCombustivel) {
	this.tipoCombustivel = tipoCombustivel;
    }

    public TipoCombustivel getTipoCombustivel() {
	return tipoCombustivel;
    }

    public Long getCodigo() {
	return codigo;
    }

    public void setCodigo(Long codigo) {
	this.codigo = codigo;
    }

    public String getFabricante() {
	return fabricante;
    }

    public void setFabricante(String fabricante) {
	this.fabricante = fabricante;
    }

    public String getModelo() {
	return modelo;
    }

    public void setModelo(String modelo) {
	this.modelo = modelo;
    }

    public Integer getAnoFabricacao() {
	return anoFabricacao;
    }

    public void setAnoFabricacao(Integer anoFabricacao) {
	this.anoFabricacao = anoFabricacao;
    }

    public Integer getAnoModelo() {
	return anoModelo;
    }

    public void setAnoModelo(Integer anoModelo) {
	this.anoModelo = anoModelo;
    }

    public BigDecimal getValor() {
	return valor;
    }

    public void setValor(BigDecimal valor) {
	this.valor = valor;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Veiculo other = (Veiculo) obj;
	if (codigo == null) {
	    if (other.codigo != null)
		return false;
	} else if (!codigo.equals(other.codigo))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	// TODO Auto-generated method stub
	return "[Modelo: " + modelo + " / Fabricante: " + fabricante + " / Valor: " + valor + "]";
    }
}
