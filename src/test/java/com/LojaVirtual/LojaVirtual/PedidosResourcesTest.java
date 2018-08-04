package com.LojaVirtual.LojaVirtual;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.LojaVirtual.resources.PedidosResource;
@WebAppConfiguration
public class PedidosResourcesTest extends LojaVirtualApplicationTests{

	private MockMvc mockMvc;
	
	@Autowired
	private PedidosResource pedidosRes;
	
	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(pedidosRes).build();
	}
	
	@Test
	public void testGETPedidosResources() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/api/pedidos")).andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void testPOSTPedidosResources() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.post("/api/pedidos")
				.param("enderecoEntrega", "RUA")
				.param("codigoCliente", "1")
				.param("codigoProdutos", "")
				.param("totalPedido", "123")
				).andExpect(MockMvcResultMatchers.redirectedUrl("/api/pedidos"));
	}
	
	public PedidosResourcesTest () {
		// TODO Auto-generated constructor stub
	}
	

}
