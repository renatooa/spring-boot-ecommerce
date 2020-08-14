package br.com.renato.ecommerce.model.exception;

public class ElementoNaoEncontradoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ElementoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
}