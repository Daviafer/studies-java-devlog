package com.program.devlog.api.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.program.devlog.domain.model.Cliente;

@RestController		// Pra tratar requisições http e devolver resposta. controlador REST
public class ClienteController {
	
	@GetMapping("/clientes") // Mapeando na URI
	public List<Cliente> listar() {
		
//		Cliente cliente1 = new Cliente();
		var cliente1 = new Cliente();
		cliente1.setId(1L);
		cliente1.setNome("Fulano Tal");
		cliente1.setTelefone("11 1111-1111");
		cliente1.setEmail("email@email.com");
		
		var cliente2 = new Cliente();
		cliente2.setId(1L);
		cliente2.setNome("Nome Tal Dois");
		cliente2.setTelefone("11 1111-1111");
		cliente2.setEmail("email@email.com");
		
		return Arrays.asList(cliente1, cliente2);
	}
}
