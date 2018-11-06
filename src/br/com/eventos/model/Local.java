package br.com.eventos.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Local {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idLocal;
	@Column
	private String nome;
	@Column
	private String telefone;
	@Column
	private int capacidade;
	// vari�vel para determinar se existe �rea para fumantes
	@Column
	private boolean areaFumante;
	private int avaliacao;
	@Column
	private String endereco;
	@Column
	private boolean vip;
	
	public Local() {
		
	}
	
	public Local(int id) {
		this.idLocal = id;
	}
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
