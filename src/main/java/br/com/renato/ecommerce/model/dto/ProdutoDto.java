package br.com.renato.ecommerce.model.dto;

import br.com.renato.ecommerce.model.dto.entity.Produto;

public class ProdutoDto {

	private String id;

	private String nome;

	private Double valor;

	private boolean disponivel = true;

	public ProdutoDto() {
	}

	public ProdutoDto(Produto produto) {
		this.id = produto.getId();
		this.nome = produto.getNome();
		this.valor = produto.getValor();
		this.disponivel = produto.isDisponivel();
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

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public boolean isDisponivel() {
		return disponivel;
	}

	public void setDisponivel(boolean disponivel) {
		this.disponivel = disponivel;
	}
}
