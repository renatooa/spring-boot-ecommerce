package br.com.renato.ecommerce.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.renato.ecommerce.model.dto.PedidoDto;
import br.com.renato.ecommerce.model.dto.entity.pedido.Pedido;
import br.com.renato.ecommerce.repository.PedidoRepository;

@RestController()
@CrossOrigin(origins="*")
@RequestMapping("/pedidos")
public class PedidoController {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@GetMapping
	public List<Pedido> listarPedidos() {

		List<Pedido> pedidos = pedidoRepository.findAll();
		
		return pedidos;
	}
	
	@GetMapping(path = "/{idPedido}")
	public PedidoDto obterPedido(@PathVariable(required = true) String idPedido) {
		Optional<Pedido> pedido = pedidoRepository.findById(idPedido);
		return new PedidoDto(pedido.get());
	}
}