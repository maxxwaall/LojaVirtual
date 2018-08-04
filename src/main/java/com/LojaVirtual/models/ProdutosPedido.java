package com.LojaVirtual.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ProdutosPedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long codigo;
	
	@ManyToOne
	@JoinColumn(name="pedido_id")
	private Pedidos pedido;
	
	@ManyToOne
	@JoinColumn(name="produto_id")
	private Produtos produto;
	
	private String nome;
	
	public ProdutosPedido(Pedidos pedido, Produtos produto, String nome) {
		this.pedido = pedido;
		this.produto = produto;
		this.nome = nome;
	}
	
	public ProdutosPedido() {
		// TODO Auto-generated constructor stub
	}

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public Pedidos getPedido() {
		return pedido;
	}

	public void setPedido(Pedidos pedido) {
		this.pedido = pedido;
	}

	public Produtos getProduto() {
		return produto;
	}

	public void setProduto(Produtos produto) {
		this.produto = produto;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
