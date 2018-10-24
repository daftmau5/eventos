package br.com.eventos.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import br.com.eventos.dao.EventosDAO;
import br.com.eventos.model.Atracao;

public class AtracaoDAO implements EventosDAO<Atracao>{
	
	private EntityManagerFactory emf;
	
	public AtracaoDAO(){ 
		emf = Persistence.createEntityManagerFactory("EVENTOS");
	}

	@Override
	public void adicionar(Atracao a) throws DAOExcep {
		try {
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();
			em.persist(a);
			em.getTransaction().commit();
			em.close();
		}catch (Exception e){
			throw new DAOExcep(e);
		}
	}

	@Override
	public List<Atracao> listar() throws DAOExcep {
		List<Atracao> atracao = new ArrayList<>();
		String sql = "select * from atracao";
		try {
			EntityManager em = emf.createEntityManager();
			TypedQuery<Atracao> tpdQry = em.createQuery(sql, Atracao.class);
			atracao = tpdQry.getResultList();
			em.close();	
		}catch(Exception e) {
			throw new DAOExcep(e);
		}
		return atracao;
	}

	@Override
	public Atracao pesquisarPorId(long id) throws DAOExcep{
		Atracao a = new Atracao();
		try {
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();
			a = em.find(Atracao.class, id);
			em.getTransaction().commit();
			em.close();
		}catch(Exception e ) {
			throw new DAOExcep(e);
		}
		return a;
	}
	
	@Override
	public void excluir(int id) throws DAOExcep {
		try {
			EntityManager em = emf.createEntityManager();
			Atracao atracao = em.find(Atracao.class, id);
			em.getTransaction().begin();
			em.remove(atracao);
			em.getTransaction().commit();
			em.close();	
		}catch(Exception e){
			throw new DAOExcep(e);
		}
	}
}