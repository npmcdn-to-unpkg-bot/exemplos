package pacote.teste.cap2;

import javax.persistence.Persistence;

public class CriaTabela {

	public static void main(String[] args) {
		
		(Persistence.createEntityManagerFactory("Algaworks-PU")).close();
		
	}
}
