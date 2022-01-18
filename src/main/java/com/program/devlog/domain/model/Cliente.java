package com.program.devlog.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.program.devlog.domain.ValidationGroups;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Entity		// relacionanmento com banco de dados - JPA
public class Cliente {
	
	@EqualsAndHashCode.Include
	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank	// impede nulo e vazio
	@Size(max = 60)		// tamanho máximo
	private String nome;
	
	@NotBlank
	@Email
	@Size(max=255)
	private String email;
	
	@NotBlank
	@Size(max=20)
	@Column(name = "fone")		// nome da comuna no banco de dados
	private String telefone;
	
}
