package br.com.eventos.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import br.com.eventos.dao.EventosDAO;
import br.com.eventos.model.Evento;

public class EventoDAO implements EventosDAO<Evento>{

	private EntityManagerFactory emf;

	public EventoDAO() {
		emf = Persistence.createEntityManagerFactory("EVENTOS");
	}

	@Override
	public void adicionar(Evento o) throws DAOExcep {
		try {
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();
			em.persist(o);
			em.getTransaction().commit();
			em.close();
		}catch(Exception e) {
			throw new DAOExcep(e);
		}
	}

	@Override
	public List<Evento> listar() throws DAOExcep {
		List<Evento> evento = new ArrayList<>();
		String sql = "select * from evento";
		try {
			EntityManager em = emf.createEntityManager();
			TypedQuery<Evento> tpdQry = em.createQuery(sql, Evento.class);
			evento = tpdQry.getResultList();
			em.close();
		}catch(Exception e) {
			throw new DAOExcep(e);
		}
		return evento;
	}

	@Override
	public Evento pesquisarPorId(long id) throws DAOExcep {
		Evento e = new Evento();
		try {
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();
			e = em.find(Evento.class, id);
			em.getTransaction().commit();
			em.close();
		}catch(Exception i) {
			throw new DAOExcep(i);
		}
		return e;
	}
	
	@Override
	public void excluir(int id) throws DAOExcep {
		try {
			EntityManager em = emf.createEntityManager();
			Evento evento = em.find(Evento.class, id);
			em.getTransaction().begin();
			em.remove(evento);
			em.getTransaction().commit();
			em.close();
		}catch(Exception e) {
			throw new DAOExcep(e);
		}
	}
	
}
