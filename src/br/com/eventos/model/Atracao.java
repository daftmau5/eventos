package br.com.eventos.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Atracao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idAtracao;
	@Column
	private String nome;
	@Column
	private String descricao;
	
	public Atracao() {
		
	}
	
	public Atracao(int id) {
		this.idAtracao = id;
	}

	public int getIdAtracao() {
		return idAtracao;
	}

	public void setIdAtracao(int idAtracao) {
		this.idAtracao = idAtracao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	@Override
	public String toString() {
		return nome;
	}

}
