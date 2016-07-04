package pacote.repository;

import java.util.List;

import pacote.modelo.Editora;

public interface Repository<T> {

	void persistir(T t);

	void atualizar(T t);

	void remover(T t);

	T busca(Long id);

	List<T> buscaTodas();
}