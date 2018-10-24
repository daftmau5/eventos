package br.com.eventos.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import br.com.eventos.dao.EventosDAO;
import br.com.eventos.model.Tema;

public class TemaDAO implements EventosDAO<Tema> {
	
	private EntityManagerFactory emf;

	public TemaDAO() {
		emf = Persistence.createEntityManagerFactory("EVENTOS");
	}

	@Override
	public void adicionar(Tema t) throws DAOExcep {
		try {
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();
			em.persist(t);
			em.getTransaction().commit();
			em.close();
		}catch(Exception e) {
			throw new DAOExcep(e);
		}
	}

	@Override
	public List<Tema> listar() throws DAOExcep {
		List<Tema> tema = new ArrayList<>();
		String sql = "select * from tema";
		try {
			EntityManager em = emf.createEntityManager();
			TypedQuery<Tema> tpdQry = em.createQuery(sql, Tema.class);
			tema = tpdQry.getResultList();
			em.close();
		}catch(Exception e) {
			throw new DAOExcep(e);
		}
		return tema;
	}

	@Override
	public Tema pesquisarPorId(long id) throws DAOExcep {
		Tema t = new Tema();
		try {
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();
			t = em.find(Tema.class, id);
			em.getTransaction().commit();
			em.close();
		}catch(Exception e) {
			throw new DAOExcep(e);
		}
		return t;
	}

	@Override
	public void excluir(int id) throws DAOExcep {
		try {
			EntityManager em = emf.createEntityManager();
			Tema tema = em.find(Tema.class, id);
			em.getTransaction().begin();
			em.remove(tema);
			em.getTransaction().commit();
			em.close();
		}catch(Exception e) {
			throw new DAOExcep(e);
		}
	}

}
