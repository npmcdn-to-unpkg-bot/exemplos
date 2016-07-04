package pacote.modelo;

import java.util.ArrayList;
import java.util.List;

public class OperadoraUtil {

	private static List<Operadora> operadoras;

	public static List<Operadora> geraOperadoras() {
		Operadora oi = new Operadora("Oi", 10, "Celular", 1);
		Operadora vivo = new Operadora("Vivo", 11, "Celular", 2);
		Operadora claro = new Operadora("Claro", 12, "Celular", 3);
		Operadora gvt = new Operadora("GVT", 13, "Fixo", 1);
		Operadora tim = new Operadora("Tim", 14, "Celular", 2);
		Operadora embratel = new Operadora("Embratel", 15, "Fixo", 4);
		
		operadoras = new ArrayList<>();
		operadoras.add(oi);
		operadoras.add(gvt);
		operadoras.add(vivo);
		operadoras.add(claro);
		operadoras.add(embratel);
		operadoras.add(tim);
		
		return operadoras;
	}
}
