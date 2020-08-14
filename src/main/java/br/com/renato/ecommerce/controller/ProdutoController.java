package br.com.renato.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.renato.ecommerce.model.dto.ProdutoDto;
import br.com.renato.ecommerce.model.exception.NaoEncontradoException;
import br.com.renato.ecommerce.model.recursos.ProdutoRecursos;
import br.com.renato.ecommerce.service.ProdutoService;

@RestController()
@CrossOrigin(origins = "*")
@RequestMapping("/produtos")
public class ProdutoController implements ProdutoRecursos {

	@Autowired
	private ProdutoService produtoService;

	@GetMapping
	@Override
	public List<ProdutoDto> listarProdutos() throws NaoEncontradoException {
		return produtoService.listarProdutos();
	}
}