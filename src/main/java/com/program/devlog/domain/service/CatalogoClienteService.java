/*
 * REGRAS DE NEGÓCIO 
 */

package com.program.devlog.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.program.devlog.domain.exception.NegocioException;
import com.program.devlog.domain.model.Cliente;
import com.program.devlog.domain.repository.ClienteRepository;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@Service
public class CatalogoClienteService {
	private ClienteRepository clienteRepository;
	
	public Cliente buscar(Long clienteId) {
		return clienteRepository.findById(clienteId)
		.orElseThrow(() -> new NegocioException("Cliente não encontrado"));
	}
	
	@Transactional		// declando que o método deve ser executado dentro de uma transação
	public Cliente salvar(Cliente cliente) {
		boolean emailEmUso = clienteRepository.findByEmail(cliente.getEmail())
				.stream()
				.anyMatch(clienteExistente -> !clienteExistente.equals(cliente));
		if (emailEmUso) {
			throw new NegocioException("O email já consta em outro cadastro.");
		}
		return clienteRepository.save(cliente);
	}
	
	public void excluir(Long clienteId) {
		clienteRepository.deleteById(clienteId);
	}
}
