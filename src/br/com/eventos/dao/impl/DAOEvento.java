package br.com.eventos.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import br.com.eventos.model.Atracao;
import br.com.eventos.model.Evento;
import br.com.eventos.model.Local;
import br.com.eventos.model.Tema;
import br.com.eventos.model.Usuario;

public class DAOEvento {

	private Connection con;

	private DAOTema daoTema;

	private DAOAtracao daoAtracao;

	private DAOLocal daoLocal;

	public ResultSet executeQuery(String sql, Statement stmt) throws SQLException {
		ResultSet rs = stmt.executeQuery(sql);
		return rs;
	}

	public DAOEvento() {
		try {
			ConnectionFactory conn = new ConnectionFactory();
			con = conn.getCon();
        } catch (Exception e) {
            e.printStackTrace();
        } 

	}

	public void inserir(Evento e) {
		try {
			String sql = "INSERT INTO tbEvento (descricao, dataEvento, idTema, idAtracao, idLocal, preco) VALUES (?, ?, ?, ?, ?, ?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, e.getDescricao());
			stmt.setDate(2, new java.sql.Date(e.getData().getTimeInMillis()));
			stmt.setInt(3, e.getTema().getIdTema());
			stmt.setInt(4, e.getAtracao().getIdAtracao());
			stmt.setInt(5, e.getLocal().getIdLocal());
			stmt.setDouble(6, e.getPreco());
			stmt.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	/*

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
	*/

	public ArrayList<Evento> listar() throws DAOExcep {
		ArrayList<Evento> array = new ArrayList<>();
		try {
			ResultSet rs = executeQuery("select * from tbEvento;", con.createStatement());
			while (rs.next()) {
				Evento e = new Evento();
				daoTema = new DAOTema();
				daoAtracao = new DAOAtracao();
				daoLocal = new DAOLocal();
				
				e.setIdEvento(rs.getInt(1));
				
				Usuario usuario = new Usuario();
				usuario.setIdUsuario(rs.getInt(2));

				Tema tema = new Tema();
				
				tema = daoTema.listarById(rs.getInt(3));
				e.setTema(tema);
				
				Atracao atracao = daoAtracao.listarById(rs.getInt(4));
				e.setAtracao(atracao);
				
				/*
				Date dateEvento  = new Date();
				Calendar calendar = Calendar.getInstance();
				SimpleDateFormat formatador = new SimpleDateFormat("dd.MM.yyyy");
				try {
					dateEvento = formatador.parse(rs.getTimestamp(5).toString());
					calendar.setTime(dateEvento);
					e.setData(calendar);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				*/
				
				e.setDescricao(rs.getString(6));

				e.setPreco(rs.getDouble(7));

				Local local = daoLocal.listarById(rs.getInt(8));
				e.setLocal(local);
				
				array.add(e);
			}
			return array;
		} catch (SQLException e) {
			e.printStackTrace();
			return array;
		}
	}

	public String excluir(int id) {

		try {

			String sql = "DELETE FROM tbEvento WHERE idEvento = ?";
			PreparedStatement stmt;
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.executeUpdate();
			return "Evento com id " + id + " deletado";

		} catch (SQLException e) {
			e.printStackTrace();
			return "Erro ao deletar: Alguém está participando desse evento";

		}
	}
	
	public ArrayList<Evento> listarPorFiltro(Evento evento) throws DAOExcep {
		ArrayList<Evento> array = new ArrayList<>();
		try {
			//String sql = "select * from tbEvento where dataEvento = ? descricao like ? and idLocal = ?";
			String sql = "select * from tbEvento where descricao like ? and idLocal = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
		//	pstmt.setDate(1, new java.sql.Date(evento.getData().getTimeInMillis()));
			pstmt.setString(1, "%" + evento.getDescricao() + "%");
			pstmt.setInt(2, evento.getLocal().getIdLocal());

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				Evento e = new Evento();
				daoTema = new DAOTema();
				daoAtracao = new DAOAtracao();
				daoLocal = new DAOLocal();
				
				e.setIdEvento(rs.getInt(1));
				
				Usuario usuario = new Usuario();
				usuario.setIdUsuario(rs.getInt(2));

				Tema tema = new Tema();
				
				tema = daoTema.listarById(rs.getInt(3));
				e.setTema(tema);
				
				Atracao atracao = daoAtracao.listarById(rs.getInt(4));
				e.setAtracao(atracao);
				
				/*
				Date dateEvento  = new Date();
				Calendar calendar = Calendar.getInstance();
				SimpleDateFormat formatador = new SimpleDateFormat("dd.MM.yyyy");
				try {
					dateEvento = formatador.parse(rs.getTimestamp(5).toString());
					calendar.setTime(dateEvento);
					e.setData(calendar);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				*/

				e.setDescricao(rs.getString(6));

				e.setPreco(rs.getDouble(7));

				Local local = daoLocal.listarById(rs.getInt(8));
				e.setLocal(local);
				
				array.add(e);
			}
			return array;
		} catch (SQLException e) {
			e.printStackTrace();
			return array;
		}
	}
	
	public Evento listarById(long id) throws DAOExcep {
		Evento evento = new Evento();
		String sql = "SELECT * FROM tbEvento WHERE idEvento = ?";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, id);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) { 
				daoTema = new DAOTema();
				daoAtracao = new DAOAtracao();
				daoLocal = new DAOLocal();
				
				evento.setIdEvento(rs.getInt(1));
				
				Usuario usuario = new Usuario();
				usuario.setIdUsuario(rs.getInt(2));

				Tema tema = new Tema();
				
				tema = daoTema.listarById(rs.getInt(3));
				evento.setTema(tema);
				
				Atracao atracao = daoAtracao.listarById(rs.getInt(4));
				evento.setAtracao(atracao);
				
				/*
				Date dateEvento  = new Date();
				Calendar calendar = Calendar.getInstance();
				SimpleDateFormat formatador = new SimpleDateFormat("dd.MM.yyyy");
				try {
					dateEvento = formatador.parse(rs.getTimestamp(5).toString());
					calendar.setTime(dateEvento);
					evento.setData(calendar);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				*/

				evento.setDescricao(rs.getString(6));

				evento.setPreco(rs.getDouble(7));

				Local local = daoLocal.listarById(rs.getInt(8));
				evento.setLocal(local);
				
			}
		} catch (SQLException e) {
			throw new DAOExcep( e );
		}
		return evento;
	}
	
}
