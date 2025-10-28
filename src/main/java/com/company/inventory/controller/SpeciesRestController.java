package com.company.inventory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.inventory.model.Species;
import com.company.inventory.response.SpeciesResponseRest;
import com.company.inventory.services.ISpeciesService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/vet1")
public class SpeciesRestController {
	
	@Autowired
	private ISpeciesService speciesService;
	
	
	/**
	 * 
	 * @param species
	 * @return
	 */
	@PostMapping("/species")
	public ResponseEntity<SpeciesResponseRest> save(@RequestBody Species species) {
		ResponseEntity<SpeciesResponseRest> response = speciesService.save(species);
		return response;
	
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/species/{id}")
	public ResponseEntity<SpeciesResponseRest> searchSpeciesById(@PathVariable Long id) {

		ResponseEntity<SpeciesResponseRest> response = speciesService.searchById(id);
		return response;
	}
	
	/**
	 * 
	 * @param name
	 * @return
	 */
	@GetMapping("/species/filter/{name}")
	public ResponseEntity<SpeciesResponseRest> searchByName(@PathVariable String name){
		ResponseEntity<SpeciesResponseRest> response = speciesService.searchByName(name);
		return response;
	}
	
	/**
	 * 
	 * @return
	 */
	@GetMapping("/species")
	public ResponseEntity<SpeciesResponseRest> search(){
		ResponseEntity<SpeciesResponseRest> response = speciesService.search();
		return response;
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping("/species/{id}")
	public ResponseEntity<SpeciesResponseRest> delete(@PathVariable Long id) {
		
		ResponseEntity<SpeciesResponseRest> response = speciesService.deleteById(id);
		return response;
		
	}
	
	/**
	 * 
	 * @param species
	 * @param id
	 * @return
	 */
	@PutMapping("/species/{id}")
	public ResponseEntity<SpeciesResponseRest> update(@RequestBody Species species, @PathVariable Long id) {
		
		ResponseEntity<SpeciesResponseRest> response = speciesService.update(species, id);
		return response;
		
	}
	
}
