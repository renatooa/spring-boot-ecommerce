package br.com.renato.ecommerce.model.entity.pedido;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.renato.ecommerce.model.entity.cliente.Cliente;

@Entity
public class Pedido {

	@Id
	private String id;

	@Temporal(TemporalType.DATE)
	private Date dataCadastro;

	@Enumerated(EnumType.STRING)
	private PedidoStatus status;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "idCliente", referencedColumnName = "id")
	private Cliente cliente;

	@OneToMany
	@JoinColumn(name = "idPedido", referencedColumnName = "id", insertable = false, updatable = false)
	private List<ProdutoPedido> produtos;

	public Pedido() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public PedidoStatus getStatus() {
		return status;
	}

	public void setStatus(PedidoStatus status) {
		this.status = status;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<ProdutoPedido> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<ProdutoPedido> produtos) {
		this.produtos = produtos;
	}
}