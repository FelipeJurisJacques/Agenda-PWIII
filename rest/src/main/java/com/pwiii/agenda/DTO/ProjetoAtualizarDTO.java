package com.pwiii.agenda.DTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotEmpty;

public class ProjetoAtualizarDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	@NotEmpty
	private List<String> email = new ArrayList<>();

	public ProjetoAtualizarDTO() {
		super();
	}

	public ProjetoAtualizarDTO(List<String> email) {
		super();
		this.email = email;
	}

	public List<String> getEmail() {
		return email;
	}

	public void setEmail(List<String> email) {
		this.email = email;
	}
}
