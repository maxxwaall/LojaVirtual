package com.LojaVirtual.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Pedidos {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long codigo;


	private String enderecoEntrega;
	
	private Double valorTotalPedido;
	
	@ManyToOne
	@JoinColumn(name="cliente_id")
	private Clientes cliente;
	
	
	public Pedidos(long codigo, String endereco, Double valorTotal, Clientes cliente) {
		this.codigo = codigo;
		this.enderecoEntrega = endereco;
		this.valorTotalPedido = valorTotal;
		this.cliente = cliente;
	}
	
	public Clientes getCliente() {
		return cliente;
	}


	public void setCliente(Clientes cliente) {
		this.cliente = cliente;
	}


	public void setValorTotalPedido(Double valorTotalPedido) {
		this.valorTotalPedido = valorTotalPedido;
	}
	
	public Double getValorTotalPedido() {
		return valorTotalPedido;
	}

	public long getCodigo() {
		return codigo;
	}


	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}


	public String getEnderecoEntrega() {
		return enderecoEntrega;
	}


	public void setEnderecoEntrega(String enderecoEntrega) {
		this.enderecoEntrega = enderecoEntrega;
	}


	public Pedidos() {
		
	}

}
