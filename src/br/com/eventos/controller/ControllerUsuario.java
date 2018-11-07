package br.com.eventos.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.eventos.dao.EventosDAO;
import br.com.eventos.dao.impl.AtracaoDAO;
import br.com.eventos.dao.impl.DAOExcep;
import br.com.eventos.dao.impl.EventoDAO;
import br.com.eventos.dao.impl.LocalDAO;
import br.com.eventos.dao.impl.TemaDAO;
import br.com.eventos.dao.impl.UsuarioDAO;
import br.com.eventos.model.Atracao;
import br.com.eventos.model.Evento;
import br.com.eventos.model.Local;
import br.com.eventos.model.Tema;
import br.com.eventos.model.Usuario;

@WebServlet("/ControllerUsuario")
public class ControllerUsuario  extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	UsuarioDAO usuarioDao = new UsuarioDAO();

	public ControllerUsuario() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd = request.getParameter("cmd");
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		String msg = null;
		
		try {
			if("login".equals(cmd)) {
				HttpSession session = request.getSession();
				if (usuarioDao.checkLogin(login, senha) == null) {
					msg = "Erro! - Usuário não encontrado";
					session.setAttribute("MENSAGEM", msg);
					response.sendRedirect("./");
				}else {
					session.setAttribute("USUARIO_LOGADO", usuarioDao.checkLogin(login, senha));
					msg = "Sucesso! - Usuário logado";
					session.setAttribute("MENSAGEM", msg);
					Usuario u = (Usuario) session.getAttribute("USUARIO_LOGADO");
					System.out.println(u.getEmail());
					response.sendRedirect("./lista_evento.html");
				}
				
			}
		}catch (Exception  e) {
			e.printStackTrace();
			msg = "Erro!";
			msg += "\n\n" + e.getMessage() + "\n";
			for (StackTraceElement trace : e.getStackTrace()) { 
				msg += trace.toString();
			}
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd = request.getParameter("cmd");
		String msg = null;
		HttpSession session = request.getSession();
		
		try {
			
			if("cadastrar".equals(cmd)) {
				Usuario usuario = new Usuario();
				usuario.setLogin(request.getParameter("txtLogin"));
				usuario.setSenha(request.getParameter("txtSenha"));
				usuario.setCPF(request.getParameter("txtCPF"));
				usuario.setEmail(request.getParameter("txtEmail"));
				usuario.setEndereco(request.getParameter("txtEndereco"));
				usuario.setTelefone(request.getParameter("txtTelefone"));
				System.out.println(usuario.getLogin());
				usuarioDao.adicionar(usuario);
				msg = "Usuario foi adicionado com sucesso";
			}else if("cancelar".equals(cmd)) {
				msg = "Cancelado";
			}
			
		}catch (DAOExcep | NumberFormatException e) {
			e.printStackTrace();
			msg = "Erro ao acessar este usuário";
			msg += "\n\n" + e.getMessage() + "\n";
			for (StackTraceElement trace : e.getStackTrace()) { 
				msg += trace.toString();
			}
		}
		
		session.setAttribute("MENSAGEM", msg);
		response.sendRedirect("./");
	}
}	