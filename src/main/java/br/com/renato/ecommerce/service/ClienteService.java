package br.com.renato.ecommerce.service;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.renato.ecommerce.model.dto.ClienteDto;
import br.com.renato.ecommerce.model.dto.MensagemDto;
import br.com.renato.ecommerce.model.dto.PedidoDto;
import br.com.renato.ecommerce.model.dto.entity.cliente.Cliente;
import br.com.renato.ecommerce.model.dto.entity.cliente.ClienteStatus;
import br.com.renato.ecommerce.model.dto.entity.pedido.Pedido;
import br.com.renato.ecommerce.model.exception.NaoEncontradoException;
import br.com.renato.ecommerce.model.recursos.ClienteRecursos;
import br.com.renato.ecommerce.repository.ClienteRepository;
import br.com.renato.ecommerce.repository.PedidoRepository;
import br.com.renato.ecommerce.repository.ProdutoRepository;

@Service
public class ClienteService implements ClienteRecursos {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private PedidoRepository pedidpRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	public ClienteService() {
	}

	@Override
	public List<ClienteDto> listarClientes(String status, Sort ordem) throws NaoEncontradoException {

		List<Cliente> clientes = recuperarClientes(status, ordem);

		if (clientes.isEmpty()) {
			throw new NaoEncontradoException();
		}

		return clientes.stream().map(ClienteDto::new).collect(Collectors.toList());
	}

	private List<Cliente> recuperarClientes(String status, Sort ordem) {

		List<Cliente> clientes = new ArrayList<Cliente>();

		if (status == null) {
			clientes.addAll(clienteRepository.findAll(ordem));
		} else {
			clientes.addAll(clienteRepository
					.findByStatus(ClienteStatus.valueOf(status.toUpperCase(), ClienteStatus.OUTRO), ordem));
		}
		return clientes;
	}

	@Override
	public ClienteDto obterCLiente(String idCliente) throws NaoEncontradoException {
		Optional<Cliente> cliente = clienteRepository.findById(idCliente);

		if (!cliente.isPresent()) {
			throw new NaoEncontradoException();
		}

		return new ClienteDto(cliente.get());

	}

	@Override
	public ResponseEntity<MensagemDto> inserirCliente(ClienteDto clienteDto, UriComponentsBuilder uriBuilder) {
		Cliente cliente = clienteDto.toCliente();

		clienteRepository.save(cliente);

		String path = "/clientes/{idCliente}";

		URI uri = criarUri(uriBuilder, path, cliente.getId());

		return ResponseEntity.created(uri).body(new MensagemDto());
	}

	@Override
	public ResponseEntity<MensagemDto> atualizarCliente(ClienteDto clienteDto, String idCliente)
			throws NaoEncontradoException {
		Optional<Cliente> cliente = clienteRepository.findById(idCliente);

		if (!cliente.isPresent()) {
			throw new NaoEncontradoException();
		}

		clienteDto.atualizar(cliente.get());

		return ResponseEntity.ok().body(new MensagemDto());
	}

	@Override
	public List<PedidoDto> listarPedidosCliente(String idCliente) throws NaoEncontradoException {
		List<Pedido> pedidos = pedidpRepository.findByClienteId(idCliente);

		if (pedidos == null || pedidos.size() == 0) {
			throw new NaoEncontradoException();
		}

		return pedidos.stream().map(PedidoDto::new).collect(Collectors.toList());
	}

	@Override
	public ResponseEntity<MensagemDto> inserirPedido(PedidoDto pedidoDto, String idCliente,
			UriComponentsBuilder uriBuilder) {
		Pedido pedido = pedidoDto.toPedido(idCliente, clienteRepository, produtoRepository);

		pedidpRepository.save(pedido);

		String path = "/pedidos/{idPedido}";

		URI uri = criarUri(uriBuilder, path, pedido.getId());

		return ResponseEntity.created(uri).body(new MensagemDto());
	}

	private URI criarUri(UriComponentsBuilder uriBuilder, String path, String id) {

		URI uri = uriBuilder.path(path).buildAndExpand(id).toUri();

		return uri;
	}
}
