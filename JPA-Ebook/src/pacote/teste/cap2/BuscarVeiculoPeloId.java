package pacote.teste.cap2;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.persistence.EntityManager;

import pacote.modelo.Acessorio;
import pacote.modelo.Veiculo;
import pacote.util.JpaUtil;

public class BuscarVeiculoPeloId {

	public static void main(String[] args) {
		
		EntityManager manager = JpaUtil.getEntityManager();
		
		Veiculo veiculo = manager.getReference(Veiculo.class, 1L);
		
		System.out.println(veiculo);
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		System.out.println(format.format(veiculo.getDataCadastro().getTime()));
		System.out.println(veiculo.getPropietario().getNome());
		
		System.out.println("Acessorios: ");
		for (Acessorio acessorio: veiculo.getAcessorios()) {
		    System.out.println(acessorio.getDescricao());
		    
		    System.out.println("######Veiculos que possuem esse acessorio");
		    System.out.println(acessorio);
		    for (Veiculo veiculo2 : acessorio.getVeiculos()) {
			System.out.println(veiculo2);
		    }
		}
		
		manager.close();
		JpaUtil.close();
	}
}
