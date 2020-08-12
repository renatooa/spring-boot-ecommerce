package br.com.renato.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.renato.ecommerce.model.entity.pedido.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, String> {

}