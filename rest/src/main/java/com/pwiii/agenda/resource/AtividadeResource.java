package com.pwiii.agenda.resource;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.pwiii.agenda.DTO.AtividadeAtualizarDTO;
import com.pwiii.agenda.DTO.AtividadeDTO;
import com.pwiii.agenda.entity.AtividadeEntity;
import com.pwiii.agenda.entity.ProjetoEntity;
import com.pwiii.agenda.service.AtividadeService;

@RestController
@RequestMapping(value="/atividade")
@CrossOrigin(origins = "*")
public class AtividadeResource {
	
	@Autowired
	AtividadeService service;
	
	@RequestMapping(method=RequestMethod.GET)
	public List<AtividadeDTO> listar() {
		List<AtividadeEntity> lista = service.buscar();
		List<AtividadeDTO> listaDTO = lista.stream().map(
				obj -> new AtividadeDTO(obj)).collect(Collectors.toList()
		);
		return listaDTO;		
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<AtividadeEntity> listar(
			@PathVariable Integer id
	){
		AtividadeEntity obj = service.buscar(id);
		return ResponseEntity.ok(obj);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> salvar(@Valid @RequestBody AtividadeDTO objDTO){
		
		ProjetoEntity projeto = new ProjetoEntity();
		projeto.setId(objDTO.getProjeto());
		
		AtividadeEntity obj = new AtividadeEntity(
				null, 
				objDTO.getDescrissao(), 
				objDTO.getNome(),
				null,
				null,
				null,
				objDTO.getInicioEstimado(),
				null,
				objDTO.getTerminoEstimado(),
				projeto
		);
		
		obj = service.salvar(obj);	
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	//@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	@RequestMapping(value="/PUT/{id}", method=RequestMethod.POST)
	public ResponseEntity<Void> atualizar(
			@Valid @RequestBody AtividadeAtualizarDTO objDTO,
			@PathVariable Integer id
	){
		AtividadeEntity obj = new AtividadeEntity(
				id, 
				null, 
				null,
				objDTO.getEstado(),
				null,
				null,
				null,
				null,
				null,
				null
		);
		
		obj = service.atualizar(obj);	
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	//@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	@RequestMapping(value="/DELETE/{id}", method=RequestMethod.POST)
	public ResponseEntity<Void> deletar(
			@PathVariable Integer id
	){
		service.apagar(id);
		return ResponseEntity.noContent().build();
	}
}
