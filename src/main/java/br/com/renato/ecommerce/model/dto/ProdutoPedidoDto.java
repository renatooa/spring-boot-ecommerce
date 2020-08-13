package br.com.renato.ecommerce.model.dto;

import br.com.renato.ecommerce.model.dto.entity.pedido.ProdutoPedido;

public class ProdutoPedidoDto {

	private ProdutoDto produto;
	private Integer quantidade;

	public ProdutoPedidoDto() {
	}

	public ProdutoPedidoDto(ProdutoPedido produtoPedido) {
		this.produto = new ProdutoDto(produtoPedido.getProduto());
		this.quantidade = produtoPedido.getQuantidade();
	}

	public ProdutoDto getProduto() {
		return produto;
	}

	public void setProduto(ProdutoDto produto) {
		this.produto = produto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
}