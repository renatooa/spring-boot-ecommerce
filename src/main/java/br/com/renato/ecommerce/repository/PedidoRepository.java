package br.com.renato.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.renato.ecommerce.model.dto.entity.pedido.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, String> {
	
	public List<Pedido> findByClienteId(String idCliente);
}