package br.com.renato.ecommerce.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
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
import br.com.renato.ecommerce.model.exception.NaoEncontradoException;
import br.com.renato.ecommerce.service.ClienteService;

@RestController()
@CrossOrigin(origins = "*")
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	@GetMapping()
	public List<ClienteDto> listarClientes(String status, Sort ordem) throws NaoEncontradoException {
		return clienteService.listarClientes(status, ordem);
	}

	@GetMapping(path = "/{idCliente}")
	public ClienteDto obterCLiente(@PathVariable(required = true) String idCliente) throws NaoEncontradoException {
		return clienteService.obterCLiente(idCliente);
	}

	@PostMapping
	@Transactional
	public ResponseEntity<MensagemDto> inserirCliente(@RequestBody(required = true) @Valid ClienteDto clienteDto,
			UriComponentsBuilder uriBuilder) {

		return clienteService.inserirCliente(clienteDto, uriBuilder);
	}

	@Transactional
	@PutMapping(path = "/{idCliente}")
	public ResponseEntity<MensagemDto> atualizarCliente(@RequestBody(required = true) @Valid ClienteDto clienteDto,
			@PathVariable(required = true) String idCliente) throws NaoEncontradoException {

		return clienteService.atualizarCliente(clienteDto, idCliente);
	}

	@GetMapping(path = "/{idCliente}/pedidos")
	public List<PedidoDto> listarPedidosCliente(@PathVariable(required = true) String idCliente)
			throws NaoEncontradoException {

		return clienteService.listarPedidosCliente(idCliente);
	}

	@Transactional
	@PostMapping(path = "/{idCliente}/pedidos")
	public ResponseEntity<MensagemDto> inserirPedido(@RequestBody(required = true) @Valid PedidoDto pedidoDto,
			@PathVariable(required = true) String idCliente, UriComponentsBuilder uriBuilder) {

		return clienteService.inserirPedido(pedidoDto, idCliente, uriBuilder);
	}
}