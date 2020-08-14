package br.com.renato.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.renato.ecommerce.model.dto.PedidoDto;
import br.com.renato.ecommerce.model.exception.NaoEncontradoException;
import br.com.renato.ecommerce.model.recursos.PedidoRecursos;
import br.com.renato.ecommerce.service.PedidoService;

@RestController()
@CrossOrigin(origins = "*")
@RequestMapping("/pedidos")
public class PedidoController implements PedidoRecursos {

	@Autowired
	private PedidoService pedidoService;

	@Override
	@GetMapping
	public List<PedidoDto> listarPedidos(@RequestParam(name = "status-entrega", required = true) String statusEntrega)
			throws NaoEncontradoException {
		return pedidoService.listarPedidos(statusEntrega);
	}
	@Override
	@GetMapping(path = "/{idPedido}")
	public PedidoDto obterPedido(@PathVariable(required = true) String idPedido) throws NaoEncontradoException {
		return pedidoService.obterPedido(idPedido);
	}
}