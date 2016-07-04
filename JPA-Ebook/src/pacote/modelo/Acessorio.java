package pacote.modelo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "acessorio")
public class Acessorio {

    @Id
    @GeneratedValue(generator = "inc")
    @GenericGenerator(name = "inc", strategy = "increment")
    private Long codigo;

    @Column(length = 60, nullable = false)
    private String descricao;

    @ManyToMany(mappedBy = "acessorios", cascade = CascadeType.PERSIST)
    private Set<Veiculo> veiculos = new HashSet<Veiculo>();

    public Acessorio(String descricao) {
	this.descricao = descricao;
    }

    public Acessorio() {

    }

    public Set<Veiculo> getVeiculos() {
	return veiculos;
    }

    public void setVeiculos(Set<Veiculo> veiculos) {
	this.veiculos = veiculos;
    }

    public Long getCodigo() {
	return codigo;
    }

    public void setCodigo(Long codigo) {
	this.codigo = codigo;
    }

    public String getDescricao() {
	return descricao;
    }

    public void setDescricao(String descricao) {
	this.descricao = descricao;
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
	Acessorio other = (Acessorio) obj;
	if (codigo == null) {
	    if (other.codigo != null)
		return false;
	} else if (!codigo.equals(other.codigo))
	    return false;
	return true;
    }
}
