package com.pwiii.agenda.DTO;

import java.io.Serializable;
import java.util.Date;
import javax.validation.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import com.pwiii.agenda.entity.AtividadeEntity;

public class AtividadeDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;

	@NotEmpty
	private String nome;
	
	@NotEmpty
	private String descrissao;
	
	private Integer estado;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date criado;
	
	//@NotEmpty
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date inicio;
	
	//@NotEmpty
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date inicioEstimado;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date termino;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date terminoEstimado;

	//@NotEmpty
	private Integer projeto;
	
	public AtividadeDTO() {
		super();
	}

	public AtividadeDTO(AtividadeEntity obj) {
		super();
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.descrissao = obj.getDescrissao();
		this.estado = obj.getEstado();
		this.criado = obj.getCriado();
		this.inicio = obj.getInicio();
		this.inicioEstimado = obj.getInicioEstimado();
		this.termino = obj.getTermino();
		this.terminoEstimado = obj.getTerminoEstimado();
		this.projeto = obj.getProjeto().getId();
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

	public String getDescrissao() {
		return descrissao;
	}

	public void setDescrissao(String descrissao) {
		this.descrissao = descrissao;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public Date getCriado() {
		return criado;
	}

	public void setCriado(Date criado) {
		this.criado = criado;
	}

	public Date getInicio() {
		return inicio;
	}

	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}

	public Date getInicioEstimado() {
		return inicioEstimado;
	}

	public void setInicioEstimado(Date inicioEstimado) {
		this.inicioEstimado = inicioEstimado;
	}

	public Date getTermino() {
		return termino;
	}

	public void setTermino(Date termino) {
		this.termino = termino;
	}

	public Date getTerminoEstimado() {
		return terminoEstimado;
	}

	public void setTerminoEstimado(Date terminoEstimado) {
		this.terminoEstimado = terminoEstimado;
	}

	public Integer getProjeto() {
		return projeto;
	}

	public void setProjeto(Integer projeto) {
		this.projeto = projeto;
	}
}
