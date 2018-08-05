package com.LojaVirtual.resources;

import java.util.List;

public class AuxiliarPedidos {

	private String enderecoEntrega;
	private long codigoCliente;
	private List<Long> codigoProdutos;
	private Double totalPedido;
	
	public AuxiliarPedidos(String enderecoEntrega, long codigoCliente, List<Long> codigoProd, Double total) {
		this.enderecoEntrega = enderecoEntrega;
		this.codigoCliente = codigoCliente;
		this.codigoProdutos = codigoProd;
		this.totalPedido = total;
		
	}
	
	public String getEnderecoEntrega() {
		return enderecoEntrega;
	}



	public void setEnderecoEntrega(String enderecoEntrega) {
		this.enderecoEntrega = enderecoEntrega;
	}



	public long getCodigoCliente() {
		return codigoCliente;
	}



	public void setCodigoCliente(long codigoCliente) {
		this.codigoCliente = codigoCliente;
	}



	public List<Long> getCodigoProdutos() {
		return codigoProdutos;
	}



	public void setCodigoProdutos(List<Long> codigoProdutos) {
		this.codigoProdutos = codigoProdutos;
	}



	public Double getTotalPedido() {
		return totalPedido;
	}



	public void setTotalPedido(Double totalPedido) {
		this.totalPedido = totalPedido;
	}



	public AuxiliarPedidos() {
		// TODO Auto-generated constructor stub
	}

}
