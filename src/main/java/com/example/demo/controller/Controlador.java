package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;

import com.example.demo.model.Cliente;
import com.example.demo.model.DetalleFactura;
import com.example.demo.model.Factura;
import com.example.demo.repository.ClienteRepository;
import com.example.demo.repository.DetalleFacturaRepository;
import com.example.demo.repository.FacturaRepository;

@Controller
public class Controlador {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private FacturaRepository facturaRepository;
	
	@Autowired
	private DetalleFacturaRepository detalleFacturaRepository;
	
	public Cliente crearCliente(Cliente cliente) {
		try {
			return clienteRepository.save(cliente);
		} catch (DataIntegrityViolationException e) {
			System.out.println("El correo ingresado ya existe.");
			return null;
		}
	}
	
	public Factura crearFactura(Factura factura, Cliente cliente) {
		try {
			if (!clienteRepository.existsById(cliente.getId())) {
				System.out.println("El cliente para la factura no existe.");
				return null;
			}
			return facturaRepository.save(factura);
		} catch (DataIntegrityViolationException e) {
			System.out.println("El numero de factura ya existe.");
			return null;
		}
	}
	
	public DetalleFactura crearDetalleFactura(DetalleFactura detalleFactura, Factura factura) {
		if (!facturaRepository.existsById(factura.getId())) {
			System.out.println("El factura no existe.");
			return null;
		}
		return detalleFacturaRepository.save(detalleFactura);
	}
	
	public Factura buscarFactura(String numeroFactura) {
		return facturaRepository.findByNumeroFactura(numeroFactura);
	}
	
	public Cliente actualizarCliente(Cliente nuevoCliente) {
		try {
			
			Optional<Cliente> optCliente = clienteRepository.findById(nuevoCliente.getId());
			if (optCliente.isEmpty()) {
				System.out.println("El cliente no existe.");
				return null;
			}
			Cliente cliente = optCliente.get();
			cliente.setNombre(nuevoCliente.getNombre());
			cliente.setCorreo(nuevoCliente.getCorreo());
			return clienteRepository.save(cliente);
		} catch (DataIntegrityViolationException e) {
			System.out.println("El correo ingresado ya existe.");
			return null;
		}
	}
	
}
