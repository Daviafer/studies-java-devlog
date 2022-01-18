package com.program.devlog.api.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(Include.NON_NULL)	// incluir propriedades não nulas (retirando o "campos" na exception do email já cadastrado)
@Getter
@Setter
public class Problema {
	
	private Integer status;
	private OffsetDateTime dataHora;
	private String titulo;
	private List<Campo> campos;

	//Criando a classe Campo
	
	@AllArgsConstructor
	@Getter
	public static class Campo{
		private String nome;
		private String mensagem;
	}
}
