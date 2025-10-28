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

import com.company.inventory.model.Owner;
import com.company.inventory.response.OwnerResponseRest;
import com.company.inventory.services.IOwnerService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/vet1")
public class OwnerRestController {
	
	@Autowired
	private IOwnerService ownerService;
	
	/**
	 * 
	 * @param owner
	 * @return
	 */
	@PostMapping("/owner")
	public ResponseEntity<OwnerResponseRest> save(@RequestBody Owner owner) {
		ResponseEntity<OwnerResponseRest> response = ownerService.save(owner);
		return response;
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/owner/{id}")
	public ResponseEntity<OwnerResponseRest> searchOwnerById(@PathVariable Long id) {
		ResponseEntity<OwnerResponseRest> response = ownerService.searchById(id);
		return response;
	}
	
	/**
	 * 
	 * @param dpi
	 * @return
	 */
	@GetMapping("/owner/filter/{dpi}")
	public ResponseEntity<OwnerResponseRest> searchOwnerByDpi(@PathVariable String dpi) {
		ResponseEntity<OwnerResponseRest> response = ownerService.searchByDpi(dpi);
		return response;
	}
	
	/**
	 * 
	 * @param owner
	 * @param id
	 * @return
	 */
	@PutMapping("/owner/{id}")
	public ResponseEntity<OwnerResponseRest> update(@RequestBody Owner owner,@PathVariable Long id) {
		ResponseEntity<OwnerResponseRest> response = ownerService.update(owner, id);
		return response;
	}
	
	/**
	 * 
	 * @return
	 */
	@GetMapping("/owner")
	public ResponseEntity<OwnerResponseRest> searchOwner() {
		ResponseEntity<OwnerResponseRest> response = ownerService.search();
		return response;
	}
	
	@DeleteMapping("/owner/{id}")
	public ResponseEntity<OwnerResponseRest> deleteOwner(@PathVariable Long id) {
		ResponseEntity<OwnerResponseRest> response = ownerService.deleteById(id);
		return response;
	}
	
}
