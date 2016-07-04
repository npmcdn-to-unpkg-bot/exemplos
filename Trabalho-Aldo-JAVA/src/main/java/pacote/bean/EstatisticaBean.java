package pacote.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import pacote.modelo.Atendimento;
import pacote.modelo.AtendimentosEstatistica;
import pacote.modelo.ClientesEstatistica;
import pacote.modelo.Status;
import pacote.service.AtendimentoService;
import pacote.service.ClienteEstatisticaService;
import pacote.util.FacesUtils;

@Named
@ViewScoped
public class EstatisticaBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private AtendimentoService service;
	@Inject
	private ClienteEstatisticaService clienteEstatisticaService;

	private List<ClientesEstatistica> estatisticaClientesAtendidos;
	private List<ClientesEstatistica> estatisticaClientesCancelados;
	private AtendimentosEstatistica atendimentosEstatistica;

	public void init() {
		List<Atendimento> atendimentosComClientesAtendidos = service.listarAtendimentosPorStatus(Status.ATENDIDO);
		List<Atendimento> atendimentosComClientesCancelados = service.listarAtendimentosPorStatus(Status.CANCELADO);

		// Calculos para estatistica
		estatisticaClientesAtendidos = new ArrayList<>();
		estatisticaClientesCancelados = new ArrayList<>();
		atendimentosEstatistica = new AtendimentosEstatistica();

		double totalAtendidos = atendimentosComClientesAtendidos.size();
		double totalCancelados = atendimentosComClientesCancelados.size();

		atendimentosEstatistica.setTotal(totalAtendidos + totalCancelados);
		atendimentosEstatistica.setPorcentagemAtendidos(totalAtendidos / atendimentosEstatistica.getTotal());
		atendimentosEstatistica.setPorcentagemCancelados(totalCancelados / atendimentosEstatistica.getTotal());

		calcularAtendimentosComClientesAtendidos(atendimentosComClientesAtendidos);
		calcularAtendimentosComClientesCancelados(atendimentosComClientesCancelados);
	}

	private void calcularAtendimentosComClientesCancelados(List<Atendimento> atendimentosComClientesCancelados) {
		long somaDiferencaEmMilisegundosCancelados = 0;
		int count = 0;

		for (Atendimento atendimento : atendimentosComClientesCancelados) {
			Calendar horaChegada = atendimento.getHoraChegada();
			Calendar horaSaida = atendimento.getHoraSaida();

			String tempoPermanencia = calculaDiferencaEhDevolveUmaString(horaChegada, horaSaida);

			ClientesEstatistica estatistica = new ClientesEstatistica();
			estatistica.setCliente(atendimento.getCliente());
			estatistica.setNumeroAtendimento(atendimento.getId());
			estatistica.setHoraChegada(horaChegada);
			estatistica.setHoraSaida(horaSaida);
			estatistica.setTempoPermacencia(tempoPermanencia);
			estatistica.setStatus(atendimento.getStatus());

			estatisticaClientesCancelados.add(estatistica);

			// Soma a diferença de tempo de cancelamento
			somaDiferencaEmMilisegundosCancelados += calculaDiferencaEmMilisegundos(horaChegada, horaSaida);
			count++;
		}

		// Média do tempo de cancelamento
		if (somaDiferencaEmMilisegundosCancelados != 0) {
			long mediaEmMilisegundos = somaDiferencaEmMilisegundosCancelados / count;
			atendimentosEstatistica.setMediaTempoCancelamento(calculaMediaEhDevolveUmaString(mediaEmMilisegundos));
		}
	}

	private void calcularAtendimentosComClientesAtendidos(List<Atendimento> atendimentosComClientesAtendidos) {
		long somaDiferencaEmMilisegundosEspera = 0;
		long somaDiferencaEmMilisegundosAtendimento = 0;
		int count = 0;

		for (Atendimento atendimento : atendimentosComClientesAtendidos) {
			Calendar horaChegada = atendimento.getHoraChegada();
			Calendar horaAtendimento = atendimento.getHoraAtendimento();
			Calendar horaSaida = atendimento.getHoraSaida();

			String tempoEspera = calculaDiferencaEhDevolveUmaString(horaChegada, horaAtendimento);
			String tempoPermanencia = calculaDiferencaEhDevolveUmaString(horaChegada, horaSaida);

			ClientesEstatistica estatistica = new ClientesEstatistica();
			estatistica.setCliente(atendimento.getCliente());
			estatistica.setNumeroAtendimento(atendimento.getId());
			estatistica.setHoraChegada(horaChegada);
			estatistica.setHoraAtendimento(horaAtendimento);
			estatistica.setHoraSaida(horaSaida);
			estatistica.setTempoEspera(tempoEspera);
			estatistica.setTempoPermacencia(tempoPermanencia);
			estatistica.setStatus(atendimento.getStatus());

			estatisticaClientesAtendidos.add(estatistica);

			// Soma a diferença de tempo de espera e atendimento
			somaDiferencaEmMilisegundosEspera += calculaDiferencaEmMilisegundos(horaChegada, horaAtendimento);
			somaDiferencaEmMilisegundosAtendimento += calculaDiferencaEmMilisegundos(horaChegada, horaSaida);
			count++;
		}

		// calcula a media de tempo de espera e atendimento
		if (somaDiferencaEmMilisegundosEspera != 0) {
			long mediaEmMilisegundosEspera = somaDiferencaEmMilisegundosEspera / count;
			long mediaEmMilisegundosAtendimento = somaDiferencaEmMilisegundosAtendimento / count;
			atendimentosEstatistica.setMediaTempoEspera(calculaMediaEhDevolveUmaString(mediaEmMilisegundosEspera));
			atendimentosEstatistica
					.setMediaTempoAtendimento(calculaMediaEhDevolveUmaString(mediaEmMilisegundosAtendimento));
		}
	}

	public long calculaDiferencaEmMilisegundos(Calendar dataInicial, Calendar dataFinal) {
		long diff = dataFinal.getTimeInMillis() - dataInicial.getTimeInMillis();
		return diff;
	}

	public String calculaDiferencaEhDevolveUmaString(Calendar dataInicial, Calendar dataFinal) {
		// diferenca em horas e minutos
		long diff = dataFinal.getTimeInMillis() - dataInicial.getTimeInMillis();
		long hours = (60 * 60 * 1000);
		long diffHoras = diff / hours;
		long diffHorasMinutos = (diff % hours) / (60 * 1000);
		long diffHorasMinutosSegundos = (diff / 1000) % 60;

		String diferencaEmHorasEhMinutos = "";

		if (diffHoras == 0) {
			if (diffHorasMinutos <= 1) {
				diferencaEmHorasEhMinutos = diffHorasMinutos + " minuto e " + diffHorasMinutosSegundos + " segundos";
			} else {
				diferencaEmHorasEhMinutos = diffHorasMinutos + " minutos e " + diffHorasMinutosSegundos + " segundos";
			}
		} else if (diffHoras == 1) {
			if (diffHorasMinutos <= 1) {
				diferencaEmHorasEhMinutos = diffHoras + " hora e " + diffHorasMinutos + " minuto e "
						+ diffHorasMinutosSegundos + " segundos";
			} else {
				diferencaEmHorasEhMinutos = diffHoras + " hora e " + diffHorasMinutos + " minutos e "
						+ diffHorasMinutosSegundos + " segundos";
			}
		} else if (diffHoras > 1) {
			if (diffHorasMinutos <= 1) {
				diferencaEmHorasEhMinutos = diffHoras + " horas e " + diffHorasMinutos + " minuto e "
						+ diffHorasMinutosSegundos + " segundos";
			} else {
				diferencaEmHorasEhMinutos = diffHoras + " horas e " + diffHorasMinutos + " minutos e "
						+ diffHorasMinutosSegundos + " segundos";
			}
		}
		return diferencaEmHorasEhMinutos;
	}

	public String calculaMediaEhDevolveUmaString(long diff) {
		// diferenca em horas e minutos
		long hours = (60 * 60 * 1000);
		long diffHoras = diff / hours;
		long diffHorasMinutos = (diff % hours) / (60 * 1000);
		long diffHorasMinutosSegundos = (diff / 1000) % 60;

		String diferencaEmHorasEhMinutos = "";

		if (diffHoras == 0) {
			if (diffHorasMinutos <= 1) {
				diferencaEmHorasEhMinutos = diffHorasMinutos + " minuto e " + diffHorasMinutosSegundos + " segundos";
			} else {
				diferencaEmHorasEhMinutos = diffHorasMinutos + " minutos e " + diffHorasMinutosSegundos + " segundos";
			}
		}
		if (diffHoras == 1) {
			if (diffHorasMinutos <= 1) {
				diferencaEmHorasEhMinutos = diffHoras + " hora e " + diffHorasMinutos + " minuto e "
						+ diffHorasMinutosSegundos + " segundos";
			} else {
				diferencaEmHorasEhMinutos = diffHoras + " hora e " + diffHorasMinutos + " minutos e "
						+ diffHorasMinutosSegundos + " segundos";
			}
		} else if (diffHoras > 1) {
			if (diffHorasMinutos <= 1) {
				diferencaEmHorasEhMinutos = diffHoras + " horas e " + diffHorasMinutos + " minuto e "
						+ diffHorasMinutosSegundos + " segundos";
			} else {
				diferencaEmHorasEhMinutos = diffHoras + " horas e " + diffHorasMinutos + " minutos e "
						+ diffHorasMinutosSegundos + " segundos";
			}
		}
		return diferencaEmHorasEhMinutos;
	}

	public void salvar() {
		try {
			clienteEstatisticaService.persistir(estatisticaClientesAtendidos);
			clienteEstatisticaService.persistir(estatisticaClientesCancelados);
			FacesUtils.mostrarMensagemSucesso("Salvo com sucesso!", null);
		} catch (Exception e) {
			FacesUtils.mostrarMensagemErro("Aconteceu um erro", null);
			throw new RuntimeException(e);
		}
	}

	public List<ClientesEstatistica> getEstatisticaClientesAtendidos() {
		return estatisticaClientesAtendidos;
	}

	public List<ClientesEstatistica> getEstatisticaClientesCancelados() {
		return estatisticaClientesCancelados;
	}

	public AtendimentosEstatistica getAtendimentosEstatistica() {
		return atendimentosEstatistica;
	}
}
