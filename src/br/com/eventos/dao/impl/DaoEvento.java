package br.com.eventos.dao.impl;

import java.sql.Connection;
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
				daoTema = new DaoTema();
				daoAtracao = new AtracaoDAO();
				daoLocal = new DaoLocal();
				
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

	public void excluir(int id) {

		try {

			String sql = "DELETE FROM tbEvento WHERE idEvento = ?";
			PreparedStatement stmt;
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
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
				daoTema = new DaoTema();
				daoAtracao = new AtracaoDAO();
				daoLocal = new DaoLocal();
				
				e.setIdEvento(rs.getInt(1));
				
				Usuario usuario = new Usuario();
				usuario.setIdUsuario(rs.getInt(2));

				Tema tema = new Tema();
				
				tema = daoTema.listarById(rs.getInt(3));
				e.setTema(tema);
				
				Atracao atracao = daoAtracao.listarById(rs.getInt(4));
				e.setAtracao(atracao);
				
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
}
