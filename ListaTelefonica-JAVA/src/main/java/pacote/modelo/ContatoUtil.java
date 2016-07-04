package pacote.modelo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ContatoUtil {

	private static List<Contato> contatos;

	public static List<Contato> geraContatos() {
		Operadora oi = new Operadora("Oi", 10, "Celular", 1);
		Operadora vivo = new Operadora("Vivo", 11, "Celular", 2);
		Operadora claro = new Operadora("Claro", 12, "Celular", 3);
		Operadora gvt = new Operadora("GVT", 13, "Fixo", 1);

		Contato c1 = new Contato("Antônio", "9898-9898", Calendar.getInstance(), oi);
		Contato c2 = new Contato("Lucas", "3333-4444", Calendar.getInstance(), vivo);
		Contato c3 = new Contato("Augusto", "3223-2357", Calendar.getInstance(), claro);
		Contato c4 = new Contato("Zuleide", "6247-9897", Calendar.getInstance(), gvt);

		contatos = new ArrayList<>();
		contatos.add(c1);
		contatos.add(c2);
		contatos.add(c3);
		contatos.add(c4);

		return contatos;
	}

}
