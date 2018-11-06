package br.com.eventos.model;

import java.util.ArrayList;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import br.com.eventos.model.Local;

@Entity
public class Evento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idEvento;
	@ManyToMany(fetch=FetchType.EAGER)
	private ArrayList<Usuario> usuariosConfirmados;
	@ManyToOne
	private Tema tema;
	@Column
	private String descricao;
	@Column
	private String nome;
	@ManyToOne
	private Atracao atracao;
	@Column
	private Calendar data;
	@ManyToOne
	private Local local;
	@Column
	private String preco;

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

	public String getPreco() {
		return preco;
	}

	public void setPreco(String preco) {
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


}
