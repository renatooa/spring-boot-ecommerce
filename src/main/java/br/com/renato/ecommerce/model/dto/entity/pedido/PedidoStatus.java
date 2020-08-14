package br.com.renato.ecommerce.model.dto.entity.pedido;

public enum PedidoStatus {
	ENTREGUE, CONFIRMADO, OUTRO;

	public static PedidoStatus valueOf(String name, PedidoStatus ifNull) {
		PedidoStatus pedidoStatus = ifNull;

		try {
			pedidoStatus = valueOf(name);
		} catch (Exception e) {
		}

		return pedidoStatus;
	}
}