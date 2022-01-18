package com.program.devlog.domain.service;

import java.time.OffsetDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.program.devlog.domain.model.Cliente;
import com.program.devlog.domain.model.Entrega;
import com.program.devlog.domain.model.StatusEntrega;
import com.program.devlog.domain.repository.EntregaRespository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class SolicitacaoEntregaService {
	
	private CatalogoClienteService catalogoClienteService;
	private EntregaRespository entregaRespository;
	
	@Transactional
	public Entrega solicitar(Entrega entrega) {
		Cliente cliente = catalogoClienteService.buscar(entrega.getCliente().getId());
				
		entrega.setCliente(cliente);
		entrega.setStatus(StatusEntrega.PENDENTE);
		entrega.setDataPedido(OffsetDateTime.now());
		
		return entregaRespository.save(entrega);
	}

}
