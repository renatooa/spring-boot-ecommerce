package br.com.renato.ecommerce.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.renato.ecommerce.model.dto.PedidoDto;
import br.com.renato.ecommerce.model.dto.entity.pedido.Pedido;
import br.com.renato.ecommerce.model.dto.entity.pedido.PedidoStatus;
import br.com.renato.ecommerce.model.exception.NaoEncontradoException;
import br.com.renato.ecommerce.model.recursos.PedidoRecursos;
import br.com.renato.ecommerce.repository.PedidoRepository;

@Service
public class PedidoService implements PedidoRecursos {

	@Autowired
	private PedidoRepository pedidoRepository;

	@Override
	public List<PedidoDto> listarPedidos(String statusEntrega) throws NaoEncontradoException {

		List<Pedido> pedidos = pedidoRepository.findByStatus(PedidoStatus.valueOf(statusEntrega.toUpperCase(), PedidoStatus.OUTRO));

		if (pedidos == null || pedidos.isEmpty()) {
			throw new NaoEncontradoException();
		}

		return pedidos.stream().map(PedidoDto::new).collect(Collectors.toList());
	}

	@Override
	public PedidoDto obterPedido(String idPedido) throws NaoEncontradoException {

		Optional<Pedido> pedido = pedidoRepository.findById(idPedido);

		if (!pedido.isPresent()) {
			throw new NaoEncontradoException();
		}

		return new PedidoDto(pedido.get());
	}
}