package br.com.eventos.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.eventos.dao.GenericDAOException;
import br.com.eventos.dao.TemaDAO;
import br.com.eventos.model.Tema;;

public class TemaDAOImpl implements TemaDAO {

	private static final String JDBC_URL = "jdbc:mariadb://localhost:3306/eventos";
	private static final String JDBC_USER = "root";
	private static final String JDBC_PASS = "testemaria1";
	private Connection con;
	
	public TemaDAOImpl() throws GenericDAOException {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			con = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
		}catch(SQLException | ClassNotFoundException e) {
			throw new GenericDAOException(e);
		}
	}

	@Override
	public void adicionar(Tema t) throws GenericDAOException {	
		String sql = "INSERT INTO tbtema (descricao) "+
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
		String sql = "SELECT * FROM tbtema WHERE descricao like ?";
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
	
	
	
}


