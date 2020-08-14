package br.com.renato.ecommerce.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.renato.ecommerce.model.dto.ProdutoDto;
import br.com.renato.ecommerce.model.dto.entity.Produto;
import br.com.renato.ecommerce.model.exception.NaoEncontradoException;
import br.com.renato.ecommerce.model.servicos.ProdutoRecursos;
import br.com.renato.ecommerce.repository.ProdutoRepository;

@Service
public class ProdutoService implements ProdutoRecursos {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Override
	public List<ProdutoDto> listarProdutos() throws NaoEncontradoException {

		List<Produto> produtos = produtoRepository.findAll();

		if (produtos == null || produtos.isEmpty()) {
			throw new NaoEncontradoException();
		}

		return produtos.stream().map(ProdutoDto::new).collect(Collectors.toList());
	}
}