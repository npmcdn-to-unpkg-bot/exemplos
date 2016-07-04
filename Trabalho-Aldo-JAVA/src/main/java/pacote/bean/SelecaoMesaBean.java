package pacote.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import pacote.service.AtendimentoService;

@Named
@ViewScoped
public class SelecaoMesaBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private AtendimentoService service;

	private List<Integer> mesas;
	private List<Integer> mesasDoBanco;
	private Integer mesaSelecionada;

	/**
	 * Método chamado na fase de invocação da aplicação, antes de renderizar a
	 * página. Inicializa a lista de mesas, e busca todas as mesas que estão
	 * ocupadas
	 */
	public void init() {
		mesas = new ArrayList<>();
		for (int i = 1; i < 50; i++) {
			mesas.add(i);
		}
		mesasDoBanco = service.listarMesas();
	}

	/**
	 * Verifica se ja existe algum registro relacionado a essa mesa. Se sim
	 * retorna TRUE e ele deixa o botão desabilitado, se não existe retorna
	 * FALSE e deixa o botão habilitado
	 * 
	 * @param mesa
	 * @return
	 */
	public boolean disponivel(int mesa) {
		return mesasDoBanco.contains(mesa);
	}

	/**
	 * Adiciona um ZERO a todas as mesas de número menor que 10
	 * 
	 * @param mesa
	 * @return
	 */
	public String adicionaZero(int mesa) {
		return mesa < 10 ? "0" + mesa : mesa + "";
	}

	/**
	 * Metodo chamado quando o usuário selecionar uma mesa, ele pega o numero da
	 * mesa e fecha o dialog
	 * 
	 * @param event
	 */
	public void selecionarMesa(AjaxBehaviorEvent event) {
		RequestContext.getCurrentInstance().closeDialog(mesaSelecionada);
	}

	/**
	 * Método utilizado para abrir o dialog para selecionar a mesa
	 */
	public void abrirDialog() {
		Map<String, Object> opcoes = new HashMap<>();
		// Abre o dialog e bloqueia o fundo
		opcoes.put("modal", true);
		// Não deixa redimencionar
		opcoes.put("resizable", false);
		// Altura da aerea conteudo
		opcoes.put("contentHeight", 325);
		opcoes.put("contentWidth", 423);
		RequestContext.getCurrentInstance().openDialog("SelecaoMesa.xhtml", opcoes, null);
	}

	public List<Integer> getMesas() {
		return mesas;
	}

	public Integer getMesaSelecionada() {
		return mesaSelecionada;
	}

	public void setMesaSelecionada(Integer mesaSelecionada) {
		this.mesaSelecionada = mesaSelecionada;
	}
}
