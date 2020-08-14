package br.com.renato.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.renato.ecommerce.model.dto.entity.pedido.Pedido;
import br.com.renato.ecommerce.model.dto.entity.pedido.PedidoStatus;

public interface PedidoRepository extends JpaRepository<Pedido, String> {

	public List<Pedido> findByClienteId(String idCliente);

	public List<Pedido> findByStatus(PedidoStatus status);
}