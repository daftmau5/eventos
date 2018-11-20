package br.com.eventos.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.eventos.dao.impl.DAOExcep;
import br.com.eventos.dao.impl.DAOUsuario;
import br.com.eventos.model.Usuario;

@WebServlet("/ControllerUsuario")
public class ControllerUsuario extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	DAOUsuario usuarioDao = new DAOUsuario();

	public ControllerUsuario() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Usuario user = (Usuario)session.getAttribute("USUARIO_LOGADO");
		if(!(user==null)) {
			response.sendRedirect("./editar_usuario.jsp");
			response.getWriter().append("Served at: ").append(request.getContextPath());
		}else {
			response.sendRedirect("./cadastro_usuario.jsp");
			response.getWriter().append("Served at: ").append(request.getContextPath());
		}
		

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cmd = request.getParameter("cmd");
		String msg = null;
		HttpSession session = request.getSession();

		try {

			if ("cadastrar".equals(cmd)) {
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
				session.setAttribute("MENSAGEM", msg);
				response.sendRedirect("./");
			} else if("editar".equals(cmd)) {
				System.out.println("Editando usuario");
				Usuario usuario = new Usuario();
				usuario.setIdUsuario(Integer.parseInt(request.getParameter("txtIdUsuario")));
				usuario.setLogin(request.getParameter("txtLogin"));
				usuario.setSenha(request.getParameter("txtSenha"));
				usuario.setCPF(request.getParameter("txtCPF"));
				usuario.setEmail(request.getParameter("txtEmail"));
				usuario.setEndereco(request.getParameter("txtEndereco"));
				usuario.setTelefone(request.getParameter("txtTelefone"));
				System.out.println(usuario.getLogin());
				usuarioDao.atualizar(usuario);
				msg = "Usuario foi atualizado com sucesso";
				session.setAttribute("MENSAGEM", msg);
				session.setAttribute("USUARIO_LOGADO", usuario);
				System.out.println(msg);
				response.sendRedirect("./ControllerUsuario");
			} else if ("cancelar".equals(cmd)) {
				msg = "Cancelado";
			}

		} catch (DAOExcep | NumberFormatException e) {
			e.printStackTrace();
			msg = "Erro ao acessar este usuário";
			msg += "\n\n" + e.getMessage() + "\n";
			for (StackTraceElement trace : e.getStackTrace()) {
				msg += trace.toString();
			}
		}

		
	}
}