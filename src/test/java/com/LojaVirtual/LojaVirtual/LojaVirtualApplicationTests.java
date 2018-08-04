package com.LojaVirtual.LojaVirtual;

import java.io.IOException;
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


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
//@TestPropertySource(locations="classpath:test.properties")
//@WebAppConfiguration
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
     
    //Definimos o restTemplate
    private RestTemplate restTemplate;
     
    //Definimos o JacksonMapper responsável por converter
    //JSON para Objeto e vice versa
    private ObjectMapper MAPPER = new ObjectMapper();
	
	
    @Before
    public void setUp() throws Exception {
 
    	Clientes cliente = clienteRep.save(new Clientes("Diego"));
    	Produtos produto = produtoRep.save(new Produtos("Abacaxi"));   	
    	Pedidos pedido = pedidoRep.save(new Pedidos(cliente.getCodigo(), "RUA", 2332213.00, cliente));
    	ProdutosPedido produtoPedido = produtosPedidoRep.save(new ProdutosPedido(pedido, produto, "PEDIDO - 1"));
    	
        //Inicializamos o objeto restTemplate
        restTemplate = new RestTemplate();
    }
    
	@Test
    public void testPost() throws JsonProcessingException{
 
    	List<String> codigoProd = Arrays.asList("1");

		AuxiliarPedidos auxiliar = new AuxiliarPedidos("RUA", 1, codigoProd, 12.00);
    	
        //Fazemos um HTTP request do tipo POST passando o pedido como parâmetro
        Pedidos pedidoRet = restTemplate.postForObject(BASE_PATH, auxiliar , Pedidos.class);
 
        //Verificamos se o resultado da requisição é igual ao esperado
        //Se sim significa que tudo correu bem
        Assert.assertEquals("RUA", pedidoRet.getEnderecoEntrega());
    }
	
	@Test
	public void testGet() throws IOException{
		String response = restTemplate.getForObject(BASE_PATH, String.class);
		Assert.assertTrue(!response.isEmpty());
	}

}
