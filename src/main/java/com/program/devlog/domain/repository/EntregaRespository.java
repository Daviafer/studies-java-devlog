package com.program.devlog.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.program.devlog.domain.model.Entrega;

@Repository
public interface EntregaRespository extends JpaRepository<Entrega, Long> {

}
