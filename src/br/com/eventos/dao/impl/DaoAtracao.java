package br.com.eventos.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import br.com.eventos.dao.DaoTemplate;
import br.com.eventos.model.Atracao;

public class DaoAtracao implements DaoTemplate<Atracao>{
	
	private Connection con;
	
	public ResultSet executeQuery(String sql, Statement stmt) throws SQLException {
		ResultSet rs = stmt.executeQuery(sql);
		return rs;
	}
	
	public DaoAtracao() {
		try {		
			Class.forName("com.mysql.jdbc.Driver");
			// Class.forName("org.mariadb.jdbc.Driver");
			String urldb = "jdbc:mysql://localhost:3306/eventos";
			con = DriverManager.getConnection(urldb, "root", "carlos123");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	@Override
	public void inserir(Atracao t) throws DAOExcep {
		try {
			String sql = "INSERT INTO tbAtracao (nome, descricao) VALUES (?, ?)";
			PreparedStatement stmt = con.prepareStatement( sql );
			stmt.setString(1, t.getNome());
			stmt.setString(2, t.getDescricao());
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new DAOExcep( e );
		}
	}

	@Override
	public ArrayList<Atracao> listar() throws DAOExcep {
		ArrayList<Atracao> array = new ArrayList<>();
		try {		
			ResultSet rs = executeQuery("select * from tbAtracao;", con.createStatement());
			while (rs.next()) {
				Atracao a = new Atracao();
				a.setIdAtracao(rs.getInt(1));
				a.setNome(rs.getString(2));
				a.setDescricao(rs.getString(3));
				array.add(a);
			}
			return array;
		} catch (SQLException e) {
			throw new DAOExcep( e );
		}	
	}
	
	public Atracao listarById(int id) throws DAOExcep {
		Atracao atracao = new Atracao();
		try {

			String sql = "select * from tbAtracao where id = ?; ";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();

			atracao.setIdAtracao(rs.getInt(1));
			atracao.setNome(rs.getString(2));
			atracao.setDescricao(rs.getString(3));

			return atracao;
		} catch (SQLException e) {
			throw new DAOExcep( e );
		}
	}

	@Override
	public void excluir(int id) throws DAOExcep {
		
		try {
			
			String sql = "DELETE FROM tbAtracao WHERE id = ?";
			PreparedStatement stmt;
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new DAOExcep( e );
		}

		
	}
}
