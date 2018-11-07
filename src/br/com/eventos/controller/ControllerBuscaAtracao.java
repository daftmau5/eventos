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

@WebServlet("/ControlerBuscaAtracao")
public class ControllerBuscaAtracao extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	AtracaoDAO ad = new AtracaoDAO();
	
	public ControllerBuscaAtracao() {
		super();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String cmd = request.getParameter("cmd");
		String msg = null;
		HttpSession session = request.getSession();
		
		try {

			if("pesquisar".equals(cmd)) {
				String id = request.getParameter("txtIDAtracao");
				Long id2 = Long.parseLong(id);
				List<Atracao> lista = (List<Atracao>) ad.pesquisarPorId(id2);
				session.setAttribute("LISTA", lista);
				msg = "Foram encontrados " + lista.size() + " Atrações";
			}
			else if ("limpar".equals(cmd)) {
				request.getParameter("txtNomeAtracao");
			}
			
		}catch(DAOExcep | NumberFormatException e) {
			e.printStackTrace();
			msg = "Erro ao acessar cadastro de atração";
			msg += "\n\n" + e.getMessage() + "\n";
			for (StackTraceElement trace : e.getStackTrace()) {
				msg += trace.toString();
			}
		}
		
		session.setAttribute("Mensagem", msg);
		response.sendRedirect("./");
	}
}
