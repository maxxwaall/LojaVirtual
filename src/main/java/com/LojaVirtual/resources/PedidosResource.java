package com.LojaVirtual.resources;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


/* Autor: Fabiano Albino Ferreira
 * 
 * Data da criação: 30/07/2018
 * 
 * Descrição: Classe criada para receber e também retornar pedidos existentes no banco de dados.
 * 
 */
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

	//Método GET para retornar todos os pedidos cadastrados no banco de dados.
	@GetMapping(produces = "application/json")
	public @ResponseBody List<AuxiliarPedidos> getPedidos() {
		Iterable<Pedidos> listaPedidos = pedidoRep.findAll();
		Iterable<ProdutosPedido> listaProdutosPorPedidoByIdPedido = produtosPorPedidoRep.findAll();
		List<AuxiliarPedidos> listPedidosReturn = new ArrayList<>();
		Map<Long, List<Long>> mapProdutosPedidoByPedido = new HashMap<>();

		try {
			
			for (Pedidos pedido : listaPedidos) {
				List<Long> produtosDoPedido = new ArrayList<Long>();
				for (ProdutosPedido prodPedido : listaProdutosPorPedidoByIdPedido) {
					if (pedido.getCodigo() == prodPedido.getPedido().getCodigo()) {
						produtosDoPedido.add(prodPedido.getProduto().getCodigo());
					}
				}
				mapProdutosPedidoByPedido.put(pedido.getCodigo(), produtosDoPedido);
			}

			for (Pedidos pedido : listaPedidos) {
				AuxiliarPedidos auxPedido = new AuxiliarPedidos(pedido.getEnderecoEntrega(),
																pedido.getCliente().getCodigo(), 
																mapProdutosPedidoByPedido.get(pedido.getCodigo()),
																pedido.getValorTotalPedido());
				listPedidosReturn.add(auxPedido);
			}
			
		} catch (Exception ex) {
			throw ex;
		}

		return listPedidosReturn;
	}

	/*Metodo POST para cadastrar os pedidos recebidos da API no banco de dados.
	 * 
	 * Example Body: {
					    "codigoCliente":1,
					    "enderecoEntrega": "Rua da Justiça",
					    "codigoProdutos":["1","2"],
					    "totalPedido": 60
					 }
	 * 
	 */
	@PostMapping()
	public Pedidos inserirPedidos(@RequestBody @Valid AuxiliarPedidos auxPedidos) {
		
		Pedidos pedidos ;
		Iterable<ProdutosPedido> listProdutosPedido = new ArrayList<ProdutosPedido>();
		Iterable<Produtos> listProdutosByCodigo = new ArrayList<Produtos>();
		try {
						
			Clientes cliente = clientRep.findByCodigo(auxPedidos.getCodigoCliente());
			
			pedidos = new Pedidos(auxPedidos.getEnderecoEntrega(), auxPedidos.getTotalPedido(), cliente);
			pedidos = pedidoRep.save(pedidos);
			
			listProdutosByCodigo = produtoRep.findByCodigoIn(auxPedidos.getCodigoProdutos());
			
			for (Produtos produto : listProdutosByCodigo) {
				
				ProdutosPedido produtosPorPedido = new ProdutosPedido(pedidos, produto, "PEDIDO - PRODUTO (" + produto.getCodigo() + ")");
				((ArrayList<ProdutosPedido>) listProdutosPedido).add(produtosPorPedido);

			}
			
			produtosPorPedidoRep.save(listProdutosPedido);
			
		} catch (Exception ex) {
			throw ex;
		}

		return pedidos;
	}

}
