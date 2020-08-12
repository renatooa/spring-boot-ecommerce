package br.com.renato.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.renato.ecommerce.model.entity.pedido.Pedido;
import br.com.renato.ecommerce.repository.PedidoRepository;

@RestController()
@CrossOrigin(origins="*")
@RequestMapping("/pedidos")
public class PedidoController {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@GetMapping(name="/")
	public List<Pedido> get() {

		List<Pedido> pedidos = pedidoRepository.findAll();
		
		return pedidos;
	}

}
