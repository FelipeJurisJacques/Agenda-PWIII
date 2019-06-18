package com.pwiii.agenda.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.pwiii.agenda.config.UsuarioSpringSecurity;
import com.pwiii.agenda.dao.UsuarioDao;
import com.pwiii.agenda.entity.UsuarioEntity;

@Service
public class UsuarioSpringSecurityService implements UserDetailsService {

	@Autowired
	private UsuarioDao dao;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UsuarioEntity usuarioEntity = dao.findByEmail(email);
		if (usuarioEntity == null) {
			throw new UsernameNotFoundException(email);
		} else {
			return new UsuarioSpringSecurity(
					usuarioEntity.getId(),
					usuarioEntity.getEmail(),
					usuarioEntity.getSenha(),
					usuarioEntity.getPerfis()
			);
		}
	}
}
