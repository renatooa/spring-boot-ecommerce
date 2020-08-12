package br.com.renato.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.renato.ecommerce.model.entity.cliente.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, String> {

}