package br.com.eventos.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.eventos.dao.EventosDAO;
import br.com.eventos.model.Usuario;

public class UsuarioDAO implements EventosDAO<Usuario> {

	private EntityManagerFactory emf;

	public UsuarioDAO() {
		emf = Persistence.createEntityManagerFactory("EVENTOS");
	}	
	
	@Override
	public void adicionar(Usuario o) throws DAOExcep {
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
	public List<Usuario> listar() {
		return null;
	}
	
	@Override
	public Usuario pesquisarPorId(long id) throws DAOExcep {
		Usuario u = new Usuario();
		try {
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();
			u = em.find(Usuario.class, id);
			em.getTransaction().commit();
			em.close();
		}catch(Exception e) {
			throw new DAOExcep(e);
		}
		return u;
	}

	@Override
	public void excluir(int id) {
	}
}
