package br.com.eventos.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import br.com.eventos.model.Atracao;
import br.com.eventos.model.Evento;
import br.com.eventos.model.Local;
import br.com.eventos.model.Tema;
import br.com.eventos.model.Usuario;

public class DaoEvento {

	private Connection con;

	private DaoTema daoTema;

	private AtracaoDAO daoAtracao;

	private DaoLocal daoLocal;

	public ResultSet executeQuery(String sql, Statement stmt) throws SQLException {
		ResultSet rs = stmt.executeQuery(sql);
		return rs;
	}

	public DaoEvento() {
		try {
			// Class.forName("org.mariadb.jdbc.Driver");
        	Class.forName("org.mariadb.jdbc.Driver");
			String urldb = "jdbc:mariadb://sql10.freemysqlhosting.net/sql10264413?user=sql10264413&password=cvbBJqBPmf";
			con = DriverManager.getConnection(urldb);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void inserir(Evento e) {
		try {
			String sql = "INSERT INTO tbEvento (descricao, data, tema_id, atracao_id, local_id, preco) VALUES (?, ?, ?, ?, ?, ?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, e.getDescricao());
			stmt.setDate(2, new java.sql.Date(e.getData().getTimeInMillis()));
			stmt.setInt(3, e.getTema().getIdTema());
			stmt.setInt(4, e.getAtracao().getIdAtracao());
			stmt.setInt(5, e.getLocal().getIdLocal());
			stmt.setString(6, e.getPreco());
			stmt.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public void adicionaUsuario(Evento e, Usuario u) {
		try {
			String sql = "INSERT INTO evento_usuario (evento_id, usuario_id) VALUES (?, ?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, e.getIdEvento());
			stmt.setInt(2, u.getIdUsuario());
			stmt.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public ArrayList<Evento> listar() throws DAOExcep {
		ArrayList<Evento> array = new ArrayList<>();
		try {
			ResultSet rs = executeQuery("select * from tbEvento;", con.createStatement());
			while (rs.next()) {
				Evento e = new Evento();
				e.setIdEvento(rs.getInt(1));

				Tema tema = daoTema.listarById(rs.getInt(2));
				e.setTema(tema);

				e.setDescricao(rs.getString(3));

				Atracao atracao = daoAtracao.listarById(rs.getInt(4));
				e.setAtracao(atracao);

				Calendar calendar = Calendar.getInstance();
				SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
				try {
					Date dateNasc = formatador.parse(rs.getTimestamp(5).toString());
					calendar.setTime(dateNasc);
				} catch (Exception ex) {
					ex.printStackTrace();
				}

				e.setData(calendar);

				Local local = daoLocal.listarById(rs.getInt(6));
				e.setLocal(local);

				e.setPreco(rs.getString(7));

				array.add(e);
			}
			return array;
		} catch (SQLException e) {
			e.printStackTrace();
			return array;
		}
	}

	public void excluir(int id) {

		try {

			String sql = "DELETE FROM tbEvento WHERE id = ?";
			PreparedStatement stmt;
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
