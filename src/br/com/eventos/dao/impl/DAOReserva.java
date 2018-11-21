package br.com.eventos.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import br.com.eventos.model.Reserva;

public class DAOReserva {

	private Connection con;

	private DAOEvento daoEvento;

	private DAOUsuario daoUsuario;

	public ResultSet executeQuery(String sql, Statement stmt) throws SQLException {
		ResultSet rs = stmt.executeQuery(sql);
		return rs;
	}

	public DAOReserva() {
		try {
			ConnectionFactory conn = new ConnectionFactory();
			con = conn.getCon();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void inserir(Reserva r) {
		try {
			String sql = "INSERT INTO tbReserva (modoPagamento, status, usuario_id, evento_id) VALUES (?, ?, ?, ?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			// stmt.setDate(1, new java.sql.Date(r.getData().getTime()));
			stmt.setString(1, r.getModoPagamento());
			stmt.setString(2, r.getStatus());
			stmt.setInt(3, r.getUsuario().getIdUsuario());
			stmt.setInt(4, r.getEvento().getIdEvento());
			stmt.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public ArrayList<Reserva> listar(int id)  throws SQLException {
		try {
			
		ArrayList<Reserva> array = new ArrayList<>();

		String sql = "select * from tbReserva where usuario_id = ?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, id);
		ResultSet rs = pstmt.executeQuery();
		
		DAOEvento eventoDao = new DAOEvento();
		DAOUsuario usuarioDao = new DAOUsuario();

		while (rs.next()) {

			Reserva r = new Reserva();
		
			r.setIdReserva(rs.getInt(1));

				
				r.setEvento(eventoDao.listarById(rs.getInt(3)));
				r.setStatus(rs.getString(5));
			array.add(r);
		}
		return array;
		} catch (DAOExcep e) {
			e.printStackTrace();
			return null;
		}
	

	}

	public void excluir(int id) {

		try {

			String sql = "DELETE FROM tbReserva WHERE id = ?";
			PreparedStatement stmt;
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
