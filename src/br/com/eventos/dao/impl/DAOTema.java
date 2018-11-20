package br.com.eventos.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.eventos.dao.GenericDAOException;
import br.com.eventos.model.Tema;

public class DAOTema {

	private Connection con;

	public ResultSet executeQuery(String sql, Statement stmt) throws SQLException {
		ResultSet rs = stmt.executeQuery(sql);
		return rs;
	}

	public DAOTema() {
		try {
			ConnectionFactory conn = new ConnectionFactory();
			con = conn.getCon();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void adicionar(Tema t) throws GenericDAOException {
		String sql = "INSERT INTO tbTema (descricao) " + "VALUES (?)";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, t.getDescricao());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new GenericDAOException(e);
		}
	}

	public void inserir(Tema t) throws DAOExcep {
		try {
			String sql = "INSERT INTO tbTema (descricao) VALUES (?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, t.getDescricao());
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new DAOExcep(e);
		}
	}

	public void salvar(long id, Tema t) throws GenericDAOException {
		String sql = "UPDATE tbTema SET descricao = ? WHERE idTema = ?";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, t.getDescricao());
			pstmt.setLong(2, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new GenericDAOException(e);
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
			throw new DAOExcep(e);
		}
	}

	public void remover(long id) throws GenericDAOException {
		String sql = "DELETE FROM tbTema WHERE idTema = ?";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new GenericDAOException(e);
		}

	}

	public Tema listarById(int id) throws DAOExcep {
		Tema tema = new Tema();
		try {

			String sql = "select * from tbTema where idTema = ?;";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				tema.setIdTema(rs.getInt(1));
				tema.setDescricao(rs.getString(2));
			}

			return tema;
		} catch (SQLException e) {
			throw new DAOExcep(e);
		}
	}

	public Tema pesquisarporId(long id) throws GenericDAOException {
		Tema t = new Tema();
		String sql = "SELECT * FROM tbTema WHERE idTema = ?";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				t.setIdTema(rs.getInt("idTema"));
				t.setDescricao(rs.getString("descricao"));
			}
		} catch (SQLException e) {
			throw new GenericDAOException(e);
		}

		return t;

	}

	public void excluir(int id) throws DAOExcep {

		try {

			String sql = "DELETE FROM tbTema WHERE idTema = ?";
			PreparedStatement stmt;
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new DAOExcep(e);
		}

	}

	public List<Tema> pesquisarporTema(String tema) throws GenericDAOException {
		List<Tema> lista = new ArrayList<>();
		String sql = "SELECT * FROM tbTema WHERE descricao like ?";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + tema + "%");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Tema t = new Tema();
				t.setIdTema(rs.getInt("idTema"));
				t.setDescricao(rs.getString("descricao"));
				lista.add(t);
			}
		} catch (SQLException e) {
			throw new GenericDAOException(e);
		}
		return lista;
	}
}
