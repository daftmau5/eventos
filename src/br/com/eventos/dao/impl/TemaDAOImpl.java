package br.com.eventos.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.eventos.dao.GenericDAOException;
import br.com.eventos.dao.TemaDAO;
import br.com.eventos.model.Tema;;

public class TemaDAOImpl implements TemaDAO {

	private Connection con;
	
	public TemaDAOImpl() throws GenericDAOException {
		try {
			ConnectionFactory conn = new ConnectionFactory();
			con = conn.getCon();
		}catch(Exception e) {
			throw new GenericDAOException(e);
		}
	}

	@Override
	public void adicionar(Tema t) throws GenericDAOException {	
		String sql = "INSERT INTO tbTema (descricao) "+
				"VALUES (?)";
		try {
		PreparedStatement pstmt = con.prepareStatement(sql);
		
			
			pstmt.setString(1, t.getDescricao());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new GenericDAOException(e);
		}
		
		
	}

	@Override
	public List<Tema> pesquisarporTema(String tema) throws GenericDAOException {
		List<Tema> lista = new ArrayList<>();
		String sql = "SELECT * FROM tbTema WHERE descricao like ?";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + tema + "%");
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) { 
				Tema t = new Tema();
				t.setIdTema(rs.getInt("idTema"));
				t.setDescricao(rs.getString("descricao"));
				lista.add(t);
			}
		} catch (SQLException e) {
			throw new GenericDAOException( e );
		}
		return lista;
	}

	@Override
	public void remover(long id) throws GenericDAOException {
		String sql = "DELETE FROM tbTema WHERE idTema = ?";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, id);
			pstmt.executeUpdate();
		}catch(SQLException e) {
			throw new GenericDAOException(e);
		}
	
	}
	
	
	@Override
	public Tema pesquisarporId(long id) throws GenericDAOException {
		Tema t = new Tema();
		String sql = "SELECT * FROM tbTema WHERE idTema = ?";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setLong(1, id);
				ResultSet rs = pstmt.executeQuery();
				if(rs.next()) {
					t.setIdTema(rs.getInt("idTema"));
					t.setDescricao(rs.getString("descricao"));
				}
		}catch(SQLException e) {
			throw new GenericDAOException(e);	
		}
			
		return t;
		
	}

	
	public void salvar(long id, Tema t) throws GenericDAOException {
		String sql = "UPDATE tbTema SET descricao = ? WHERE idTema = ?";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, t.getDescricao());
			pstmt.setLong(2, id);
			pstmt.executeUpdate();
		}catch(SQLException e) {
			throw new GenericDAOException(e);
		}
	}
	
	
	
	
}


