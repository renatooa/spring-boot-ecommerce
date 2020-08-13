package br.com.renato.ecommerce.model.dto.entity.pedido;

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

import br.com.renato.ecommerce.model.dto.entity.cliente.Cliente;

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

	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name = "idPedido", referencedColumnName = "id")
	private List<ProdutoPedido> produtos;

	public Pedido() {
	}
	
	public Pedido(String id, Date dataCadastro, PedidoStatus status, Cliente cliente) {
		super();
		this.id = id;
		this.dataCadastro = dataCadastro;
		this.status = status;
		this.cliente = cliente;
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