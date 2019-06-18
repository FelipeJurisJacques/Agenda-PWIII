package com.pwiii.agenda.DTO;

import java.io.Serializable;
import javax.validation.constraints.NotEmpty;
import com.pwiii.agenda.entity.ProjetoEntity;

public class ProjetoDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;

	@NotEmpty
	private String nome;
	
	@NotEmpty
	private String descricao;

	public ProjetoDTO() {
		super();
	}

	public ProjetoDTO(ProjetoEntity obj) {
		super();
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.descricao = obj.getDescricao();
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
