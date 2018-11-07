package br.com.eventos.model;

public class Atracao {

	private int idAtracao;
	private String nome;
	private String descricao;

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
