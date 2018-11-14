package br.com.eventos.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import br.com.eventos.dao.impl.AtracaoDAO;
import br.com.eventos.dao.impl.DAOExcep;
import br.com.eventos.model.Atracao;
import br.com.eventos.model.Usuario;

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
				List<Atracao> lista = ad.ProcurarNome(request.getParameter("txtNomeAtracao"));
				session.setAttribute("LISTA", lista);
				msg = "Encontramos " + lista.size() + " Atra��o";
			}
			else if ("remover".equals(cmd)) {
				String id = request.getParameter("txtId");
				ad.excluir(Integer.parseInt(id));
				msg = "Atracao " + id + " removido";
				List<Atracao> lista = ad.ProcurarNome("");
				session.setAttribute("LISTA", lista);
			}
			else if ("editar".equals(cmd)){
				String id = request.getParameter("txtId");
				Atracao a = ad.listarById(Long.parseLong(id));
				session.setAttribute("ATRACAO_EDITAR", a);
				msg = "Ser� editado: " + a;
			}
			else if ("atualizar".equals(cmd)) {
				Atracao a = new Atracao();
				String id = request.getParameter("txtId");
				System.out.println(id);
				a.setNome(request.getParameter("txtNomeAtracao"));
				a.setDescricao(request.getParameter("txtDescricaoAtracao"));
				ad.atualizar(Long.parseLong(id), a );
				List<Atracao> lista = ad.ProcurarNome("");
				session.setAttribute("LISTA", lista);				
				msg = "Atracao atualizado!";
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
		response.sendRedirect("./buscar_atracao.jsp");
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Usuario user = (Usuario)session.getAttribute("USUARIO_LOGADO");
		if(!(user==null)) {
			response.sendRedirect("./buscar_atracao.jsp");
			response.getWriter().append("Served at: ").append(request.getContextPath());
		}else {
			System.out.println("precisa logar");
			response.sendRedirect("./");
		}
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}
}
