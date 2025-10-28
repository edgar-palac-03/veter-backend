package com.company.inventory.services;

import org.springframework.http.ResponseEntity;

import com.company.inventory.model.Species;
import com.company.inventory.response.SpeciesResponseRest;

public interface ISpeciesService {
	
	public ResponseEntity<SpeciesResponseRest> save(Species species);
	public ResponseEntity<SpeciesResponseRest> searchById(Long id);
	public ResponseEntity<SpeciesResponseRest> searchByName(String name);
	public ResponseEntity<SpeciesResponseRest> search();
	public ResponseEntity<SpeciesResponseRest> deleteById(Long Id);
	public ResponseEntity<SpeciesResponseRest> update(Species species ,Long Id);
	

}
