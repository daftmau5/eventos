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

import br.com.eventos.dao.impl.DAOAtracao;
import br.com.eventos.dao.impl.DAOExcep;
import br.com.eventos.dao.impl.DAOLocal;
import br.com.eventos.dao.impl.DAOTema;
import br.com.eventos.dao.impl.DAOEvento;
import br.com.eventos.model.Atracao;
import br.com.eventos.model.Evento;
import br.com.eventos.model.Local;
import br.com.eventos.model.Tema;
import br.com.eventos.model.Usuario;

@WebServlet("/ControllerCadastroEvento")
public class ControllerCadastroEvento extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public ControllerCadastroEvento() {
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String msg = null;

		HttpSession session = request.getSession();
		Usuario user = (Usuario)session.getAttribute("USUARIO_LOGADO");
		if(!(user==null)) {

			try {
				DAOTema temaDao = new DAOTema();
				DAOAtracao atracaoDao = new DAOAtracao();
				DAOLocal localDao = new DAOLocal();

				List<Tema> listaTema = temaDao.listar();
				session.setAttribute("LISTA_TEMA", listaTema);

				List<Atracao> listaAtracao = atracaoDao.listar();
				session.setAttribute("LISTA_ATRACAO", listaAtracao);

				List<Local> listaLocal = localDao.listar();
				session.setAttribute("LISTA_LOCAL", listaLocal);

				response.sendRedirect("./cadastro_evento.jsp");

			} catch (DAOExcep e) {
				e.printStackTrace();
				msg = "Erro ao acessar este evento";
				msg += "\n\n" + e.getMessage() + "\n";
				for (StackTraceElement trace : e.getStackTrace()) {
					msg += trace.toString();
				}
			}
		} else {
			msg = "Você não está logado";
			session.setAttribute("MENSAGEM", msg);
			response.sendRedirect("./");

		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd = request.getParameter("cmd");
		String msg = null;
		HttpSession session = request.getSession();

		Usuario user = (Usuario) session.getAttribute("USUARIO_LOGADO");
		if (!(user == null)) {

			DAOEvento eventoDao = new DAOEvento();

			Tema tema = new Tema();
			Atracao atracao = new Atracao();
			Evento evento = new Evento();
			Local local = new Local();

			try {

				if ("cadastrar".equals(cmd)) {
					evento.setDescricao(request.getParameter("txtDescricaoEvento"));
					evento.setPreco(Double.parseDouble(request.getParameter("txtPrecoEvento").replaceAll(",", ".")));
					tema.setIdTema(Integer.parseInt(request.getParameter("idTema")));
					atracao.setIdAtracao(Integer.parseInt(request.getParameter("idAtracao")));
					local.setIdLocal(Integer.parseInt(request.getParameter("idLocal")));

					String dataEvento = request.getParameter("txtDataEvento");
					SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
					Calendar cal = Calendar.getInstance();
					cal.setTime(sdf.parse(dataEvento));
					evento.setData(cal);

					evento.setTema(tema);
					evento.setAtracao(atracao);
					evento.setLocal(local);

					eventoDao.inserir(evento);
					msg = "Evento foi adicionado com sucesso";
				}

			} catch (ParseException e) {
				e.printStackTrace();
				msg = "Erro no registro";
				msg += "\n\n" + e.getMessage() + "\n";
				for (StackTraceElement trace : e.getStackTrace()) {
					msg += trace.toString();
				}
			}

			session.setAttribute("MENSAGEM", msg);
			response.sendRedirect("./ControllerCadastroEvento");
		} else {

			msg = "Você não está logado";
			session.setAttribute("MENSAGEM", msg);
			// response.sendRedirect("./pesquisa_evento.jsp");
		}
	}

}