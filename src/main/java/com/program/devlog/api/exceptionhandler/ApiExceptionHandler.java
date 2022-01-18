package com.program.devlog.api.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.program.devlog.domain.exception.NegocioException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler{
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		//pegando a classe Campo dentro da classe Problema
		List<Problema.Campo> campos = new ArrayList<>();
		// for para interação com os campo. Pegando todos erros e jogando para variável error. Após: Adicionando nos campos
		for(ObjectError error : ex.getBindingResult().getAllErrors()) {
			// realizando um Casting FieldError
			String nome = ((FieldError) error).getField();
			//mensagem específica do erro
			String mensagem = error.getDefaultMessage();
			//após atribuir os erros a variável, adicionando a variável campos e mostrando
			campos.add(new Problema.Campo(nome, mensagem));
		}
		
		//Construtor classe problema
		Problema problema = new Problema();
		problema.setStatus(status.value());
		problema.setDataHora(OffsetDateTime.now());
		problema.setTitulo("Faça o preenchimento correto dos campos.");
		problema.setCampos(campos);
		
		return handleExceptionInternal(ex, problema, headers, status, request);
	}
	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<Object> handleNegocio(NegocioException ex, WebRequest request){
		
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		//Construtor classe problema
		Problema problema = new Problema();
		problema.setStatus(status.value());
		problema.setDataHora(OffsetDateTime.now());
		problema.setTitulo(ex.getMessage());
		
		return handleExceptionInternal(ex, problema, new HttpHeaders(), status ,request);
	}
}
