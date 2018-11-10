package br.com.eventos.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import br.com.eventos.model.Local;

public class DaoLocal {
	
	private Connection con;
	
	public ResultSet executeQuery(String sql, Statement stmt) throws SQLException {
		ResultSet rs = stmt.executeQuery(sql);
		return rs;
	}
	
	public DaoLocal() {
		try {		
			Class.forName("org.mariadb.jdbc.Driver");
			// Class.forName("org.mariadb.jdbc.Driver");
			String urldb = "jdbc:mariadb://sql10.freemysqlhosting.net/sql10264413?user=sql10264413&password=cvbBJqBPmf";
			con = DriverManager.getConnection(urldb);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public void inserir(Local l) throws DAOExcep {
		try {
			String sql = "INSERT INTO tbLocal (nome, telefone, capacidade, areafumante, avaliacao, endereco, vip) VALUES (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement stmt = con.prepareStatement( sql );
			stmt.setString(1, l.getNome());
			stmt.setString(2, l.getTelefone());
			stmt.setInt(3, l.getCapacidade());
			stmt.setBoolean(4, l.isAreaFumante());
			stmt.setInt(5, l.getAvaliacao());
			stmt.setString(6, l.getEndereco());
			stmt.setBoolean(7, l.isVip());
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new DAOExcep( e );
		}
	}

	
	public ArrayList<Local> listar() throws DAOExcep {
		ArrayList<Local> array = new ArrayList<>();
		try {		
			ResultSet rs = executeQuery("select * from tbLocal;", con.createStatement());
			while (rs.next()) {
				Local a = new Local();
				a.setIdLocal(rs.getInt(1));
				a.setNome(rs.getString(2));
				a.setTelefone(rs.getString(3));
				a.setCapacidade(rs.getInt(4));
				a.setAreaFumante(rs.getBoolean(5));
				a.setEndereco(rs.getString(6));
				a.setVip(rs.getBoolean(7));
				array.add(a);
			}
			return array;
		} catch (SQLException e) {
			throw new DAOExcep( e );
		}	
	}
	
	public Local listarById(int id) throws DAOExcep {
		Local local = new Local();
		try {

			String sql = "select * from tbLocal where id = ?;";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();

			local.setIdLocal(rs.getInt(1));
			local.setNome(rs.getString(2));
			local.setTelefone(rs.getString(3));
			local.setCapacidade(rs.getInt(4));
			local.setAreaFumante(rs.getBoolean(5));
			local.setAvaliacao(rs.getInt(6));
			local.setEndereco(rs.getString(7));
			local.setVip(rs.getBoolean(8));

			return local;
		} catch (SQLException e) {
			throw new DAOExcep( e );
		}
	}

	public void excluir(int id) throws DAOExcep {

		try {
			
			String sql = "DELETE FROM tbLocal WHERE id = ?";
			PreparedStatement stmt;
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new DAOExcep( e );
		}
		
	}

}
