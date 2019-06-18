package com.pwiii.agenda;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.pwiii.agenda.entity.UsuarioEntity;
import com.pwiii.agenda.enums.Perfil;
import com.pwiii.agenda.service.UsuarioService;

@SpringBootApplication
public class RestApplication implements CommandLineRunner{
	
	/*@Autowired
	private UsuarioService usuarioService;*/

	public static void main(String[] args) {
		SpringApplication.run(RestApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		UsuarioEntity usuario = new UsuarioEntity(null,  "Felipe", "felipejuris2016@Outlook.com", "1234");
		usuario.addPerfil(Perfil.ADMIN);
		
		//usuarioService.salvar(usuario);
	}
}
