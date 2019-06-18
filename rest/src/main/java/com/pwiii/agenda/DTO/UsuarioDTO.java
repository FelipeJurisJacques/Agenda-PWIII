package com.pwiii.agenda.DTO;

import java.io.Serializable;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import com.pwiii.agenda.entity.UsuarioEntity;

public class UsuarioDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	@NotEmpty
	private String nome;

	@NotEmpty
	@Email(message = "Email inválido")
	private String email;
	
	@NotEmpty
	private String senha;
	
	public UsuarioDTO() {}

	public UsuarioDTO(UsuarioEntity obj) {
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.email = obj.getEmail();
		this.senha = obj.getSenha();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}
