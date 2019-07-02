package com.pwiii.agenda.resource;

import java.net.URI;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.pwiii.agenda.DTO.NovaSenhaDTO;
import com.pwiii.agenda.DTO.UsuarioDTO;
import com.pwiii.agenda.entity.UsuarioEntity;
import com.pwiii.agenda.service.UsuarioService;

@RestController
@RequestMapping(value="/usuario")
@CrossOrigin(origins = "*")
public class UsuarioResource {
	
	@Autowired
	UsuarioService service;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<UsuarioEntity> buscar(Authentication authentication){
		UsuarioEntity obj = service.getByEmail(authentication.getName());
		return ResponseEntity.ok(obj);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> salvar(@Valid @RequestBody UsuarioDTO objDTO){
		UsuarioEntity obj = new UsuarioEntity(
				null, 
				objDTO.getNome(), 
				objDTO.getEmail(), 
				objDTO.getSenha()
		);
		obj = service.salvar(obj);	
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	//@RequestMapping(method=RequestMethod.PUT)
	@RequestMapping(value="/PUT", method=RequestMethod.POST)
	public ResponseEntity<Void> atualizar(
			@Valid @RequestBody NovaSenhaDTO objDTO,
			Authentication authentication
	){
		UsuarioEntity obj = service.getByEmail(authentication.getName());
		obj.setSenha(objDTO.getSenha());
		obj = service.atualizar(obj);	
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
}
