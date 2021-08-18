package com.dac.marina.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dac.marina.model.Autor;
import com.dac.marina.repository.AutorRepository;

@RestController
@RequestMapping("/api/v1")
public class AutorController {
	
	@Autowired
	private AutorRepository autorRepository;
	
	@GetMapping("/autores")
	public List<Autor> listaTodos() {
		return autorRepository.findAll();
	}
	
	@GetMapping("/autores/{id}")
	public ResponseEntity<Autor> getAutorById(@PathVariable(value = "id") Long autorId)
	 throws ResourceNotFoundException {
		Autor autor = autorRepository.findById(autorId)
	      .orElseThrow(() -> new ResourceNotFoundException());
	    return ResponseEntity.ok().body(autor);
	}
	
	@PostMapping("/autores")
	public Autor criaAutor(@Validated @RequestBody Autor autor) {
	    return autorRepository.save(autor);
	}
	
	@PutMapping("/autores/{id}")
	public ResponseEntity<Autor> atualizaAutor(@PathVariable(value = "id") Long autorId,
	  @Validated @RequestBody Autor AutorItem) throws ResourceNotFoundException {
	    Autor autor = autorRepository.findById(autorId)
	    .orElseThrow(() -> new ResourceNotFoundException());

	    autor.setNum_ordem(AutorItem.getNum_ordem());
	    autor.setEmail(AutorItem.getEmail());
	    autor.setPrimeiro_nome(AutorItem.getPrimeiro_nome());
	    autor.setNome_meio(AutorItem.getNome_meio());
	    autor.setSobrenome(AutorItem.getSobrenome());
	    autor.setAfiliacao(AutorItem.getAfiliacao());
	    autor.setAfiliacao_ingles(AutorItem.getAfiliacao_ingles());
	    autor.setPais(AutorItem.getPais());
	    autor.setOrcID(AutorItem.getOrcID());

	    final Autor autorAtualizado = autorRepository.save(autor);
	    return ResponseEntity.ok(autorAtualizado);
	}
	
	@DeleteMapping("/autores/{id}")
	public Map<String, Boolean> deleteAutor(@PathVariable(value = "id") Long autorId)
	  throws ResourceNotFoundException {
	    Autor autor = autorRepository.findById(autorId)
	      .orElseThrow(() -> new ResourceNotFoundException());

	    autorRepository.delete(autor);
	    Map<String, Boolean> response = new HashMap<>();
	    response.put("Autor excluido com sucesso", Boolean.TRUE);
	    return response;
	}

}
