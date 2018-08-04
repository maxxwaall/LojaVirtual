package com.LojaVirtual.resources;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.LojaVirtual.models.Clientes;
import com.LojaVirtual.models.Pedidos;
import com.LojaVirtual.models.Produtos;
import com.LojaVirtual.models.ProdutosPedido;
import com.LojaVirtual.repository.ClienteRepository;
import com.LojaVirtual.repository.PedidoRepository;
import com.LojaVirtual.repository.ProdutoRepository;
import com.LojaVirtual.repository.ProdutosPedidoRepository;

@RestController
@RequestMapping("/api/pedidos")
public class PedidosResource {
	
	public PedidosResource() {
	
	}
	
	@Autowired
	private ClienteRepository clientRep;
	
	@Autowired
	private ProdutoRepository produtoRep;
	
	@Autowired
	private PedidoRepository pedidoRep;
	
	@Autowired
	private ProdutosPedidoRepository produtosPorPedidoRep;
	
	@GetMapping(produces="application/json")
	public @ResponseBody Iterable<Pedidos> getPedidos() {
		Iterable<Pedidos> listaPedidos = pedidoRep.findAll();
		return listaPedidos;
	}
	
	@PostMapping()
	public Pedidos inserirPedidos(@RequestBody @Valid AuxiliarPedidos auxPedidos) {
		Pedidos pedidos = new Pedidos();
		Clientes cliente  = clientRep.findByCodigo(auxPedidos.getCodigoCliente());

		pedidos.setCliente(cliente);
		pedidos.setEnderecoEntrega(auxPedidos.getEnderecoEntrega());
		pedidos.setValorTotalPedido(auxPedidos.getTotalPedido());
		
		pedidos = pedidoRep.save(pedidos);
		
		for(String codigoProduto : auxPedidos.getCodigoProdutos()) {
			ProdutosPedido produtosPorPedido = new ProdutosPedido();
			
			Produtos produto = produtoRep.findByCodigo(Long.parseLong(codigoProduto));
			produtosPorPedido.setPedido(pedidos);
			produtosPorPedido.setProduto(produto);
			
			produtosPorPedidoRep.save(produtosPorPedido);
			
			System.out.print(pedidos.getCodigo() + cliente.getCodigo() + produto.getCodigo() + produtosPorPedido.getCodigo() );
		}
		
		
		return pedidos;
	}
	
	
	
	

}
