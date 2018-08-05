package com.LojaVirtual.LojaVirtual;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.LojaVirtual.models.Clientes;
import com.LojaVirtual.models.Pedidos;
import com.LojaVirtual.models.Produtos;
import com.LojaVirtual.models.ProdutosPedido;
import com.LojaVirtual.repository.ClienteRepository;
import com.LojaVirtual.repository.PedidoRepository;
import com.LojaVirtual.repository.ProdutoRepository;
import com.LojaVirtual.repository.ProdutosPedidoRepository;
import com.LojaVirtual.resources.AuxiliarPedidos;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


/*
 * Autor: Fabiano Albino Ferreira.
 * 
 * Data da criação: 02/08/2018.
 * 
 * Descrição: Classe criada para testar os métodos GET e POST da Api
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class LojaVirtualApplicationTests {
	
	 final String BASE_PATH = "http://localhost:8080/api/pedidos";
	 
    //Injetamos o repositório para acesso ao Banco de dados
    @Autowired
    private ClienteRepository clienteRep;
    
    @Autowired
    private PedidoRepository pedidoRep;
    
    @Autowired
    private ProdutoRepository produtoRep;
    
    @Autowired
    private ProdutosPedidoRepository produtosPedidoRep;
     
    private RestTemplate restTemplate;
	
	//Método a a ser executado antes dos testes.
    @Before
    public void setUp() throws Exception {
 
    	Clientes cliente = clienteRep.save(new Clientes("Diego"));
    	Produtos produto = produtoRep.save(new Produtos("Abacaxi"));   	
    	Pedidos pedido = pedidoRep.save(new Pedidos("RUA", 2332213.00, cliente));
    	ProdutosPedido produtoPedido = produtosPedidoRep.save(new ProdutosPedido(pedido, produto, "PEDIDO - 1"));
    	
        //Inicializamos o objeto restTemplate
        restTemplate = new RestTemplate();
    }
    
    //Método de teste criado para testar o método POST da Api.
	@Test
    public void testPost() throws JsonProcessingException{
 
    	List<Long> codigoProd = new ArrayList<Long>(1);

		AuxiliarPedidos auxiliar = new AuxiliarPedidos("RUA", 1, codigoProd, 12.00);
    	
        //HTTP request do tipo POST passando o pedido como parâmetro
        Pedidos pedidoRet = restTemplate.postForObject(BASE_PATH, auxiliar , Pedidos.class);
 
        //Assert para verificar se o resultado foi o esperado
        Assert.assertEquals("RUA", pedidoRet.getEnderecoEntrega());
    }
	
	//Método de teste criado para testar o método GET da Api.
	@Test
	public void testGet() throws IOException{
		String response = restTemplate.getForObject(BASE_PATH, String.class);
		
		//Assert para verificar se o resultado foi o esperado.
		Assert.assertTrue(!response.isEmpty());
	}

}
