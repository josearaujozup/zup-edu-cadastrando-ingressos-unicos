package br.com.zup.edu.ingressounico.controller;

import java.time.LocalDate;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.zup.edu.ingressounico.model.Ingresso;

public class IngressoDTO {
	
	@NotBlank
	private String nome;
	
	@NotBlank
	@CPF
    private String cpf;
	
	@NotNull
    private int numero;
	
	@NotNull
	@Future
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataEvento;

	public IngressoDTO(@NotBlank String nome, @NotBlank @CPF String cpf, @NotBlank int numero,
			@NotNull @Future LocalDate dataEvento) {
		this.nome = nome;
		this.cpf = cpf;
		this.numero = numero;
		this.dataEvento = dataEvento;
	}
	
	public IngressoDTO() {
		
	}

	public String getNome() {
		return nome;
	}

	public String getCpf() {
		return cpf;
	}

	public int getNumero() {
		return numero;
	}

	public LocalDate getDataEvento() {
		return dataEvento;
	}

	public Ingresso toModel() {
		return new Ingresso(nome, cpf, numero, dataEvento);
	}
	
}
