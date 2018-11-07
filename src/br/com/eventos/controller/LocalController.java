package br.com.eventos.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.eventos.dao.GenericDAOException;
import br.com.eventos.dao.impl.LocalDAOImpl;
import br.com.eventos.model.Local;

/**
 * Servlet implementation class TemaController
 */
@WebServlet("/LocalController")
public class LocalController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LocalController() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		String cmd = request.getParameter("cmd");
		String msg = null;
		HttpSession session = request.getSession();
		try {
			LocalDAOImpl lDAO = new LocalDAOImpl();
			if ("cadastrar".equals(cmd)) {
				Local l = new Local();
				l.setNome(request.getParameter("txtNomeLocal"));
				l.setTelefone(request.getParameter("txtTelefone"));
				l.setCapacidade(Integer.parseInt(request.getParameter("txtCapacidade")));
				l.setAreaFumante(Boolean.parseBoolean(request.getParameter("txtAreaFumante")));
				l.setEndereco(request.getParameter("txtEndereco"));
				l.setVip(Boolean.parseBoolean(request.getParameter("txtVip")));
				

				lDAO.adicionar(l);
				msg = "Tema cadastrado com sucesso!!";

			} else if ("pesquisar".equals(cmd)) {
				List<Local> lista = lDAO.pesquisaLocal(request.getParameter("txtDescricao"));
				session.setAttribute("LISTA", lista);
				msg = "Atualmente temos" + lista.size() + " locais cadastrados";
			}

		} catch (GenericDAOException | NumberFormatException e) {
			e.printStackTrace();
			msg = "erro ao adicionar este local";
		}
		session.setAttribute("MENSAGEM", msg);
		response.sendRedirect("./cadastro_local.jsp");
	}
}
