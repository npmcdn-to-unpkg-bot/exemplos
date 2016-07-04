package pacote.reader;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;

import com.thoughtworks.xstream.converters.ConversionException;

import pacote.modelo.Negociacao;

public class LeitorXMLTest {

	@Test
	public void carregaXmlComUmaNegociacaoEmListaUnitaria() {
		String xml = "<list><negociacao><preco>43.5</preco><quantidade>1000</quantidade><data>"
				+ "<time>1456704000000</time><timezone>Etc/UTC</timezone></data>" + "</negociacao></list>";
		LeitorXML leitor = new LeitorXML();
		InputStream is = new ByteArrayInputStream(xml.getBytes());

		List<Negociacao> negociacaos = leitor.carrega(is);

		assertEquals(1, negociacaos.size());
		assertEquals(new BigDecimal("43.5"), negociacaos.get(0).getPreco());
		assertEquals(1000, negociacaos.get(0).getQuantidade());
	}

	@Test
	public void carregaXmlComTresNegociacaoEmListaUnitaria() {
		String xml = "<list><negociacao><preco>43.5</preco><quantidade>1000</quantidade><data>"
				+ "<time>1456704000000</time><timezone>Etc/UTC</timezone></data>" + "</negociacao>"
				+ "<negociacao><preco>43.5</preco><quantidade>1000</quantidade><data>"
				+ "<time>1456704000000</time><timezone>Etc/UTC</timezone></data>" + "</negociacao>"
				+ "<negociacao><preco>43.5</preco><quantidade>1000</quantidade><data>"
				+ "<time>1456704000000</time><timezone>Etc/UTC</timezone></data>" + "</negociacao></list>";
		LeitorXML leitor = new LeitorXML();
		InputStream is = new ByteArrayInputStream(xml.getBytes());

		List<Negociacao> negociacaos = leitor.carrega(is);

		assertEquals(3, negociacaos.size());
	}
	
	@Test(expected = ConversionException.class)
	public void carregaXmlComPrecoFaltando() {
		String xml = "<list><negociacao><preco></preco><quantidade>1000</quantidade><data>"
				+ "<time>1456704000000</time><timezone>Etc/UTC</timezone></data>" + "</negociacao></list>";
		LeitorXML leitor = new LeitorXML();
		InputStream is = new ByteArrayInputStream(xml.getBytes());

		List<Negociacao> negociacaos = leitor.carrega(is);

		assertEquals(1, negociacaos.size());
		assertEquals(null, negociacaos.get(0).getPreco());
		assertEquals(1000, negociacaos.get(0).getQuantidade());
	}
}
