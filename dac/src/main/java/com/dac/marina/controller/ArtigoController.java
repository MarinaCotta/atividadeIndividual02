package com.dac.marina.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dac.marina.model.Artigo;
import com.dac.marina.model.Autor;
import com.dac.marina.repository.ArtigoRepository;

@RestController
@RequestMapping("/api/v1")
public class ArtigoController {
	
	@Autowired
	private ArtigoRepository artigoRepository;
	
	@GetMapping("/artigos")
	public List<Artigo> listaTodos() {
		return artigoRepository.findAll();
	}
	
	@GetMapping("/artigos/{id}")
	public ResponseEntity<Artigo> getArtigoById(@PathVariable(value = "id") Long artigoId)
	 throws ResourceNotFoundException {
		Artigo artigo = artigoRepository.findById(artigoId)
	      .orElseThrow(() -> new ResourceNotFoundException());
	    return ResponseEntity.ok().body(artigo);
	}
	
	@GetMapping("/autoresArtigo/{id}")
	public ResponseEntity<List<Autor>> getArtigosVolume(@PathVariable(value = "id") Long artigoId)
	 throws ResourceNotFoundException {
		Artigo artigo = artigoRepository.findById(artigoId)
	      .orElseThrow(() -> new ResourceNotFoundException());
	    return ResponseEntity.ok().body(artigo.getLista_autores());
	}
	
	@PostMapping("/artigos")
	public Artigo criaArtigo(@Validated @RequestBody Artigo artigo) {
	    return artigoRepository.save(artigo);
	}
	
	@PutMapping("edita/autoresArtigo/{id}")
	public ResponseEntity<Artigo> atualizaAutoresArtigo(@PathVariable(value = "id") Long autorId,
	  @Validated @RequestBody Artigo artigoItem) throws ResourceNotFoundException {
	    Artigo artigo = artigoRepository.findById(autorId)
	    .orElseThrow(() -> new ResourceNotFoundException());

	    artigo.setLista_autores(artigoItem.getLista_autores());

	    final Artigo artigoAtualizado = artigoRepository.save(artigo);
	    return ResponseEntity.ok(artigoAtualizado);
	}
	
}
