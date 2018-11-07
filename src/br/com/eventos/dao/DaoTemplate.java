package br.com.eventos.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.eventos.dao.impl.DAOExcep;

public interface DaoTemplate<Object> {

	public void inserir(Object o) throws DAOExcep;

	public ArrayList<Object> listar() throws DAOExcep;
	
	public void excluir(int id) throws DAOExcep;

}
