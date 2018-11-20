package br.com.eventos.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.eventos.dao.impl.DAOExcep;
import br.com.eventos.dao.impl.DAOEvento;
import br.com.eventos.dao.impl.DAOReserva;
import br.com.eventos.model.Evento;
import br.com.eventos.model.Reserva;
import br.com.eventos.model.Usuario;

@WebServlet("/ControllerReservaEvento")
public class ControllerReservaEvento extends HttpServlet {

	private static final long serialVersionUID = -7015734962950630368L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String msg = null;
		HttpSession session = request.getSession();

		Usuario user = (Usuario) session.getAttribute("USUARIO_LOGADO");
		if (!(user.getEmail() == null)) {

			try {
				DAOReserva daoReserva = new DAOReserva();

				List<Reserva> listaReserva = daoReserva.listar(user.getIdUsuario());
				session.setAttribute("LISTA_RESERVA", listaReserva);
				response.sendRedirect("./reservas_usuario.jsp");

			} catch (SQLException e) {
				e.printStackTrace();
				msg = "Erro ao acessar este evento";
				msg += "\n\n" + e.getMessage() + "\n";
				for (StackTraceElement trace : e.getStackTrace()) {
					msg += trace.toString();
				}
			}

		} else {

			msg = "Você não está logado";
			session.setAttribute("MENSAGEM", msg); //
			response.sendRedirect("./ControllerPesquisaEvento");

		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cmd = request.getParameter("cmd");
		String msg = null;
		HttpSession session = request.getSession();

		Usuario user = (Usuario) session.getAttribute("USUARIO_LOGADO");
		if (!(user == null)) {

			DAOEvento eventoDao = new DAOEvento();
			DAOReserva reservaDao = new DAOReserva();
			Evento evento = new Evento();
			Reserva reserva = new Reserva();

			try {
				if ("efetua_pagamento".equals(cmd)) {
					String id = request.getParameter("idEvento");
					String formaPagamento = request.getParameter("formaPagamento");
					evento = eventoDao.listarById(Long.parseLong(id));
					
					reserva.setEvento(evento);
					reserva.setUsuario(user);
					///reserva.setModoPagamento(formaPagamento);
					reserva.setStatus("RESERVADO");
					reservaDao.inserir(reserva);

					response.sendRedirect("./ControllerPesquisaEvento");
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

		} else {
			msg = "Você não está logado";
			session.setAttribute("MENSAGEM", msg);
			response.sendRedirect("./pesquisa_evento.jsp");

		}

	}

}
