package com.pwiii.agenda.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pwiii.agenda.dao.ProjetoDao;
import com.pwiii.agenda.entity.ProjetoEntity;
import com.pwiii.agenda.entity.UsuarioEntity;
import com.pwiii.agenda.exceptions.ObjNaoEncontradoException;

@Service
public class ProjetoService {
	
	@Autowired
	private ProjetoDao dao;
	
	public List<ProjetoEntity> buscar() {
		return dao.findAll();
	} 
	
	public ProjetoEntity buscar(Integer id) {
		Optional<ProjetoEntity> curso = dao.findById(id);
		return curso.orElseThrow(()-> new ObjNaoEncontradoException("Objeto não encontrado"));
	}

	public ProjetoEntity salvar(ProjetoEntity obj) {
		obj.setId(null);
		return dao.save(obj);
	}
	
	public ProjetoEntity atualizar (ProjetoEntity obj, List<UsuarioEntity> novosUsuarios) {
		List<UsuarioEntity> usuarios = obj.getUsuarios();
		for (UsuarioEntity novo : novosUsuarios) {
			if(!usuarios.contains(novo)) {
				obj.addUsuario(novo);
				System.out.println("Adicionando ao projeto: " + novo);
			}
		}
		return dao.save(obj);
	}
	
	public void apagar(Integer id, UsuarioEntity usuario) {
		ProjetoEntity obj = buscar(id);
		List<UsuarioEntity> list = obj.getUsuarios();
		System.out.println("Lista de usuarios atual: " + list);
		if(list.size() == 1) {
			for(UsuarioEntity item: list) {
				if(item.getId() == usuario.getId()) {
					dao.deleteById(id);
					break;
				}
			}
		}/*
		else {
			int i = 0;
			for(UsuarioEntity item: list) {
				i++;
				if(item.getId() == usuario.getId()) {
					list.remove(i);
					//obj.setUsuarios(list);
					//dao.save(obj);
					break;
				}
			}
		}*/
	}
}
