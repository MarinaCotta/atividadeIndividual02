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

import com.dac.marina.model.Artigo;
import com.dac.marina.model.Autor;
import com.dac.marina.model.Volume;
import com.dac.marina.repository.VolumeRepository;


@RestController
@RequestMapping("/api/v1")
public class VolumeController {
	
	@Autowired
	private VolumeRepository volumeRepository;
	
	@GetMapping("/volumes")
	public List<Volume> listaTodos() {
		return volumeRepository.findAll();
	}
	
	@GetMapping("/volumes/{id}")
	public ResponseEntity<Volume> getVolumeById(@PathVariable(value = "id") Long volumeId)
	 throws ResourceNotFoundException {
		Volume volume = volumeRepository.findById(volumeId)
	      .orElseThrow(() -> new ResourceNotFoundException());
	    return ResponseEntity.ok().body(volume);
	}
	
	@GetMapping("/volume/{id}/artigos")
	public ResponseEntity<List<Artigo>> getArtigosVolume(@PathVariable(value = "id") Long volumeId)
	 throws ResourceNotFoundException {
		Volume volume = volumeRepository.findById(volumeId)
	      .orElseThrow(() -> new ResourceNotFoundException());
	    return ResponseEntity.ok().body(volume.getLista_artigos());
	}

	@GetMapping("/volume/{id}/artigos/autores")
	public ResponseEntity<List<Autor>> getAutoresArtigoVolume(@PathVariable(value = "id") Long volumeId, Long artigoId)
	 throws ResourceNotFoundException {
		Volume volume = volumeRepository.findById(volumeId)
	      .orElseThrow(() -> new ResourceNotFoundException());
		List<Artigo> auxListArt = volume.getLista_artigos();
		List<Autor> auxListAutor = null;
		for (int i = 0; i< auxListArt.size(); i++) {
			auxListAutor = auxListArt.get(i).getLista_autores();
		}
	    return ResponseEntity.ok().body(auxListAutor);
	}
	
	@PostMapping("/volumes")
	public Volume criaVolume(@Validated @RequestBody Volume volume) {
	    return volumeRepository.save(volume);
	}
	
	@PutMapping("/volumes/{id}")
	public ResponseEntity<Volume> atualizaVolume(@PathVariable(value = "id") Long volumeId,
	  @Validated @RequestBody Volume volumeItem) throws ResourceNotFoundException {
	    Volume volume = volumeRepository.findById(volumeId)
	    .orElseThrow(() -> new ResourceNotFoundException());

	    volume.setSigla_evento(volumeItem.getSigla_evento());
	    volume.setNum_edicao(volumeItem.getNum_edicao());
	    volume.setCidade(volumeItem.getCidade());
	    volume.setData_inicio(volumeItem.getData_inicio());
	    volume.setDescr_port(volumeItem.getDescr_port());
	    volume.setDescr_ingles(volumeItem.getDescr_ingles());
	    volume.setLista_artigos(volume.getLista_artigos());

	    final Volume volumeAtualizado = volumeRepository.save(volume);
	    return ResponseEntity.ok(volumeAtualizado);
	}
	
	
	@DeleteMapping("/volumes/{id}")
	public Map<String, Boolean> deleteVolume(@PathVariable(value = "id") Long volumeId)
	  throws ResourceNotFoundException {
	    Volume volume = volumeRepository.findById(volumeId)
	      .orElseThrow(() -> new ResourceNotFoundException());

	    volumeRepository.delete(volume);
	    Map<String, Boolean> response = new HashMap<>();
	    response.put("Volume excluido com sucesso", Boolean.TRUE);
	    return response;
	}
	
	
	@DeleteMapping("/volumes/{id}/artigos/{id}")
	public ResponseEntity<List<Artigo>> deleteArtigoVolume(@PathVariable(value = "id") Long volumeId)
	 throws ResourceNotFoundException {
		Volume volume = volumeRepository.findById(volumeId)
	      .orElseThrow(() -> new ResourceNotFoundException());
		//List<Artigo> auxListArt = volume.getLista_artigos();
		 //volumeRepository.delete(auxListArt);
		 Map<String, Boolean> response = new HashMap<>();
		 response.put("Artigo excluido com sucesso", Boolean.TRUE);
		 return null;
	}
}
