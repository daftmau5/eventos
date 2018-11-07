package br.com.eventos.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.eventos.dao.EventosDAO;
import br.com.eventos.model.Atracao;

public class AtracaoDAO implements EventosDAO<Atracao>{
	
	Connection con;
	
	public ResultSet executeQuery(String sql, Statement stmt) throws SQLException {
		ResultSet rs = stmt.executeQuery(sql);
		return rs;
	}
	
	public AtracaoDAO() {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/eventos?user=root&password=123456");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

	@Override
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

	@Override
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
			return array;
		} catch (SQLException e) {
			e.printStackTrace();
			return array;
		}	
	}

	@Override
	public void excluir(int id) throws DAOExcep {
		String sql = "DELETE FROM tbAtracao WHERE id = ?";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new DAOExcep( e );
		}
	}

	@Override
	public Atracao pesquisarPorId(long id) throws DAOExcep {
		Atracao a = new Atracao();
		String sql = "SELECT * FROM tbAtracao WHERE id = ?";
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
	
	
}