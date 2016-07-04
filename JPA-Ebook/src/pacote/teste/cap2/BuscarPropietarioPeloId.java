package pacote.teste.cap2;

import javax.persistence.EntityManager;

import pacote.modelo.Acessorio;
import pacote.modelo.Propietario;
import pacote.modelo.Telefone;
import pacote.modelo.Veiculo;
import pacote.util.JpaUtil;

public class BuscarPropietarioPeloId {

    public static void main(String[] args) {

	EntityManager manager = JpaUtil.getEntityManager();

	Propietario prop = manager.find(Propietario.class, 1L);

	System.out.println("Propietario: " + prop.getNome());
	for (Veiculo v : prop.getVeiculos()) {
	    System.out.println(v);

	    System.out.print("Acessorios: ");
	    for (Acessorio acessorio : v.getAcessorios()) {
		System.out.print("/ " + acessorio.getDescricao() + " /");
	    }
	    System.out.println();
	}

	manager.close();
	JpaUtil.close();
    }
}
