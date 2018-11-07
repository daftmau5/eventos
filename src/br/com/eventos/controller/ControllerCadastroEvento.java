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

import br.com.eventos.dao.DaoTemplate;
import br.com.eventos.dao.impl.DAOExcep;
import br.com.eventos.dao.impl.DaoAtracao;
import br.com.eventos.dao.impl.DaoEvento;
import br.com.eventos.dao.impl.DaoLocal;
import br.com.eventos.dao.impl.DaoTema;
import br.com.eventos.model.Atracao;
import br.com.eventos.model.Evento;
import br.com.eventos.model.Local;
import br.com.eventos.model.Tema;

@WebServlet("/ControllerCadastroEvento")
public class ControllerCadastroEvento  extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ControllerCadastroEvento() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String msg = null;
		
		try {
			HttpSession session = request.getSession();
			DaoTemplate<Tema> temaDao = new DaoTema();
			DaoTemplate<Atracao> atracaoDao = new DaoAtracao();
			DaoTemplate<Local> localDao = new DaoLocal();
			DaoTemplate<Evento> eventoDao = new DaoEvento();

			List<Evento> listaEvento = eventoDao.listar();
			session.setAttribute("LISTA-EVENTO", listaEvento);
		
			List<Tema> listaTema = temaDao.listar();
			session.setAttribute("LISTA_TEMA", listaTema);
			
			
			List<Atracao> listaAtracao = atracaoDao.listar();
			session.setAttribute("LISTA_ATRACAO", listaAtracao);
			
			List<Local> listaLocal = localDao.listar();
			session.setAttribute("LISTA_LOCAL", listaLocal);
			
			response.sendRedirect("./cadastro_evento.jsp");

		 
		}catch (DAOExcep  e) {
			e.printStackTrace();
			msg = "Erro ao acessar este evento";
			msg += "\n\n" + e.getMessage() + "\n";
			for (StackTraceElement trace : e.getStackTrace()) { 
				msg += trace.toString();
			}
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		String cmd = request.getParameter("cmd");
		String msg = null;
		HttpSession session = request.getSession();
		
		DaoTemplate<Evento> eventoDao = new DaoEvento();
		
		try {
			
			if("cadastrar".equals(cmd)) {
				Tema tema = new Tema();
				Atracao atracao = new Atracao();
				Evento evento = new Evento();
				evento.setNome(request.getParameter("txtNomeEvento"));
				evento.setDescricao(request.getParameter("txtDescricaoEvento"));
				evento.setPreco(request.getParameter("txtPrecoEvento"));
				tema.setIdTema(Integer.parseInt(request.getParameter("idTema")));
				evento.setTema(tema);
				evento.setLocal(new Local(
						Integer.parseInt(request.getParameter("idLocal"))));
			    String dataEvento = request.getParameter("txtDataEvento");
			   	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			   	Calendar cal = Calendar.getInstance();
			   	cal.setTime(sdf.parse(dataEvento));
			   	evento.setData(cal);
				evento.setAtracao(new Atracao(
						Integer.parseInt(request.getParameter("idAtracao"))));
				eventoDao.inserir(evento);
				msg = "Evento foi adicionado com sucesso";
			}
			
		}catch (DAOExcep | NumberFormatException | ParseException e) {
			e.printStackTrace();
			msg = "Erro no registro";
			msg += "\n\n" + e.getMessage() + "\n";
			for (StackTraceElement trace : e.getStackTrace()) { 
				msg += trace.toString();
			}
		}
		
		session.setAttribute("MENSAGEM", msg);
		response.sendRedirect("./cadastro_evento.jsp");
	}
}	