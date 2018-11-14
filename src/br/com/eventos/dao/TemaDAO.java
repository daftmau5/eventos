package br.com.eventos.dao;

import java.util.List;

import br.com.eventos.model.Tema;

public interface TemaDAO {
	public void adicionar(Tema t) throws GenericDAOException;
	public List<Tema> pesquisarporTema(String tema) throws GenericDAOException;
	public void remover(long parseLong) throws GenericDAOException;
	public Tema pesquisarporId(long id) throws GenericDAOException;
	public void salvar(long id, Tema t) throws GenericDAOException;

}
