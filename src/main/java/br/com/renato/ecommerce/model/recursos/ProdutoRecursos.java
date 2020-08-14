package br.com.renato.ecommerce.model.recursos;

import java.util.List;

import br.com.renato.ecommerce.model.dto.ProdutoDto;
import br.com.renato.ecommerce.model.exception.NaoEncontradoException;

public interface ProdutoRecursos {

	public List<ProdutoDto> listarProdutos() throws NaoEncontradoException;
}