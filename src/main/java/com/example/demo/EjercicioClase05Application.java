package com.example.demo;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.controller.Controlador;
import com.example.demo.model.Cliente;
import com.example.demo.model.DetalleFactura;
import com.example.demo.model.Factura;

@SpringBootApplication
public class EjercicioClase05Application {

	public static void main(String[] args) {
		SpringApplication.run(EjercicioClase05Application.class, args);
	}
	
	@Bean
	CommandLineRunner testMain(Controlador controlador) {
		return args -> {
			// Metodo crearCliente exitoso
			Cliente cliente1 = new Cliente();
			cliente1.setCorreo("elizabeth@gmail.com");
			cliente1.setNombre("Elizabeth");
			
			Cliente clienteCreado1 = controlador.crearCliente(cliente1);
			System.out.println("Cliente creado 1: " + clienteCreado1.getNombre());
			
			//Metodo crearCliente error correo repetido
			Cliente cliente2 = new Cliente();
			cliente2.setCorreo("elizabeth@gmail.com");
			cliente2.setNombre("Kathy");
			
			Cliente clienteCreado2 = controlador.crearCliente(cliente2);
			System.out.println("Cliente creado 2: " + clienteCreado2);
			
			//Metodo crear factura exitoso
			Factura factura1 = new Factura();
			factura1.setCliente(clienteCreado1);
			factura1.setNumeroFactura("F001-00000001");
			factura1.setFechaEmision(LocalDateTime.now());
			
			Factura facturaCreada1 = controlador.crearFactura(factura1, clienteCreado1);
			System.out.println("Factura creada 1: " + facturaCreada1.getNumeroFactura());
			
			//Metodo crear factura error numero de factura repetido
			Factura factura2 = new Factura();
			factura2.setCliente(clienteCreado1);
			factura2.setNumeroFactura("F001-00000001");
			factura2.setFechaEmision(LocalDateTime.now());
			
			Factura facturaCreada2 = controlador.crearFactura(factura2, clienteCreado1);
			System.out.println("Factura creada 2: " + facturaCreada2);
			
			//Metodo crear factura error cliente no existe
			Cliente clienteNoCreado = new Cliente();
			clienteNoCreado.setId(999);
			clienteNoCreado.setCorreo("kathy@gmail.com");
			clienteNoCreado.setNombre("Kathy");
			
			Factura factura3 = new Factura();
			factura3.setCliente(clienteNoCreado);
			factura3.setNumeroFactura("F001-00000002");
			factura2.setFechaEmision(LocalDateTime.now());
			
			Factura facturaCreada3 = controlador.crearFactura(factura3, clienteNoCreado);
			System.out.println("Factura creada 3: " + facturaCreada3);
			
			//Metodo crear detalle de factura exitoso
			DetalleFactura detalleFactura1 = new DetalleFactura();
			detalleFactura1.setFactura(facturaCreada1);
			detalleFactura1.setProducto("Helado de chocolate");
			detalleFactura1.setCantidad(2);
			detalleFactura1.setPrecioUnitario(7.50);
			
			DetalleFactura detalleFacturaCreada1 = controlador.crearDetalleFactura(detalleFactura1, facturaCreada1);
			System.out.println("Detalle de factura creada 1: " + detalleFacturaCreada1.getProducto());
			
			//Metodo crear detalle de factura error factura no existe
			Factura facturaNoCreada = new Factura();
			facturaNoCreada.setId(999);
			facturaNoCreada.setCliente(clienteNoCreado);
			facturaNoCreada.setNumeroFactura("F001-00000003");
			facturaNoCreada.setFechaEmision(LocalDateTime.now());
			
			DetalleFactura detalleFactura2 = new DetalleFactura();
			detalleFactura2.setFactura(facturaNoCreada);
			detalleFactura2.setProducto("Helado de vainilla");
			detalleFactura2.setCantidad(3);
			detalleFactura2.setPrecioUnitario(7.80);
			
			DetalleFactura detalleFacturaCreada2 = controlador.crearDetalleFactura(detalleFactura2, facturaNoCreada);
			System.out.println("Detalle de factura creada 2: " + detalleFacturaCreada2);
			
			//Metodo actualizar cliente exitoso
			clienteCreado1.setCorreo("micorreo@gmail.com");
			clienteCreado1.setNombre("mi nombre");
			
			Cliente clienteActualizado1 = controlador.actualizarCliente(clienteCreado1);
			System.out.println("Cliente actualizado 1: " + clienteActualizado1.getCorreo());
			
			//Metodo actualizar cliente error cliente no existe
			clienteNoCreado.setCorreo("otrocorreo@gmail.com");
			clienteNoCreado.setNombre("otronombre");
			
			Cliente clienteActualizado2 = controlador.actualizarCliente(clienteNoCreado);
			System.out.println("Cliente actualizado 2: " + clienteActualizado2);
			
			//Metodo buscar factura exitoso
			Factura facturaBusq1 = controlador.buscarFactura("F001-00000001");
			System.out.println("Factura encontrada: " + facturaBusq1.getNumeroFactura());
			
			//Metodo buscar factura no existe
			Factura facturaBusq2 = controlador.buscarFactura("F001-00000009");
			System.out.println("Factura no encontrada: " + facturaBusq2);
		};
	}

}
