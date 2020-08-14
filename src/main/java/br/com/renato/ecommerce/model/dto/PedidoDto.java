package br.com.renato.ecommerce.model.dto;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.renato.ecommerce.model.dto.entity.Produto;
import br.com.renato.ecommerce.model.dto.entity.cliente.Cliente;
import br.com.renato.ecommerce.model.dto.entity.pedido.Pedido;
import br.com.renato.ecommerce.model.dto.entity.pedido.PedidoStatus;
import br.com.renato.ecommerce.model.dto.entity.pedido.ProdutoPedido;
import br.com.renato.ecommerce.model.exception.ElementoNaoEncontradoException;
import br.com.renato.ecommerce.repository.ClienteRepository;
import br.com.renato.ecommerce.repository.ProdutoRepository;

public class PedidoDto {

	@NotNull
	@NotBlank
	private String id;

	@NotNull
	@NotBlank
	private String idCliente;

	@NotNull
	@JsonProperty("data-cadastro")
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime dataCadastro;

	@NotNull
	@NotBlank
	@JsonProperty("status-entrega")
	private String statusEntrega;

	@NotNull
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

	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDateTime dataCadastro) {
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

		Optional<Cliente> cliente = clienteRepository.findById(idCliente);

		if (!cliente.isPresent()) {
			throw new ElementoNaoEncontradoException(MessageFormat.format("Cliente {0} não encontrado", idCliente));
		}

		Pedido pedido = new Pedido(getId(), getDataCadastro(), PedidoStatus.valueOf(getStatusEntrega().toUpperCase()),
				cliente.get());

		inserirProdutos(produtoRepository, pedido);

		return pedido;
	}

	private void inserirProdutos(ProdutoRepository produtoRepository, Pedido pedido) {
		List<ProdutoPedido> produtos = new ArrayList<ProdutoPedido>();

		this.produtos.forEach(produtoPedidoDto -> {

			Optional<Produto> produto = produtoRepository.findById(produtoPedidoDto.getProduto().getId());

			if (!produto.isPresent()) {
				throw new ElementoNaoEncontradoException(
						MessageFormat.format("Produto {0} não encontrado", produtoPedidoDto.getProduto().getId()));
			}

			produtos.add(new ProdutoPedido(pedido.getId(), produto.get().getId(), produtoPedidoDto.getQuantidade(),
					produto.get()));
		});

		pedido.setProdutos(produtos);
	}
}