package br.com.renato.ecommerce.model.dto.entity.cliente;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Cliente {

	@Id
	private String id;

	private String nome;

	@Temporal(TemporalType.DATE)
	private Date dataCadastro;

	@Enumerated(EnumType.STRING)
	private ClienteStatus status;

	public Cliente() {
	}
	
	public Cliente(String id, String nome, Date dataCadastro, ClienteStatus status) {
		super();
		this.id = id;
		this.nome = nome;
		this.dataCadastro = dataCadastro;
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public ClienteStatus getStatus() {
		return status;
	}

	public void setStatus(ClienteStatus status) {
		this.status = status;
	}
}