package com.pwiii.agenda.resource;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.pwiii.agenda.DTO.ProjetoAtualizarDTO;
import com.pwiii.agenda.DTO.ProjetoDTO;
import com.pwiii.agenda.entity.ProjetoEntity;
import com.pwiii.agenda.entity.UsuarioEntity;
import com.pwiii.agenda.service.ProjetoService;
import com.pwiii.agenda.service.UsuarioService;

@RestController
@RequestMapping(value = "/projeto")
@CrossOrigin(origins = "http://localhost")
public class ProjetoResource {

	@Autowired
	ProjetoService service;

	@Autowired
	UsuarioService usuarioService;

	/*
	@RequestMapping(method = RequestMethod.GET)
	public List<ProjetoEntity> listar(Authentication authentication) {
		//UsuarioEntity usuario = usuarioService.getByEmail(authentication.getName());
		List<ProjetoEntity> list = service.buscar();
		return list;
	}
	*/

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> salvar(@Valid @RequestBody ProjetoDTO objDTO, Authentication authentication) {
		ProjetoEntity obj = new ProjetoEntity(null, objDTO.getNome(), objDTO.getDescricao());

		UsuarioEntity usuario = usuarioService.getByEmail(authentication.getName());
		obj.addUsuario(usuario);

		obj = service.salvar(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	//@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@RequestMapping(value = "/PUT/{id}", method = RequestMethod.POST)
	public ResponseEntity<Void> atualizar(@Valid @RequestBody ProjetoAtualizarDTO objDTO, @PathVariable Integer id) {
		ProjetoEntity obj = service.buscar(id);
		List<UsuarioEntity> novosUsuarios = new ArrayList<>();
		for (String item : objDTO.getEmail()) {
			UsuarioEntity usuario = usuarioService.getByEmail(item);
			System.out.println("Email detectado: " + item);
			if (usuario.getId() != null) {
				novosUsuarios.add(usuario);
			}
		}
		//return ResponseEntity.noContent().build();
		obj = service.atualizar(obj, novosUsuarios);	
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	//@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@RequestMapping(value = "/DELETE/{id}", method = RequestMethod.POST)
	public ResponseEntity<Void> deletar(@PathVariable Integer id, Authentication authentication) {
		UsuarioEntity usuario = usuarioService.getByEmail(authentication.getName());
		service.apagar(id, usuario);
		return ResponseEntity.noContent().build();
	}
}
