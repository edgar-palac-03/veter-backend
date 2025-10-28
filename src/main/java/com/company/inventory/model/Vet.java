package com.company.inventory.model;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;


@Data
@Entity
@Table(name= "vet")
public class Vet implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2125499875048154040L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private String lastName;
	
	@Min(value = 1000000000000L, message = "El DPI debe tener 13 dígitos")
	@Max(value = 9999999999999L, message = "El DPI debe tener 13 dígitos")
	@Column(nullable = false, unique = true)
	private long dpi;

	
	@Min(value = 10000000, message = "El teléfono debe tener 8 dígitos")
	@Max(value = 99999999, message = "El teléfono debe tener 8 dígitos")
	@Column(nullable = false, unique = true)
	private long phone;
	
	@DecimalMin(value = "0.00", message = "El sueldo no puede ser negativo")
	@Column(nullable = false, precision = 10, scale = 2)
	private BigDecimal salary;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties ( {"hibernateLazyInitializer", "handler"})
	private Specialty specialty;
	
	
	
	
	
	
	

}