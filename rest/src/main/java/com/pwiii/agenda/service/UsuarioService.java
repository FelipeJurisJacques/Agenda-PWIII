package com.pwiii.agenda.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.pwiii.agenda.dao.UsuarioDao;
import com.pwiii.agenda.entity.UsuarioEntity;
import com.pwiii.agenda.enums.Perfil;
import com.pwiii.agenda.exceptions.ObjNaoEncontradoException;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioDao dao;

	@Autowired
	BCryptPasswordEncoder encoder;
	
	public UsuarioEntity buscar(Integer id) {
		Optional<UsuarioEntity> curso = dao.findById(id);
		return curso.orElseThrow(()-> new ObjNaoEncontradoException("Objeto não encontrado"));
	}
	
	public List<UsuarioEntity> buscar() {
		return dao.findAll();
	}
	
	public UsuarioEntity getByEmail(String email) {
		return dao.findByEmail(email);
	}

	public UsuarioEntity salvar(UsuarioEntity obj) {
		obj.setId(null);	
		obj.addPerfil(Perfil.COMUM);
		obj.setSenha(encoder.encode(obj.getSenha()));
		return dao.save(obj);
	}
	
	public UsuarioEntity atualizar (UsuarioEntity obj) {
		UsuarioEntity banco = buscar(obj.getId());
		obj.setSenha(encoder.encode(obj.getSenha()));
		return dao.save(banco);
	}
	
	public void apagar(Integer id) {
		dao.deleteById(id);
	}
}
