package br.com.zup.edu.ingressounico.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zup.edu.ingressounico.model.Ingresso;

public interface IngressoRepository extends JpaRepository<Ingresso, Long>{

	public boolean existsByNumeroAndDataEvento(int numero, LocalDate dataEvento);

}
