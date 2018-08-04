package com.LojaVirtual.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Produtos {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long codigo;
	
	private String nome;
	
	public Produtos() {
		// TODO Auto-generated constructor stub
	}
	
	public Produtos(String nome) {
		this.nome = nome;
	}
	
	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
