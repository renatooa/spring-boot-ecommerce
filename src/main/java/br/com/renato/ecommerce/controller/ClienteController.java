package br.com.renato.ecommerce.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.renato.ecommerce.model.dto.ClienteDto;
import br.com.renato.ecommerce.model.dto.MensagemDto;
import br.com.renato.ecommerce.model.dto.PedidoDto;
import br.com.renato.ecommerce.model.dto.entity.cliente.Cliente;
import br.com.renato.ecommerce.model.dto.entity.cliente.ClienteStatus;
import br.com.renato.ecommerce.model.dto.entity.pedido.Pedido;
import br.com.renato.ecommerce.repository.ClienteRepository;
import br.com.renato.ecommerce.repository.PedidoRepository;
import br.com.renato.ecommerce.repository.ProdutoRepository;

@RestController()
@CrossOrigin(origins = "*")
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private PedidoRepository pedidpRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@GetMapping()
	public List<ClienteDto> listarClientes(String status, Sort ordem) {

		List<Cliente> clientes = new ArrayList<Cliente>();

		if (status == null) {
			clientes.addAll(clienteRepository.findAll(ordem));
		} else {
			clientes.addAll(clienteRepository.findByStatus(ClienteStatus.valueOf(status), ordem));
		}

		return clientes.stream().map(ClienteDto::new).collect(Collectors.toList());
	}

	@GetMapping(path = "/{idCliente}")
	public ClienteDto obterCLiente(@PathVariable(required = true) String idCliente) {
		Optional<Cliente> cliente = clienteRepository.findById(idCliente);
		return new ClienteDto(cliente.get());
	}

	@PostMapping()
	public ResponseEntity<MensagemDto> inserirCliente(@RequestBody(required = true) ClienteDto clienteDto,
			UriComponentsBuilder uriBuilder) {

		Cliente cliente = clienteDto.toCliente();

		clienteRepository.save(cliente);

		URI uri = uriBuilder.path("/clientes/{idCliente}").buildAndExpand(cliente.getId()).toUri();

		return ResponseEntity.created(uri).body(new MensagemDto());
	}

	@PutMapping(path = "/{idCliente}")
	public ResponseEntity<MensagemDto> atualizarCliente(@RequestBody(required = true) ClienteDto clienteDto,
			@PathVariable(required = true) String idCliente) {

		Cliente cliente = clienteRepository.findById(idCliente).get();

		clienteDto.atualizar(cliente);

		clienteRepository.save(cliente);

		return ResponseEntity.ok().body(new MensagemDto());
	}

	@GetMapping(path = "/{idCliente}/pedidos")
	public List<PedidoDto> listarPedidosCliente(@PathVariable(required = true) String idCliente) {
		List<Pedido> pedidos = pedidpRepository.findByClienteId(idCliente);
		return pedidos.stream().map(PedidoDto::new).collect(Collectors.toList());
	}

	@PostMapping(path = "/{idCliente}/pedidos")
	public ResponseEntity<MensagemDto> inserirPedido(@RequestBody(required = true) PedidoDto pedidoDto,
			@PathVariable(required = true) String idCliente, UriComponentsBuilder uriBuilder) {

		Pedido pedido = pedidoDto.toPedido(idCliente, clienteRepository, produtoRepository);

		pedidpRepository.save(pedido);

		URI uri = uriBuilder.path("/pedidos/{idPedido}").buildAndExpand(pedido.getId()).toUri();

		return ResponseEntity.created(uri).body(new MensagemDto());
	}
}