package br.com.eventos.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import br.com.eventos.model.Local;

public class Evento {
	
	private int idEvento;

	private ArrayList<Usuario> usuariosConfirmados;
	
	private Tema tema;
	
	private String descricao;
	
	private String nome;

	private Atracao atracao;
	
	private Calendar data;
	
	private Local local;

	private Double preco;

	public int getIdEvento() {
		return idEvento;
	}

	public void setIdEvento(int idEvento) {
		this.idEvento = idEvento;
	}
	

	public ArrayList<Usuario> getUsuariosConfirmados() {
		return usuariosConfirmados;
	}

	public void setUsuariosConfirmados(ArrayList<Usuario> usuariosConfirmados) {
		this.usuariosConfirmados = usuariosConfirmados;
	}
	
	public Tema getTema() {
		return tema;
	}

	public void setTema(Tema tema) {
		this.tema = tema;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Atracao getAtracao() {
		return atracao;
	}

	public void setAtracao(Atracao atracao) {
		this.atracao = atracao;
	}

	public Local getLocal() {
		return local;
	}

	public void setLocal(Local local) {
		this.local = local;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String calendarToString(Calendar date){
		SimpleDateFormat s = new SimpleDateFormat("dd.MM.yyyy");
		String a = s.format(date.getTime());
	return a;
	}

}
