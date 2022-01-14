package com.program.devlog.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.program.devlog.domain.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	
//	List<Cliente> findByNomeContaining(String nome);	// Buscando por qualquer nome LIKE
	
	List<Cliente> findByNome(String nome);
	


}
