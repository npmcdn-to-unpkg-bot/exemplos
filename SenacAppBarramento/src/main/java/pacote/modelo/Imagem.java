package pacote.modelo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Imagem {
	private String imagem64;
	private String nomeImagem;

	public String getImagem64() {
		return imagem64;
	}

	public void setImagem64(String imagem64) {
		this.imagem64 = imagem64;
	}

	public String getNomeImagem() {
		return nomeImagem;
	}

	public void setNomeImagem(String nomeImagem) {
		this.nomeImagem = nomeImagem;
	}

}
