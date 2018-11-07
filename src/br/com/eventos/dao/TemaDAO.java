package br.com.eventos.dao;

import java.util.List;

import br.com.eventos.model.Tema;

public interface TemaDAO {
	public void adicionar(Tema t) throws GenericDAOException;
	public List<Tema> pesquisarporTema(String tema) throws GenericDAOException;
}
