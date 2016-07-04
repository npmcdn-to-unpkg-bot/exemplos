package pacote.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import pacote.modelo.Status;
import pacote.modelo.TabelaEstatistica;
import pacote.modelo.TabelaEstatisticaMedia;
import pacote.repository.GeraTabelaEstatistica;
import pacote.service.TabelaEstatisticaService;

@Named
@ViewScoped
public class TabelaEstatisticaBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private TabelaEstatisticaService estatisticaService;
	@Inject
	private GeraTabelaEstatistica geraTabelaEstatistica;
	private TabelaEstatisticaMedia media;

	private List<TabelaEstatistica> atendimentosComClientesAtendidos;
	private List<TabelaEstatistica> atendimentosComClientesCancelados;

	public void init() {
		estatisticaService.deletarTodos();

		atendimentosComClientesAtendidos = geraTabelaEstatistica.geraListaAtendidos(Status.ATENDIDO);
		atendimentosComClientesCancelados = geraTabelaEstatistica.geraListaAtendidos(Status.CANCELADO);
		media = geraTabelaEstatistica.medias();
		
		double totalAtendidos = atendimentosComClientesAtendidos.size();
		double totalCancelados = atendimentosComClientesCancelados.size();

		media.setTotal(totalAtendidos + totalCancelados);
		media.setPorcentagemAtendidos(totalAtendidos / media.getTotal());
		media.setPorcentagemCancelados(totalCancelados / media.getTotal());

		estatisticaService.inserir(atendimentosComClientesAtendidos);
		estatisticaService.inserir(atendimentosComClientesCancelados);
	}

	public List<TabelaEstatistica> getAtendimentosComClientesAtendidos() {
		return atendimentosComClientesAtendidos;
	}

	public List<TabelaEstatistica> getAtendimentosComClientesCancelados() {
		return atendimentosComClientesCancelados;
	}

	public TabelaEstatisticaMedia getMedia() {
		return media;
	}
}
