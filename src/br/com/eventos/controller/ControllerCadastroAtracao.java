package br.com.eventos.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.eventos.dao.impl.AtracaoDAO;
import br.com.eventos.dao.impl.DAOExcep;
import br.com.eventos.model.Atracao;
import br.com.eventos.model.Usuario;

@WebServlet("/ControlerCadAtracao")
public class ControllerCadastroAtracao extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	AtracaoDAO ad = new AtracaoDAO();
	
	public ControllerCadastroAtracao() {
		super();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String cmd = request.getParameter("cmd");
		String msg = null;
		HttpSession session = request.getSession();
		
		try {
			if("cadastrar".equals(cmd)) {
				Atracao a = new Atracao();
				a.setNome(request.getParameter("txtNomeAtracao"));
				a.setDescricao(request.getParameter("txtDescricaoAtracao"));
				ad.adicionar(a);
				List<Atracao> lista = ad.ProcurarNome("");
				session.setAttribute("LISTA", lista);
				msg = "Atra��o Adicionada!";
			}
			else if ("limpar".equals(cmd)) {
				request.getParameter("txtNomeAtracao");
			}
			
		}catch(DAOExcep | NumberFormatException e) {
			e.printStackTrace();
			msg = "Erro ao acessar cadastro de atra��o";
			msg += "\n\n" + e.getMessage() + "\n";
			for (StackTraceElement trace : e.getStackTrace()) {
				msg += trace.toString();
			}
		}
		
		session.setAttribute("MENSAGEM", msg);
		response.sendRedirect("./cadastro_atracao.jsp");
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Usuario user = (Usuario)session.getAttribute("USUARIO_LOGADO");
		if(!(user==null)) {
			response.sendRedirect("./cadastro_atracao.jsp");
			response.getWriter().append("Served at: ").append(request.getContextPath());
		}else {
			System.out.println("precisa logar");
			response.sendRedirect("./");
		}
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}
}
