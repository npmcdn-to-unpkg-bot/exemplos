package pacote.data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Locacao {
	private List<Filme> filmes;
	private Cliente cliente;
	private LocalDateTime data;
	private Devolucao devolucao;
	private BigDecimal total;

	public Locacao(List<Filme> filmes, Cliente cliente) {
		this.filmes = filmes;
		this.cliente = cliente;
		this.data = LocalDateTime.now();
		gerarDevolucao();
	}

	public void imprimirRecibo() {
		System.out.printf("Obrigado %s.\n", this.cliente.getNome());
		System.out.printf("Filme(s):\n");

		total = BigDecimal.ZERO;
		filmes.forEach(f -> {
			System.out.println(f.getNome());
			total = total.add(f.getValor());
		});

		System.out.printf("Valor total R$%s\n", total);
		System.out.printf("Data devolução: %s\n",
				this.getDevolucao().getDataPrevista().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
	}

	private void gerarDevolucao() {
		this.devolucao = new Devolucao(LocalDateTime.of(calcularDataPrevista(), LocalTime.of(19, 0)));
	}

	private LocalDate calcularDataPrevista() {
		// incrementa dias a minha data
		return this.data.plusDays(this.filmes.size()).toLocalDate();
	}

	public List<Filme> getFilmes() {
		return filmes;
	}

	public void setFilmes(List<Filme> filmes) {
		this.filmes = filmes;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public Devolucao getDevolucao() {
		return devolucao;
	}

	public void setDevolucao(Devolucao devolucao) {
		this.devolucao = devolucao;
	}

	public static void main(String[] args) {
		LocalDateTime l = LocalDateTime.of(LocalDate.of(2014, Month.AUGUST, 19), LocalTime.of(19, 30, 22));

	}
}
