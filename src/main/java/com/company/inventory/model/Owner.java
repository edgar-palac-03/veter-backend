package com.company.inventory.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Data
@Entity
@Table(name="owner")
public class Owner implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7011695535590044441L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private String lastName;
	
	private String dpi;
	
	private String phone;
	
	private String address;
	
	private String email;

}
