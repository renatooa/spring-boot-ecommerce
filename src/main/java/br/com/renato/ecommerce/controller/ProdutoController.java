package br.com.renato.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.renato.ecommerce.model.dto.entity.Produto;
import br.com.renato.ecommerce.repository.ProdutoRepository;

@RestController()
@CrossOrigin(origins = "*")
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoRepository produtoRepository;

	@GetMapping
	public List<Produto> listarProdutos() {
		return produtoRepository.findAll();
	}
}