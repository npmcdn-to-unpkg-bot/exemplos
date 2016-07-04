package pacote.stream;

public enum Enum {
	// As constantes devem ser declaradas no inicio da classe e usar os
	// construtores disponiveis
	COMPACTAR("Compactacao", 0), DESCOMPACTAR("Descompactacao", 1);

	// Os campos devem ser declarados como final
	private final String nome;
	private final int numero;

	// Construtores não devem ter especificador de acesso e devem suportar as
	// constantes declaradas
	Enum(String n, int i) {
		nome = n;
		numero = i;
	}

	// Os metodos nao devem ser sobrepostos
	public String getNome() {
		return nome;
	}

	public int getNumero() {
		return numero;
	}

	@Override
	public String toString() {
		return nome + "(" + numero + ")";
	}
}
