package br.com.zup.edu.ingressounico.controller;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Map;

import javax.validation.Valid;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.edu.ingressounico.model.Ingresso;
import br.com.zup.edu.ingressounico.repository.IngressoRepository;

@RestController
@RequestMapping("/ingressos")
public class IngressoController {
	private final IngressoRepository repository;

	public IngressoController(IngressoRepository repository) {
		this.repository = repository;
	}
	
	@PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Valid IngressoDTO request, UriComponentsBuilder uriComponentsBuilder){
		
		if(repository.existsByNumeroAndDataEvento(request.getNumero(),request.getDataEvento())) {
			throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,"Ingresso já existe no sistema");
		}
		
        Ingresso ingresso = request.toModel();

        repository.save(ingresso);

        URI location = uriComponentsBuilder.path("/ingressos/{id}")
                .buildAndExpand(ingresso.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
	
	@ExceptionHandler
	public ResponseEntity<?> handleUniqueConstraintErrors(ConstraintViolationException e){
		
		
		Map<String, Object> body = Map.of(
				"message", "ingresso já existente no sistema",
				"timestamp", LocalDateTime.now()
		);
		
		return ResponseEntity.unprocessableEntity().body(body);
	}
}
