package com.pwiii.agenda.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pwiii.agenda.dao.AtividadeDao;
import com.pwiii.agenda.entity.AtividadeEntity;
import com.pwiii.agenda.exceptions.ObjNaoEncontradoException;

@Service
public class AtividadeService {
	
	@Autowired
	private AtividadeDao dao;
	
	public AtividadeEntity buscar(Integer id) {
		Optional<AtividadeEntity> curso = dao.findById(id);
		return curso.orElseThrow(()-> new ObjNaoEncontradoException("Objeto não encontrado"));
	}
	
	public List<AtividadeEntity> buscar() {
		return dao.findAll();
	}

	public AtividadeEntity salvar(AtividadeEntity obj) {
		Date date = new Date();
		obj.setId(null);
		obj.setEstado(1);
		obj.setCriado(date);
		return dao.save(obj);
	}
	
	public AtividadeEntity atualizar (AtividadeEntity obj) {
		AtividadeEntity banco = buscar(obj.getId());
		Date date = new Date();
		banco.setEstado(obj.getEstado());
		if(obj.getEstado() == 2) {
			banco.setInicio(date);
		}
		if(obj.getEstado() == 3) {
			banco.setTermino(date);
		}
		return dao.save(banco);
	}
	
	public void apagar(Integer id) {
		dao.deleteById(id);
	}
}
