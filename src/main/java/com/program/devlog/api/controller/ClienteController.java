package com.program.devlog.api.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.program.devlog.domain.model.Cliente;
import com.program.devlog.domain.repository.ClienteRepository;
import com.program.devlog.domain.service.CatalogoClienteService;

import lombok.AllArgsConstructor;

@AllArgsConstructor		// construtor lombok
@RestController		// Pra tratar requisições http e devolver resposta. controlador REST
@RequestMapping("/clientes")
public class ClienteController {
	

	private ClienteRepository clienteRepository;
	private CatalogoClienteService catalogoClienteService;
	
	@GetMapping // Mapeando na URI
	public List<Cliente> listar() {
		
//		return ClienteRepository.findByNomeContaining("a");		// buscando como LIKE
		return clienteRepository.findAll();
	}
	@GetMapping("/{clienteId}") // clienteId = variável de caminho, mostrar o ID
	public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId){
		return clienteRepository.findById(clienteId)
//				.map(cliente -> ResponseEntity.ok(cliente))
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente adicionar(@Valid @RequestBody Cliente cliente) {
		// no lugar de acessar diretamente o repositório pra adicinar, atualizar ..., passa-se pela classe de serviço
//		return clienteRepository.save(cliente);
		return catalogoClienteService.salvar(cliente);
	}
	@PutMapping("/{clienteId}")
	public ResponseEntity<Cliente> atualizar(@Valid @PathVariable Long clienteId, @RequestBody Cliente cliente){
		if (!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}
		cliente.setId(clienteId);
		// no lugar de acessar diretamente o repositório pra adicinar, atualizar ..., passa-se pela classe de serviço
//		cliente = clienteRepository.save(cliente);
		cliente = catalogoClienteService.salvar(cliente);
		
		return ResponseEntity.ok(cliente);
	}
	@DeleteMapping("/{clienteId}")
	public ResponseEntity<Void> remover(@PathVariable Long clienteId){
		if (!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}
		
		// no lugar de acessar diretamente o repositório pra adicinar, atualizar ..., passa-se pela classe de serviço
//		clienteRepository.deleteById(clienteId);
		catalogoClienteService.excluir(clienteId);
		
		return ResponseEntity.noContent().build();
	}
}
