package br.com.eventos.model;

public class Tema {

private int idTema;
	
	private String descricao;

	public int getIdTema() {
		return idTema;
	}

	public void setIdTema(int idTema) {
		this.idTema = idTema;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
	public String toString() {
		return descricao;
	}

}
