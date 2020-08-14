package br.com.renato.ecommerce.model.dto.entity.cliente;

public enum ClienteStatus {
	ATIVO, INATIVO, OUTRO;
	
	
	public static ClienteStatus valueOf(String name, ClienteStatus ifNull) {
		ClienteStatus clienteStatus = ifNull;

		try {
			clienteStatus = valueOf(name);
		} catch (Exception e) {
		}

		return clienteStatus;
	}
}