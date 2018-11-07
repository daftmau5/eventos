package br.com.eventos.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import br.com.eventos.dao.DaoTemplate;
import br.com.eventos.model.Reserva;

public class DaoReserva implements DaoTemplate<Reserva> {

	private Connection con;
	
	private DaoEvento daoEvento;
	
	private DaoUsuario daoUsuario;
	
	public ResultSet executeQuery(String sql, Statement stmt) throws SQLException {
		ResultSet rs = stmt.executeQuery(sql);
		return rs;
	}

	public DaoReserva() {
		try {
			Class.forName("org.postgresql.Driver");
			// Class.forName("org.mariadb.jdbc.Driver");
			String urldb = "jdbc:postgresql://localhost:5432/eventos?allowMultiQueries=true";
			con = DriverManager.getConnection(urldb, "postgres", "12345");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void inserir(Reserva r) {
		try {
			String sql = "INSERT INTO reserva (data, modoPagamento, status, usuario_id, evento_id) VALUES (?, ?, ?, ?, ?, ?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setDate(1, new java.sql.Date(r.getData().getTime()));
			stmt.setNString(2, r.getModoPagamento());
			stmt.setString(3, r.getStatus());
			stmt.setInt(4, r.getUsuario().getIdUsuario());
			stmt.setInt(5, r.getEvento().getIdEvento());
			stmt.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	// TO-DO Eduardo 
	@Override
	public ArrayList<Reserva> listar() {
		ArrayList<Reserva> array = new ArrayList<>();
		try {		
			ResultSet rs = executeQuery("select * from reserva;", con.createStatement());
			while (rs.next()) {

				Reserva r = new Reserva();
							
				array.add(r);
			}
			return array;
		} catch (SQLException e) {
			e.printStackTrace();
			return array;
		}	
	}

	@Override
	public void excluir(int id) {
		
		try {
			
			String sql = "DELETE FROM reserva WHERE id = ?";
			PreparedStatement stmt;
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
