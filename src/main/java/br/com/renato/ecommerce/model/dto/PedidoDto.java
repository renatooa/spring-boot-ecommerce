package br.com.renato.ecommerce.model.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.renato.ecommerce.model.dto.entity.Produto;
import br.com.renato.ecommerce.model.dto.entity.cliente.Cliente;
import br.com.renato.ecommerce.model.dto.entity.pedido.Pedido;
import br.com.renato.ecommerce.model.dto.entity.pedido.PedidoStatus;
import br.com.renato.ecommerce.model.dto.entity.pedido.ProdutoPedido;
import br.com.renato.ecommerce.repository.ClienteRepository;
import br.com.renato.ecommerce.repository.ProdutoRepository;

public class PedidoDto {

	private String id;

	private String idCliente;

	@JsonProperty("data-cadastro")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataCadastro;

	@JsonProperty("status-entrega")
	private String statusEntrega;

	private List<ProdutoPedidoDto> produtos;

	public PedidoDto() {
	}

	public PedidoDto(Pedido pedido) {
		this.id = pedido.getId();
		this.idCliente = pedido.getCliente().getId();
		this.dataCadastro = pedido.getDataCadastro();
		this.statusEntrega = pedido.getStatus().toString();

		this.produtos = pedido.getProdutos().stream().map(ProdutoPedidoDto::new).collect(Collectors.toList());
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getStatusEntrega() {
		return statusEntrega;
	}

	public void setStatusEntrega(String statusEntrega) {
		this.statusEntrega = statusEntrega;
	}

	public List<ProdutoPedidoDto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<ProdutoPedidoDto> produtos) {
		this.produtos = produtos;
	}

	public Pedido toPedido(String idCliente, ClienteRepository clienteRepository, ProdutoRepository produtoRepository) {

		Cliente cliente = clienteRepository.findById(idCliente).get();

		Pedido pedido = new Pedido(getId(), getDataCadastro(), PedidoStatus.valueOf(getStatusEntrega().toUpperCase()),
				cliente);

		List<ProdutoPedido> produtos = new ArrayList<ProdutoPedido>();

		this.produtos.forEach(produtoPedidoDto -> {
			Produto produto = produtoRepository.findById(produtoPedidoDto.getProduto().getId()).get();

			produtos.add(new ProdutoPedido(pedido.getId(), produto.getId(), produtoPedidoDto.getQuantidade(), produto));
		});

		pedido.setProdutos(produtos);

		return pedido;
	}
}