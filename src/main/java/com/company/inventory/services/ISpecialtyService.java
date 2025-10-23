package com.company.inventory.services;

import org.springframework.http.ResponseEntity;

import com.company.inventory.response.SpecialtyResponseRest;

public interface ISpecialtyService {
	
	public ResponseEntity<SpecialtyResponseRest> search();

}
