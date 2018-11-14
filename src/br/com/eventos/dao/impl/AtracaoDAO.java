package br.com.eventos.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.eventos.model.Atracao;

public class AtracaoDAO {
	
	Connection con;
	
	public ResultSet executeQuery(String sql, Statement stmt) throws SQLException {
		ResultSet rs = stmt.executeQuery(sql);
		return rs;
	}
	
	public AtracaoDAO() {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            String urldb = "jdbc:mariadb://sql10.freemysqlhosting.net/sql10264413?user=sql10264413&password=cvbBJqBPmf";
			con = DriverManager.getConnection(urldb);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

	public void adicionar(Atracao a) throws DAOExcep {
		try {
            String sql = "INSERT INTO tbAtracao (nome, descricao) VALUES (?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, a.getNome());
            stmt.setString(2, a.getDescricao());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}

	public List<Atracao> listar() throws DAOExcep {
		List<Atracao> array = new ArrayList<>();
		String SQL = "SELECT * FROM tbAtracao;";
		try {		
			PreparedStatement pstmt = con.prepareStatement(SQL);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Atracao a = new Atracao();
				a.setIdAtracao(rs.getInt("idAtracao"));
				a.setNome(rs.getString("nome"));
				a.setDescricao(rs.getString("descricao"));
				array.add(a);
			}
		} catch (SQLException e) {
			e.printStackTrace();	
		}	
		return array;
	}

	public void excluir(int id) throws DAOExcep {
		String sql = "DELETE FROM tbAtracao WHERE idAtracao = ?";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new DAOExcep( e );
		}
	}

	public Atracao listarById(long id) throws DAOExcep {
		Atracao a = new Atracao();
		String sql = "SELECT * FROM tbAtracao WHERE idAtracao = ?";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, id);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) { 
				a.setIdAtracao(rs.getInt("idAtracao"));
				a.setNome(rs.getString("nome"));
				a.setDescricao(rs.getString("descricao"));
			}
		} catch (SQLException e) {
			throw new DAOExcep( e );
		}
		return a;
	}
	
	public List<Atracao> ProcurarNome(String nomeAtracao) throws DAOExcep {
		List<Atracao> lista = new ArrayList<>();
		Atracao a = new Atracao();
		String sql = "SELECT * FROM tbAtracao WHERE nome = ?";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, nomeAtracao);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) { 
				a.setIdAtracao(rs.getInt("idAtracao"));
				a.setNome(rs.getString("nome"));
				a.setDescricao(rs.getString("descricao"));
				lista.add(a);
			}
		} catch (SQLException e) {
			throw new DAOExcep( e );
		}
		return lista;
	}
	
	public void atualizar(long id, Atracao a) throws DAOExcep {
		String sql = "UPDATE tbAtracao SET nome = ?, descricao = ?"
				+ "WHERE idAtracao = ?";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, a.getNome());
			pstmt.setString(2, a.getDescricao());
			pstmt.setLong(3, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new DAOExcep( e );
		}
	}
	
	
}