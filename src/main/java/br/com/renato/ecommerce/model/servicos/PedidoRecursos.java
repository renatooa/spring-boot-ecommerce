package br.com.renato.ecommerce.model.servicos;

import java.util.List;

import br.com.renato.ecommerce.model.dto.PedidoDto;
import br.com.renato.ecommerce.model.exception.NaoEncontradoException;

public interface PedidoRecursos {

	public List<PedidoDto> listarPedidos(String statusEntrega) throws NaoEncontradoException;

	public PedidoDto obterPedido(String idPedido) throws NaoEncontradoException;

}