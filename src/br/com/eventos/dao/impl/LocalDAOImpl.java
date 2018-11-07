package br.com.eventos.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.eventos.dao.GenericDAOException;
import br.com.eventos.model.Local;

public class LocalDAOImpl {
	private static final String JDBC_URL = "jdbc:mariadb://localhost:3306/eventos";
	private static final String JDBC_USER = "root";
	private static final String JDBC_PASS = "testemaria1";
	private Connection con;
	
	public LocalDAOImpl() throws GenericDAOException {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			con = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
		}catch(SQLException | ClassNotFoundException e) {
			throw new GenericDAOException(e);
		}
	}

	
	public void adicionar(Local l) throws GenericDAOException {	
		String sql = "INSERT INTO tblocal (nome, telefone, capacidade, areaFumante, endereco, vip) "+
				"VALUES (?, ?, ?, ?, ?, ?)";
		try {
		PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, l.getNome());
			pstmt.setString(2, l.getTelefone());
			pstmt.setInt(3, l.getCapacidade());
			pstmt.setBoolean(4, l.isAreaFumante());
			pstmt.setString(5, l.getEndereco());
			pstmt.setBoolean(6, l.isVip());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new GenericDAOException(e);
		}
		
		
	}

	public List<Local> pesquisaLocal(String local) throws GenericDAOException {
		List<Local> lista = new ArrayList<>();
		String sql = "SELECT * FROM tblocal WHERE nome like ?";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + local + "%");
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) { 
				Local l = new Local();
				l.setIdLocal(rs.getInt("idLocal"));
				l.setNome(rs.getString("nome"));
				l.setTelefone(rs.getString("telefone"));
				l.setCapacidade(rs.getInt("capacidade"));
				l.setAreaFumante(rs.getBoolean("areaFumante"));
				l.setEndereco(rs.getString("endereco"));
				l.setVip(rs.getBoolean("vip"));
				lista.add(l);
			}
		} catch (SQLException e) {
			throw new GenericDAOException( e );
		}
		return lista;
	}
	
	
	
}
