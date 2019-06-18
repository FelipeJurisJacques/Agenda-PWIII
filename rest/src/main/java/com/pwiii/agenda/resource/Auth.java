package com.pwiii.agenda.resource;

import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.pwiii.agenda.config.JWTUtil;
import com.pwiii.agenda.config.UsuarioSpringSecurity;
import com.pwiii.agenda.service.UserService;

@RestController
@RequestMapping(value = "/auth")
public class Auth {

	@Autowired
	private JWTUtil jwtUtil;

	@RequestMapping(method = RequestMethod.POST, value = "/refreshtoken")
	public ResponseEntity<Void> refreshtoken(HttpServletResponse response) {
		UsuarioSpringSecurity usuarioSpringSecurity = UserService.autenticado();
		String token = jwtUtil.generateToken(usuarioSpringSecurity.getUsername());
		response.addHeader("Authorization", "Bearer " + token);
		response.addHeader("access-control-expose-headers", "Authorization");
		return ResponseEntity.noContent().build();
	}
}
