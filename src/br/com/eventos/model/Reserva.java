package br.com.eventos.model;

import java.util.Date;




public class Reserva {
	
	private int idReserva;
	
	private Date data;
	
	private Pagamento modoPagamento;
	
	private String status;
	
	private Usuario usuario;
	
	private Evento evento;

	public int getIdReserva() {
		return idReserva;
	}

	public void setIdReserva(int idReserva) {
		this.idReserva = idReserva;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getModoPagamento() {
		return modoPagamento.getNome();
	}

	public void setModoPagamento(Pagamento modoPagamento) {
		this.modoPagamento = modoPagamento;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

}
