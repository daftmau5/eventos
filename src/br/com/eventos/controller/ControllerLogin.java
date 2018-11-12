package br.com.eventos.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.eventos.dao.impl.UsuarioDAO;
import br.com.eventos.model.Usuario;

@WebServlet("/ControllerLogin")
public class ControllerLogin  extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	UsuarioDAO usuarioDao = new UsuarioDAO();

	public ControllerLogin() {
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
					response.sendRedirect("TemaController");
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
		
	}
}	