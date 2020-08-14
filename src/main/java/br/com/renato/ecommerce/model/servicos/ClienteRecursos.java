package br.com.renato.ecommerce.model.servicos;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.renato.ecommerce.model.dto.ClienteDto;
import br.com.renato.ecommerce.model.dto.MensagemDto;
import br.com.renato.ecommerce.model.dto.PedidoDto;
import br.com.renato.ecommerce.model.exception.NaoEncontradoException;

public interface ClienteRecursos {

	public List<ClienteDto> listarClientes(String status, Sort ordem) throws NaoEncontradoException;

	public ClienteDto obterCLiente(String idCliente) throws NaoEncontradoException;

	public ResponseEntity<MensagemDto> inserirCliente(ClienteDto clienteDto, UriComponentsBuilder uriBuilder);

	public ResponseEntity<MensagemDto> atualizarCliente(ClienteDto clienteDto, String idCliente);

	public List<PedidoDto> listarPedidosCliente(String idCliente) throws NaoEncontradoException;

	public ResponseEntity<MensagemDto> inserirPedido(PedidoDto pedidoDto, String idCliente,
			UriComponentsBuilder uriBuilder);
}