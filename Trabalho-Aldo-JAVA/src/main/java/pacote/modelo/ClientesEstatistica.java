package pacote.modelo;

import java.io.Serializable;
import java.util.Calendar;

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

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "cliente_estatistica_tudo ")
public class ClientesEstatistica implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "inc")
	@GenericGenerator(name = "inc", strategy = "increment")
	@Column(name = "id_cliente_estatistica_tudo", nullable = false)
	private int id;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_cliente")
	private Cliente cliente;

	@Column(name = "hora_chegada")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar horaChegada;

	@Column(name = "hora_atendimento")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar horaAtendimento;

	@Column(name = "hora_saida")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar horaSaida;

	@Column(name = "numero_atendimento")
	private int numeroAtendimento;

	@Column(name = "tempo_espera")
	private String tempoEspera;

	@Column(name = "tempo_permacencia")
	private String tempoPermacencia;

	@Enumerated(EnumType.STRING)
	private Status status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Calendar getHoraChegada() {
		return horaChegada;
	}

	public void setHoraChegada(Calendar horaChegada) {
		this.horaChegada = horaChegada;
	}

	public Calendar getHoraAtendimento() {
		return horaAtendimento;
	}

	public void setHoraAtendimento(Calendar horaAtendimento) {
		this.horaAtendimento = horaAtendimento;
	}

	public Calendar getHoraSaida() {
		return horaSaida;
	}

	public void setHoraSaida(Calendar horaSaida) {
		this.horaSaida = horaSaida;
	}

	public int getNumeroAtendimento() {
		return numeroAtendimento;
	}

	public void setNumeroAtendimento(int numeroAtendimento) {
		this.numeroAtendimento = numeroAtendimento;
	}

	public String getTempoEspera() {
		return tempoEspera;
	}

	public void setTempoEspera(String tempoEspera) {
		this.tempoEspera = tempoEspera;
	}

	public String getTempoPermacencia() {
		return tempoPermacencia;
	}

	public void setTempoPermacencia(String tempoPermacencia) {
		this.tempoPermacencia = tempoPermacencia;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
}
