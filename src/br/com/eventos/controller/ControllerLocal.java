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
import br.com.eventos.dao.impl.DAOLocal;
import br.com.eventos.model.Local;
import br.com.eventos.model.Usuario;

/**
 * Servlet implementation class 
 */
@WebServlet("/LocalController")
public class ControllerLocal extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ControllerLocal() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Usuario user = (Usuario)session.getAttribute("USUARIO_LOGADO");
		if(!(user==null)) {
			response.sendRedirect("./cadastro_local.jsp");
			response.getWriter().append("Served at: ").append(request.getContextPath());
		}else {
			System.out.println("precisa logar");
			response.sendRedirect("./");
		}
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String cmd = request.getParameter("cmd");
		String msg = null;
		HttpSession session = request.getSession();
		try {
			DAOLocal lDAO = new DAOLocal();
			if ("cadastrar".equals(cmd)) {
				Local l = new Local();
				l.setNome(request.getParameter("txtNomeLocal"));
				l.setTelefone(request.getParameter("txtTelefone"));
				l.setCapacidade(Integer.parseInt(request.getParameter("txtCapacidade")));
				System.out.println(request.getParameter("txtAreaFumante"));
				if("fumante".equals(request.getParameter("txtAreaFumante"))){
					l.setAreaFumante(true);
				}else {
					l.setAreaFumante(false);
				}
				//l.setAreaFumante(Boolean.parseBoolean(request.getParameter("txtAreaFumante")));
				l.setEndereco(request.getParameter("txtEndereco"));
				if("vip".equals(request.getParameter("txtAreaVip"))){
					l.setVip(true);
				}else {
					l.setVip(false);
				}
				
				lDAO.adicionar(l);
				msg = "Local cadastrado com sucesso!!";

			} else if ("pesquisar".equals(cmd)) {
				List<Local> lista = lDAO.pesquisaLocal(request.getParameter("txtNomeLocal"));
				session.setAttribute("LISTA", lista);
				msg = "Atualmente temos" + lista.size() + " locais cadastrados";
			} else if("remover".equals(cmd)){
				String id = request.getParameter("txtId");
				lDAO.remover(Long.parseLong(id));
				msg = "Local com id" + id + " removido";
				List<Local> lista = lDAO.pesquisaLocal("");
				session.setAttribute("LISTA", lista);
				//response.sendRedirect("./cadastro_local.jsp");
			} else if("editar".equals(cmd)) {
				String id = request.getParameter("txtId");
				Local l = lDAO.pesquisarporId(Long.parseLong(id));
				System.out.println(l.getNome());
				session.setAttribute("LOCAL_ATUAL", l);
				msg = "Detalhes do tema com o ID  "+ id;
			} else if("salvar".equals(cmd)) {
				Local l = new Local();
				String id = request.getParameter("txtId");
				l.setNome(request.getParameter("txtNomeLocal"));
				l.setTelefone(request.getParameter("txtTelefone"));
				l.setCapacidade(Integer.parseInt(request.getParameter("txtCapacidade")));
				if("fumante".equals(request.getParameter("txtAreaFumante"))){
					l.setAreaFumante(true);
				}else {
					l.setAreaFumante(false);
				}
				//l.setAreaFumante(Boolean.parseBoolean(request.getParameter("txtAreaFumante")));
				l.setEndereco(request.getParameter("txtEndereco"));
				if("vip".equals(request.getParameter("txtAreaVip"))){
					l.setVip(true);
				}else {
					l.setVip(false);
				}
				System.out.println(l.getIdLocal());
				lDAO.salvar(Long.parseLong(id), l);
				List<Local> lista = lDAO.pesquisaLocal("");
				session.setAttribute("LISTA", lista);
				msg = "Local salvo com sucesso ";
				
			}
		} catch (GenericDAOException | NumberFormatException e) {
			e.printStackTrace();
			msg = "erro ao adicionar este local";
		}
		
		session.setAttribute("MENSAGEM", msg);
		response.sendRedirect("./LocalController");
	}
}
