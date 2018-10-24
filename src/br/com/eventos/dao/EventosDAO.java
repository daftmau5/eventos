package br.com.eventos.dao;

import java.util.List;

import br.com.eventos.dao.impl.DAOExcep;

public interface EventosDAO<Object> {
	
	void adicionar(Object o) throws DAOExcep;
	List<Object> listar() throws DAOExcep;
	void excluir(int id) throws DAOExcep;
	Object pesquisarPorId(long id) throws DAOExcep;
}
