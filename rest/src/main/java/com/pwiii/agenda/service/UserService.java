package com.pwiii.agenda.service;

import org.springframework.security.core.context.SecurityContextHolder;
import com.pwiii.agenda.config.UsuarioSpringSecurity;

public class UserService {

	public static UsuarioSpringSecurity autenticado() {
		try {
			return (UsuarioSpringSecurity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			return null;
		}
	}
}
