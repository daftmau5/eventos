package br.com.eventos.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import br.com.eventos.model.Tema;

public class DaoTema {

	private Connection con;

	public ResultSet executeQuery(String sql, Statement stmt) throws SQLException {
		ResultSet rs = stmt.executeQuery(sql);
		return rs;
	}

	public DaoTema() {
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

	public void inserir(Tema t) throws DAOExcep {
		try {
			String sql = "INSERT INTO tbTema (descricao) VALUES (?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, t.getDescricao());
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new DAOExcep( e );
		}
	}

	public ArrayList<Tema> listar() throws DAOExcep {
		ArrayList<Tema> array = new ArrayList<>();
		try {
			ResultSet rs = executeQuery("select * from tbTema;", con.createStatement());
			while (rs.next()) {
				Tema t = new Tema();
				t.setIdTema(rs.getInt(1));
				t.setDescricao(rs.getString(2));
				array.add(t);
			}
			return array;
		} catch (SQLException e) {
			throw new DAOExcep( e );
		}
	}

	public Tema listarById(int id) throws DAOExcep {
		Tema tema = new Tema();
		try {

			String sql = "select * from tbTema where id = ?;";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();

			tema.setIdTema(rs.getInt(1));
			tema.setDescricao(rs.getString(2));

			return tema;
		} catch (SQLException e) {
			throw new DAOExcep( e );
		}
	}

	public void excluir(int id) throws DAOExcep {
		
		try {
			
			String sql = "DELETE FROM tbTema WHERE id = ?";
			PreparedStatement stmt;
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new DAOExcep( e );
		}

	}
}
