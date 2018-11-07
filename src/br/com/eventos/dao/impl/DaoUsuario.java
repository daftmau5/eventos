package br.com.eventos.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import br.com.eventos.dao.DaoTemplate;
import br.com.eventos.model.Usuario;

public class DaoUsuario implements DaoTemplate<Usuario>{
	
	private Connection con;
	private ResultSet rs;
	
	public ResultSet executeQuery(String sql, Statement stmt) throws SQLException {
		ResultSet rs = stmt.executeQuery(sql);
		return rs;
	}
	
	public DaoUsuario() {
		try {		
			Class.forName("org.postgresql.Driver");
			//Class.forName("org.mariadb.jdbc.Driver");
			String urldb = "jdbc:postgresql://localhost:5432/eventos?allowMultiQueries=true";
			con = DriverManager.getConnection(urldb, "postgres", "12345");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public Usuario checkLogin(String login, String senha){
		System.out.println("A");
		ArrayList<Usuario> usuarios = new ArrayList<>();
		try {
			String sql = "select id, login, email, senha, cpf, endereco, telefone from usuario where login = ? and senha = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, login);
			stmt.setString(2, senha);
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				Usuario u = new Usuario();
				u.setIdUsuario(rs.getInt(1));
				u.setLogin(rs.getString(2));
				u.setEmail(rs.getString(3));
				u.setSenha(rs.getString(4));
				u.setCPF(rs.getString(5));
				u.setEndereco(rs.getString(6));
				u.setTelefone(rs.getString(7));
				usuarios.add(u);
				System.out.println("Passou");
			}
			
			if (usuarios.size() < 1) {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usuarios.get(0);

	}
	
	@Override
	public void inserir(Usuario u) {
		try {
			String sql = "INSERT INTO usuario (login, email, senha, cpf, endereco, telefone) VALUES (?, ?, ?, ?, ?, ?)";
			PreparedStatement stmt = con.prepareStatement( sql );
			stmt.setString(1, u.getLogin());
			stmt.setString(2, u.getEmail());
			stmt.setString(3, u.getSenha());
			stmt.setString(4, u.getCPF());
			stmt.setString(5, u.getEndereco());
			stmt.setString(6, u.getTelefone());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}

	@Override
	public ArrayList<Usuario> listar() {
		
		ArrayList<Usuario> array = new ArrayList<>();
		try {		
			ResultSet rs = executeQuery("select * from usuario;", con.createStatement());
			while (rs.next()) {
				Usuario a = new Usuario();
				a.setIdUsuario(rs.getInt(1));
				a.setLogin(rs.getString(2));
				a.setEmail(rs.getString(3));
				a.setSenha(rs.getString(4));
				a.setCPF(rs.getString(5));
				a.setEndereco(rs.getString(6));
				a.setTelefone(rs.getString(7));
				array.add(a);
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
			
			String sql = "DELETE FROM usuario WHERE id = ?";
			PreparedStatement stmt;
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
