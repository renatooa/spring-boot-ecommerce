package br.com.renato.ecommerce.model.dto.entity.pedido;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.com.renato.ecommerce.model.dto.entity.Produto;

@Entity
@IdClass(ProdutoPedidoPK.class)
public class ProdutoPedido {

	@Id
	private String idPedido;

	@Id
	private String idProduto;

	private Integer quantidade;

	@ManyToOne
	@JoinColumn(name = "idProduto", referencedColumnName = "id", updatable=false, insertable= false)
	private Produto produto;
	
	public ProdutoPedido() {
	}
	
	public ProdutoPedido(String idPedido, String idProduto, Integer quantidade, Produto produto) {
		super();
		this.idPedido = idPedido;
		this.idProduto = idProduto;
		this.quantidade = quantidade;
		this.produto = produto;
	}

	public String getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(String idPedido) {
		this.idPedido = idPedido;
	}

	public String getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(String idProduto) {
		this.idProduto = idProduto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
}