package com.pwiii.agenda.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class AtividadeEntity implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private String nome;
	private String descrissao;
	private Integer estado;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date criado;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date inicio;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date inicioEstimado;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date termino;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date terminoEstimado;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="projeto_id")
	private ProjetoEntity projeto;
	
	public AtividadeEntity() {
		super();
	}

	public AtividadeEntity(Integer id, String nome, String descrissao, Integer estado, Date criado, Date inicio,
			Date inicioEstimado, Date termino, Date terminoEstimado, ProjetoEntity projeto) {
		super();
		this.id = id;
		this.nome = nome;
		this.descrissao = descrissao;
		this.estado = estado;
		this.criado = criado;
		this.inicio = inicio;
		this.inicioEstimado = inicioEstimado;
		this.termino = termino;
		this.terminoEstimado = terminoEstimado;
		this.projeto = projeto;
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

	public ProjetoEntity getProjeto() {
		return projeto;
	}

	public void setProjeto(ProjetoEntity projeto) {
		this.projeto = projeto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AtividadeEntity other = (AtividadeEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}

