package com.program.devlog.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.program.devlog.domain.ValidationGroups;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity		// mapeando como uma entidade do Jakarta Persistenses
public class Entrega {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
		
	@ManyToOne
	private Cliente cliente;
	
	@Embedded		// abstrair os dados do destinatário p outra classe mapeando para outra mesma tabela entrega
	private Destinatario destinatario;
	
	private BigDecimal taxa;
	
	@Enumerated(EnumType.STRING)	// o próprio nome do status
	private StatusEntrega status;
	
	private OffsetDateTime dataPedido;
	
	private OffsetDateTime dataFinalizacao;	
	
}
