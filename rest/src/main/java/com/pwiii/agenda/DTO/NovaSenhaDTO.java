package com.pwiii.agenda.DTO;

import java.io.Serializable;
import javax.validation.constraints.NotEmpty;
import com.pwiii.agenda.entity.UsuarioEntity;

public class NovaSenhaDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@NotEmpty
	private String senha;
	
	public NovaSenhaDTO() {}

	public NovaSenhaDTO(UsuarioEntity obj) {
		this.senha = obj.getSenha();
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}
