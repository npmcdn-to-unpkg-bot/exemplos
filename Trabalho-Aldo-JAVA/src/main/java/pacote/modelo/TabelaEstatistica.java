package pacote.modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "tabela_estatistica")
@XmlRootElement
public class TabelaEstatistica implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "inc") 
	@GenericGenerator(name = "inc", strategy = "increment")
	@Column(name = "id_tabela_estatistica", nullable = false)
	private int id;
	
	@Column(name = "numero_atendimento", nullable = false)
	private int numeroAtendimento;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_cliente", nullable = false)
	private Cliente cliente = new Cliente();

	@Column(name = "tempo_espera")
	@Temporal(TemporalType.TIME)
	private Date tempoEspera;

	@Column(name = "tempo_permanencia")
	@Temporal(TemporalType.TIME)
	private Date tempoPermanencia;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Status status;

	public int getNumeroAtendimento() {
		return numeroAtendimento;
	}

	public void setNumeroAtendimento(int numeroAtendimento) {
		this.numeroAtendimento = numeroAtendimento;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Date getTempoEspera() {
		return tempoEspera;
	}

	public void setTempoEspera(Date tempoEspera) {
		this.tempoEspera = tempoEspera;
	}

	public Date getTempoPermanencia() {
		return tempoPermanencia;
	}

	public void setTempoPermanencia(Date tempoPermanencia) {
		this.tempoPermanencia = tempoPermanencia;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + numeroAtendimento;
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
		TabelaEstatistica other = (TabelaEstatistica) obj;
		if (numeroAtendimento != other.numeroAtendimento)
			return false;
		return true;
	}
}
