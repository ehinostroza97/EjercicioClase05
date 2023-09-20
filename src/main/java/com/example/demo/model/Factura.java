package com.example.demo.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "factura")
@Data
public class Factura implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 247897303540597684L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String numeroFactura;
	private LocalDateTime fechaEmision;
	@ManyToOne
	@JoinColumn(name = "cliente")
	private Cliente cliente;
	@OneToMany(mappedBy = "factura")
	private List<DetalleFactura> detallesFactura;
}
