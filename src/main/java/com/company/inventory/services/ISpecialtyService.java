package com.company.inventory.services;

import org.springframework.http.ResponseEntity;

import com.company.inventory.model.Specialty;
import com.company.inventory.response.SpecialtyResponseRest;

public interface ISpecialtyService {
	
	public ResponseEntity<SpecialtyResponseRest> search();
	public ResponseEntity<SpecialtyResponseRest> searchById(Long id);
	public ResponseEntity<SpecialtyResponseRest> searchByName(String name);
	public ResponseEntity<SpecialtyResponseRest> save(Specialty specialty);
	public ResponseEntity<SpecialtyResponseRest> deleteById(Long id);
	public ResponseEntity<SpecialtyResponseRest> update(Specialty specialty, Long id);
	

}
