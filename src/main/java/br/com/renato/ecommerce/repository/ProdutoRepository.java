package br.com.renato.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.renato.ecommerce.model.dto.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, String> {

}