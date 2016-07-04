package pacote.teste.cap2;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import pacote.modelo.Acessorio;
import pacote.modelo.Propietario;
import pacote.modelo.TipoCombustivel;
import pacote.modelo.Veiculo;
import pacote.util.JpaUtil;

public class PersistirVeiculo {

    public static void main(String[] args) {
	EntityManager manager = JpaUtil.getEntityManager();
	EntityTransaction transaction = manager.getTransaction();
	transaction.begin();

	Propietario propietario = new Propietario();
	propietario.setNome("Antonio");
	propietario.setEmail("antonio@gmail.com");
	propietario.setTelefone("9999-0000");
	manager.persist(propietario);

	Acessorio banco = new Acessorio("Banco");
	Acessorio tetoSolar = new Acessorio("Teto de Solar");
	Acessorio arCondicionado = new Acessorio("Ar Condicionado");
	Acessorio direcao = new Acessorio("Direção Hidráulica");

	manager.persist(banco);
	manager.persist(tetoSolar);
	manager.persist(arCondicionado);
	manager.persist(direcao);

	Veiculo veiculo = new Veiculo();
	veiculo.setFabricante("Camaro");
	veiculo.setModelo("Gol");
	veiculo.setAnoFabricacao(1012);
	veiculo.setAnoModelo(1014);
	veiculo.setValor(new BigDecimal(1400));
	veiculo.setTipoCombustivel(TipoCombustivel.ALCOOL);
	veiculo.setDataCadastro(Calendar.getInstance());
	veiculo.setPropietario(propietario);
	veiculo.getAcessorios().add(direcao);
	veiculo.getAcessorios().add(banco);
	veiculo.getAcessorios().add(arCondicionado);

	manager.persist(veiculo);

	Veiculo veiculo2 = new Veiculo();
	veiculo2.setFabricante("Voukisvaguem");
	veiculo2.setModelo("Gol");
	veiculo2.setAnoFabricacao(1012);
	veiculo2.setAnoModelo(1014);
	veiculo2.setValor(new BigDecimal(1400));
	veiculo2.setTipoCombustivel(TipoCombustivel.ALCOOL);
	veiculo2.setDataCadastro(Calendar.getInstance());
	veiculo2.setPropietario(propietario);
	veiculo2.getAcessorios().add(direcao);

	manager.persist(veiculo2);

	transaction.commit();
	manager.close();
	JpaUtil.close();
    }
}
