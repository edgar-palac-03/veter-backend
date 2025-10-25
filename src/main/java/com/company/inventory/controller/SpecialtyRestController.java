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

import com.company.inventory.model.Specialty;
import com.company.inventory.response.SpecialtyResponseRest;
import com.company.inventory.services.ISpecialtyService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/vet1")
public class SpecialtyRestController {
	
	@Autowired
	private ISpecialtyService specialtyService;
	
	
	/**
	 * Busca a todas la especialidades
	 * @return
	 */
	@GetMapping("/specialty")
	public ResponseEntity<SpecialtyResponseRest> searchSpecialty(){
		
		ResponseEntity<SpecialtyResponseRest> response = specialtyService.search();
		return response;		
	}
	
	/**
	 * Busca por Id
	 * @param id
	 * @return
	 */
	@GetMapping("/specialty/{id}")
	public ResponseEntity<SpecialtyResponseRest> searchSpecialtyById(@PathVariable Long id){
		
		ResponseEntity<SpecialtyResponseRest> response = specialtyService.searchById(id);
		return response;		
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/specialty/filter/{name}")
	public ResponseEntity<SpecialtyResponseRest> searchSpecialtyByName(@PathVariable String name){
		
		ResponseEntity<SpecialtyResponseRest> response = specialtyService.searchByName(name);
		return response;		
	}
	
	/**
	 * 
	 * @param specialty
	 * @return
	 */
	@PostMapping("/specialty")
	public ResponseEntity<SpecialtyResponseRest> save(@RequestBody Specialty specialty) {
		ResponseEntity<SpecialtyResponseRest> response = specialtyService.save(specialty);
		return response;
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping("/specialty/{id}")
	public ResponseEntity<SpecialtyResponseRest> deleteById(@PathVariable Long id) {
		ResponseEntity<SpecialtyResponseRest> response = specialtyService.deleteById(id);
		return response;
	}
	
	@PutMapping("/specialty/{id}")
	public ResponseEntity<SpecialtyResponseRest> update(@RequestBody Specialty specialty, @PathVariable Long id) {
		
		ResponseEntity<SpecialtyResponseRest> response = specialtyService.update(specialty, id);
		return response;
		
	}
}
