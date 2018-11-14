package br.com.eventos.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.eventos.dao.impl.AtracaoDAO;
import br.com.eventos.dao.impl.DAOExcep;
import br.com.eventos.dao.impl.DaoEvento;
import br.com.eventos.dao.impl.DaoLocal;
import br.com.eventos.dao.impl.DaoTema;
import br.com.eventos.model.Atracao;
import br.com.eventos.model.Evento;
import br.com.eventos.model.Local;
import br.com.eventos.model.Tema;
import br.com.eventos.model.Usuario;

public class ControllerPesquisaEvento extends HttpServlet {

	private static final long serialVersionUID = 5285834682359746610L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String msg = null;
		HttpSession session = request.getSession();


		Usuario user = (Usuario)session.getAttribute("USUARIO_LOGADO");
		if(!(user.getEmail()==null)) {

			try {
				DaoLocal localDao = new DaoLocal();
				DaoEvento eventoDao = new DaoEvento();

				List<Evento> listaEvento = eventoDao.listar();
				session.setAttribute("LISTA_EVENTO", listaEvento);

				List<Local> listaLocal = localDao.listar();
				session.setAttribute("LISTA_LOCAL", listaLocal);

				response.sendRedirect("./pesquisa_evento.jsp");

			} catch (DAOExcep e) {
				e.printStackTrace();
				msg = "Erro ao acessar este evento";
				msg += "\n\n" + e.getMessage() + "\n";
				for (StackTraceElement trace : e.getStackTrace()) {
					msg += trace.toString();
				}
			}
		} else {

			msg = "Você não ta logado seu merdinha";
			session.setAttribute("MENSAGEM", msg);
			// response.sendRedirect("./pesquisa_evento.jsp");

		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String cmd = request.getParameter("cmd");
		String msg = null;
		HttpSession session = request.getSession();

		Usuario user = (Usuario) session.getAttribute("USUARIO_LOGADO");
		if (!(user == null)) {

			DaoEvento eventoDao = new DaoEvento();
			DaoLocal localDao = new DaoLocal();

			Tema tema = new Tema();
			Atracao atracao = new Atracao();
			Evento evento = new Evento();
			Local local = new Local();

			try {

				List<Local> listaLocal = localDao.listar();
				session.setAttribute("LISTA_LOCAL", listaLocal);

				if ("remover".equals(cmd)) {
					String id = request.getParameter("txtId");
					eventoDao.excluir(Integer.parseInt(id));
					msg = "Evento com o Id " + id + " foi removido";
					List<Evento> listaEvento = eventoDao.listar();
					session.setAttribute("LISTA_EVENTO", listaEvento);
				} else if ("pesquisar".equals(cmd)) {
					Evento e = new Evento();
					e.setDescricao(request.getParameter("txtDescricaoEvento"));

					local.setIdLocal(Integer.parseInt(request.getParameter("idLocal")));
					e.setLocal(local);

					/*
					 * String dataEvento = request.getParameter("txtDataEvento"); SimpleDateFormat
					 * sdf = new SimpleDateFormat("dd.MM.yyyy"); Calendar cal =
					 * Calendar.getInstance(); cal.setTime(sdf.parse(dataEvento)); e.setData(cal);
					 */

					List<Evento> listaEventos = eventoDao.listarPorFiltro(e);

					session.setAttribute("LISTA_EVENTO", listaEventos);
					msg = "Foram encontrados " + listaEventos.size() + " eventos";

				}

			} catch (DAOExcep /* | ParseException */ e) {
				e.printStackTrace();
				msg = "Erro no registro";
				msg += "\n\n" + e.getMessage() + "\n";
				for (StackTraceElement trace : e.getStackTrace()) {
					msg += trace.toString();
				}
			}

			session.setAttribute("MENSAGEM", msg);
			response.sendRedirect("./pesquisa_evento.jsp");
		} else {
			msg = "Você não ta logado seu merdinha";
			session.setAttribute("MENSAGEM", msg);
			// response.sendRedirect("./pesquisa_evento.jsp");

		}
	}

}
