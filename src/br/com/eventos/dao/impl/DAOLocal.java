package br.com.eventos.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.eventos.dao.GenericDAOException;
import br.com.eventos.model.Local;

public class DAOLocal {

	private Connection con;

	public ResultSet executeQuery(String sql, Statement stmt) throws SQLException {
		ResultSet rs = stmt.executeQuery(sql);
		return rs;
	}

	public DAOLocal() {
		try {
			ConnectionFactory conn = new ConnectionFactory();
			con = conn.getCon();
        } catch (Exception e) {
            e.printStackTrace();
        } 
	}

	public void inserir(Local l) throws DAOExcep {
		try {
			String sql = "INSERT INTO tbLocal (nome, telefone, capacidade, areafumante, avaliacao, endereco, vip) VALUES (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, l.getNome());
			stmt.setString(2, l.getTelefone());
			stmt.setInt(3, l.getCapacidade());
			stmt.setBoolean(4, l.isAreaFumante());
			stmt.setInt(5, l.getAvaliacao());
			stmt.setString(6, l.getEndereco());
			stmt.setBoolean(7, l.isVip());
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new DAOExcep(e);
		}
	}
	
	public void adicionar(Local l) throws GenericDAOException {	
		String sql = "INSERT INTO tbLocal (nome, telefone, capacidade, areaFumante, endereco, vip) "+
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
	
	public void remover(long id) throws GenericDAOException{
		String sql = "DELETE FROM tblocal WHERE idlocal = ?";
		
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, id);
			pstmt.executeUpdate();
		}catch(SQLException e){
			throw new GenericDAOException(e);
		}
	}
	
	
	public Local pesquisarporId(long id) throws GenericDAOException {
		Local l = new Local();
		String sql = "SELECT * FROM tbLocal WHERE idLocal = ?";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setLong(1, id);
				ResultSet rs = pstmt.executeQuery();
				if(rs.next()) {
					l.setIdLocal(rs.getInt("idLocal"));
					l.setNome(rs.getString("nome"));
					l.setTelefone(rs.getString("telefone"));
					l.setCapacidade(rs.getInt("capacidade"));
					l.setAreaFumante(rs.getBoolean("areaFumante"));
					l.setEndereco(rs.getString("endereco"));
					l.setVip(rs.getBoolean("vip"));
				}
		}catch(SQLException e) {
			throw new GenericDAOException(e);	
		}
			
		return l;
		
	}
	
	
	public void salvar(long id, Local l) throws GenericDAOException {
		String sql = "UPDATE tbLocal SET nome = ?, telefone = ?, capacidade = ?, areaFumante = ?, "
				+ "endereco = ?, vip = ? WHERE idLocal = ?";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, l.getNome());
			pstmt.setString(2, l.getTelefone());
			pstmt.setInt(3, l.getCapacidade());
			pstmt.setBoolean(4, l.isAreaFumante());
			pstmt.setString(5, l.getEndereco());
			pstmt.setBoolean(6,  l.isVip());
			pstmt.setLong(7, id);
			
			pstmt.executeUpdate();
		}catch(SQLException e) {
			throw new GenericDAOException(e);
		}
	}
	
	public List<Local> pesquisaLocal(String local) throws GenericDAOException {
		List<Local> lista = new ArrayList<>();
		String sql = "SELECT * FROM tbLocal WHERE nome like ?";
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
			throw new DAOExcep(e);
		}
	}

	public Local listarById(int id) throws DAOExcep {
		Local local = new Local();
		try {

			String sql = "select * from tbLocal where idLocal = ?;";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				local.setIdLocal(rs.getInt(1));
				local.setNome(rs.getString(2));
				local.setTelefone(rs.getString(3));
				local.setCapacidade(rs.getInt(4));
				local.setAreaFumante(rs.getBoolean(5));
				local.setEndereco(rs.getString(6));
				local.setVip(rs.getBoolean(7));
			}
			return local;
		} catch (SQLException e) {
			throw new DAOExcep(e);
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
			throw new DAOExcep(e);
		}

	}

}
