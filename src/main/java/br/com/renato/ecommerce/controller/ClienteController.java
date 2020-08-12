package br.com.renato.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.renato.ecommerce.model.entity.cliente.Cliente;
import br.com.renato.ecommerce.repository.ClienteRepository;

@RestController()
@CrossOrigin(origins = "*")
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private ClienteRepository clienteRepository;

	@GetMapping(name = "/")
	public List<Cliente> get() {
		return clienteRepository.findAll();
	}
}