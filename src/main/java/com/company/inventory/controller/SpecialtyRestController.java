package com.company.inventory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.inventory.response.SpecialtyResponseRest;
import com.company.inventory.services.ISpecialtyService;

@RestController
@RequestMapping("/api/vet1")
public class SpecialtyRestController {
	
	@Autowired
	private ISpecialtyService specialty;
	
	
	/**
	 * Busca a todas la especialidades
	 * @return
	 */
	@GetMapping("/specialty")
	public ResponseEntity<SpecialtyResponseRest> searchSpecialty(){
		
		ResponseEntity<SpecialtyResponseRest> response = specialty.search();
		return response;		
	}

}
