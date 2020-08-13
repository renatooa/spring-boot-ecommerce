package br.com.renato.ecommerce.model.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.renato.ecommerce.model.dto.entity.cliente.Cliente;
import br.com.renato.ecommerce.model.dto.entity.cliente.ClienteStatus;

public class ClienteDto {

	private String id;

	private String nome;

	@JsonFormat(pattern = "dd/MM/yyyy")
	@JsonProperty("data-cadastro")
	private Date dataCadastro;

	private String status;

	public ClienteDto() {
	}

	public ClienteDto(Cliente cliente) {
		this.id = cliente.getId();
		this.nome = cliente.getNome();
		this.dataCadastro = cliente.getDataCadastro();
		this.status = cliente.getStatus().toString();
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public Cliente toCliente() {
		return new Cliente(id, nome, dataCadastro, ClienteStatus.valueOf(status.toUpperCase()));
	}
	
	public void atualizar(Cliente cliente) {
		cliente.setDataCadastro(getDataCadastro());
		cliente.setNome(getNome());
		cliente.setStatus(ClienteStatus.valueOf(getStatus().toUpperCase()));
	}
}