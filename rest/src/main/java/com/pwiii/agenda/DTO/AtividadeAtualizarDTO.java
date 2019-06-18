package com.pwiii.agenda.DTO;

import java.io.Serializable;
import javax.validation.constraints.NotEmpty;
import com.pwiii.agenda.entity.AtividadeEntity;

public class AtividadeAtualizarDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	@NotEmpty
	private Integer estado;
	
	public AtividadeAtualizarDTO() {
		super();
	}

	public AtividadeAtualizarDTO(AtividadeEntity obj) {
		super();
		this.estado = obj.getEstado();
	}
	
	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}
}
