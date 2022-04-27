package br.com.zup.edu.ingressounico.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Table(uniqueConstraints = {
		@UniqueConstraint(name = "UK_numero_dataEvento", columnNames = {"numero", "dataEvento"})
})
@Entity
public class Ingresso {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(nullable = false)
    private String nome;
	
	@Column(nullable = false)
    private String cpf;
	
	@Column(nullable = false)
    private int numero;
	
	@Column(nullable = false)
    private LocalDate dataEvento;

	public Ingresso(String nome, String cpf, int numero, LocalDate dataEvento) {
		this.nome = nome;
		this.cpf = cpf;
		this.numero = numero;
		this.dataEvento = dataEvento;
	}
	
	/**
     * @deprecated construtor para uso exclusivo do Hibernate
     */
    @Deprecated
	public Ingresso() {
	}
	
    public Long getId() {
		return id;
	}
}
