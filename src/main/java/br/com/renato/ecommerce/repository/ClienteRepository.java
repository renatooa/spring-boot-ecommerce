package br.com.renato.ecommerce.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.renato.ecommerce.model.dto.entity.cliente.Cliente;
import br.com.renato.ecommerce.model.dto.entity.cliente.ClienteStatus;

public interface ClienteRepository extends JpaRepository<Cliente, String> {
	
	public List<Cliente> findByStatus(ClienteStatus status, Sort ordem);

}