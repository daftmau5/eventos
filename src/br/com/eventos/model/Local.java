package br.com.eventos.model;

public class Local {
	
	
	private int idLocal;
	
	private String nome;
	
	private String telefone;
	
	private int capacidade;
	// variável para determinar se existe área para fumantes
	
	private boolean areaFumante;
	private int avaliacao;
	
	private String endereco;
	
	private boolean vip;

	public int getIdLocal() {
		return idLocal;
	}

	public void setIdLocal(int idLocal) {
		this.idLocal = idLocal;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public int getCapacidade() {
		return capacidade;
	}

	public void setCapacidade(int capacidade) {
		this.capacidade = capacidade;
	}

	public boolean isAreaFumante() {
		return areaFumante;
	}

	public void setAreaFumante(boolean areaFumante) {
		this.areaFumante = areaFumante;
	}

	public int getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(int avaliacao) {
		this.avaliacao = avaliacao;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public boolean isVip() {
		return vip;
	}

	public void setVip(boolean vip) {
		this.vip = vip;
	}

	@Override
	public String toString() {
		return nome;
	}
}
