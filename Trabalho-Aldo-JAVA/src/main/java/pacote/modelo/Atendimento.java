package pacote.modelo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "atendimento")
@XmlRootElement
public class Atendimento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "inc")
	@GenericGenerator(name = "inc", strategy = "increment")
	@Column(name = "id_atendimento", nullable = false)
	private int id;

	@NotNull
	@ManyToOne(cascade = { CascadeType.PERSIST})
	@JoinColumn(name = "id_cliente", nullable = false)
	private Cliente cliente = new Cliente();

	@NotNull
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_funcionario", nullable = false)
	private Funcionario funcionario = new Funcionario();

	@NotNull
	@DecimalMin("0")
	@DecimalMax("100")
	@Column(name = "quantidade_pessoas", nullable = false)
	private Integer quantidadePessoas;

	@NotNull
	@Column(name = "hora_chegada", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar horaChegada = Calendar.getInstance();

	@Column(name = "hora_atendimento")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar horaAtendimento;

	@Column(name = "hora_saida")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar horaSaida;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Status status;

	@Column(name = "mesa")
	private Integer mesa;

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

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Integer getQuantidadePessoas() {
		return quantidadePessoas;
	}

	public void setQuantidadePessoas(Integer quantidadePessoas) {
		this.quantidadePessoas = quantidadePessoas;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Integer getMesa() {
		return mesa;
	}

	public void setMesa(Integer mesa) {
		this.mesa = mesa;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		Atendimento other = (Atendimento) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		SimpleDateFormat format = new SimpleDateFormat("HH:mm");
		String dataChegada = format.format(horaChegada.getTime());
		String dataAtendimento = horaAtendimento != null ? format.format(horaAtendimento.getTime()) : "";
		String dataSaida = horaSaida != null ? format.format(horaSaida.getTime()) : "";

		return "Atendimento [id=" + id + ", cliente=" + cliente.getNome() + ", quantidadePessoas=" + quantidadePessoas
				+ ", horaChegada=" + dataChegada + ", horaAtendimento=" + dataAtendimento
				+ ", horaSaida=" + dataSaida + ", status=" + status + ", mesa=" + mesa + "]";
	}

	public String to() {		
		return "Atendimento [cliente=" + cliente.getNome() + ", status=" + status + "] mesa " + mesa;
	}

}
