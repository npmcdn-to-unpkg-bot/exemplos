package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import br.com.livro.domain.Carro;
import br.com.livro.domain.CarroService;

public class CarroTest {
	private CarroService carroService = new CarroService();

	@Test
	public void test() {
		List<Carro> carros = carroService.getCarros();
		assertNotNull(carros);
		// Valida se encontrou algo
		assertTrue(carros.size() > 0);
		// Valida se encontrou o Tucker
		Carro tucker = carroService.findByName("Tucker 1948").get(0);
		assertEquals("Tucker 1948", tucker.getNome());
		// Valida se encontrou a Ferrari
		Carro ferrari = carroService.findByName("Ferrari FF").get(0);
		assertEquals("Ferrari FF", ferrari.getNome());
		// Valida se encontrou o Bugatti
		Carro bugatti = carroService.findByName("Bugatti Veyron").get(0);
		assertEquals("Bugatti Veyron", bugatti.getNome());
	}

	@Test
	public void salvaDeletaCarro() {
		Carro carro = new Carro();
		carro.setNome("nome");
		carro.setDesc("desc");
		carro.setUrlFoto("url foto");
		carro.setUrlVideo("url video");
		carro.setLatitude("lat");
		carro.setLongitude("lng");
		carro.setTipo("tipo");
		
		carroService.save(carro);
		// Id do carro salvo
		Long id = carro.getId();
		assertNotNull(id);
		// Busca no banco de dados para confirmar que o carro foi salvo
		carro = carroService.getCarro(id);
		
		assertEquals("nome", carro.getNome());
		// Atualiza carro
		carro.setNome("carro UPDATE");
		carroService.save(carro);

		// Busca o carro novamente, vai estar atualizado
		carro = carroService.getCarro(id);
		assertEquals("carro UPDATE", carro.getNome());
		
		// Deleta o carro
		carroService.delete(id);
		
		// busca novamente
		carro = carroService.getCarro(id);
		assertTrue(carro == null);
	}
}
