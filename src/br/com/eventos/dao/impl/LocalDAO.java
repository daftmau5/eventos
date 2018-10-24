package br.com.eventos.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import br.com.eventos.dao.EventosDAO;
import br.com.eventos.model.Local;

public class LocalDAO implements EventosDAO<Local>{
	
	private EntityManagerFactory emf;
	
	public LocalDAO() { 
		emf = Persistence.createEntityManagerFactory("EVENTOS");
	}

	@Override
	public void adicionar(Local local) throws DAOExcep {
		try {
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();
			em.persist(local);
			em.getTransaction().commit();
			em.close();	
		}catch(Exception e) {
			throw new DAOExcep(e);
		}
	}

	@Override
	public List<Local> listar() throws DAOExcep {
		List<Local> local = new ArrayList<>();
		String sql = "select * from local";
		try {
			EntityManager em = emf.createEntityManager();
			TypedQuery<Local> tpdQry = em.createQuery(sql, Local.class);
			local = tpdQry.getResultList();
			em.close();
		}catch(Exception e) {
			throw new DAOExcep(e);
		}
		return local;
	}

	@Override
	public Local pesquisarPorId(long id) throws DAOExcep {
		Local l = new Local();
		try {
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();
			l = em.find(Local.class, id);
			em.getTransaction().commit();
			em.close();
		}catch(Exception e) {
			throw new DAOExcep(e);
		}
		return l;
	}
	
	@Override
	public void excluir(int id) throws DAOExcep {
		try {
			EntityManager em = emf.createEntityManager();
			Local local = em.find(Local.class, id);
			em.getTransaction().begin();
			em.remove(local);
			em.getTransaction().commit();
			em.close();
		}catch(Exception e){
			throw new DAOExcep(e);
		}
	}

}
